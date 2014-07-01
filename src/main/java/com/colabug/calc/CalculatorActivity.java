package com.colabug.calc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.otto.Subscribe;

public class CalculatorActivity extends FragmentActivity
{
    private String TAG = CalculatorActivity.class.getSimpleName();

    // Display
    private DisplayFragment displayFragment;

    // Calculations
    protected Operation operation   = Operation.NONE;
    protected int       storedValue = 0;

    // Error state tracking
    protected boolean isInErrorState = false;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.main );

        displayFragment = (DisplayFragment) getSupportFragmentManager().findFragmentById( R.id.display_fragment );
    }

    /**
     * Handles the selection of a number.
     *
     * @param number - number entered
     *
     * TODO: Most, if not all, of this responsibility should live in
     *       the display fragment.
     */
    @Subscribe
    public void onNumberSelected( NumberEnteredEvent number )
    {
        if ( isInErrorState )
        {
            // TODO: Post to bus that there's an error to be handled
            clearErroredState();
        }

        if ( operation == Operation.NONE ||
             operation == Operation.EQUAL )
        {
            displayFragment.setDisplay( number );
            operation = Operation.NUMBER;
        }
        else if ( displayFragment.isDisplayingOperation() )
        {
            displayFragment.setDisplay( number );
        }
        else
        {
            appendNumber( number.getNumber() );
        }
    }

    private void appendNumber( String number )
    {
        displayFragment.setDisplay( getCurrentDisplayString() + number );
    }

    private String getCurrentDisplayString()
    {
        return displayFragment.getValue();
    }

    /**
     * Handles the selection of an operator.
     *
     * @param operationEvent - operation selected
     */
    @Subscribe
    public void onOperatorSelected( OperationSelectedEvent operationEvent )
    {
        // Ignore clicks when in an error state, when no number
        // entered, or when no operation has been set
        if ( isInErrorState ||
             operation == Operation.NONE ||
             TextUtils.isEmpty( displayFragment.getValue() ) )
        {
            return;
        }

        // Store value
        if ( !displayFragment.isDisplayingOperation() )
        {
            storeDisplayedValue();
        }

        // Store operation & update display. Having the operator
        // update outside of the above condition means that
        // the user can change their mind.
        // NOTE: Can enter error state when storing the value
        if ( !isInErrorState )
        {
            operation = operationEvent.getOperator();
            // TODO: May need to pass in the string for the operator?
            //       Is this already attached to the enum?
            displayFragment.setDisplay( operationEvent );
        }
    }

    private void storeDisplayedValue()
    {
        try
        {
            storedValue = Integer.parseInt( getCurrentDisplayString() );
        }
        catch ( NumberFormatException e )
        {
            startErrorState( R.string.ERROR );
        }
    }

    /**
     * Handles the selection of the equals key.
     */
    @Subscribe
    public void onEqualSelected( EqualsEvent equals )
    {
        // They must have entered a number
        if ( shouldPerformCalculation() )
        {
            performCalculation();
        }
    }

    private boolean shouldPerformCalculation()
    {
        return !isInErrorState &&
               !displayFragment.isDisplayingOperation() &&
               operation != Operation.NONE &&
               !TextUtils.isEmpty( getCurrentDisplayString() );
    }

    private void performCalculation()
    {
        // Attempt to obtain integer from current display
        int value;
        try
        {
            value = Integer.parseInt( getCurrentDisplayString() );
        }
        catch ( NumberFormatException e )
        {
            startErrorState( R.string.ERROR, getCurrentDisplayString() );
            return;
        }

        // Perform the operation
        switch ( operation )
        {
            case PLUS:
                displayFragment.setDisplay( storedValue + value );
                break;

            case MINUS:
                displayFragment.setDisplay( storedValue - value );
                break;

            case MULTIPLY:
                displayFragment.setDisplay( storedValue * value );
                break;

            case DIVIDE:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    displayFragment.setDisplay( storedValue / value );
                }
                break;

            case MODULO:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    displayFragment.setDisplay( storedValue % value );
                }

                break;
        }

        storedValue = 0;
        operation = Operation.EQUAL;
    }

    private void startErrorState( int stringId )
    {
        startErrorState( stringId, String.valueOf( storedValue ) );
    }

    // NOTE: Robolectric doesn't currently support the fragment getString()
    //       functionality. I submitted a pull request here:
    //       https://github.com/pivotal/robolectric/pull/300
    // TODO: Maybe a lie now, try it!
    private void startErrorState( int stringId, String number )
    {
        Log.d( TAG, "Can't format number = " + number );
        displayFragment.setDisplay( getString( stringId ) );
        isInErrorState = true;
    }

    private void clearErroredState()
    {
        displayFragment.clearDisplayedValue();
        isInErrorState = false;
    }

    private boolean willEquateToNan( int value )
    {
        return value == 0;
    }

    private void startNanState()
    {
        startErrorState( R.string.NAN );
    }

    /**
     * Handles the selection of the clear button.
     *
     * @param clearEvent - clear selected
     */
    @Subscribe
    public void onClearSelected( ClearEvent clearEvent )
    {
        displayFragment.clearDisplayedValue();
        storedValue = 0;
        operation = Operation.NONE;
        isInErrorState = false;
    }
}
