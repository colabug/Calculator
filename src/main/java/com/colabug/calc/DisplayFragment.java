package com.colabug.calc;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.squareup.otto.Subscribe;

/**
 * @since 1.0
 */
public class DisplayFragment extends BaseFragment
{
    private String TAG = DisplayFragment.class.getSimpleName();

    // View
    private View     layout;
    protected EditText display;

    // Calculations
    protected Operation operation   = Operation.NONE;
    protected int       storedValue = 0;

    // Error state tracking
    protected boolean isInErrorState = false;

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        super.onCreateView( inflater, container, savedInstanceState );

        layout = inflater.inflate( R.layout.display, container, false );

        configureDisplay();

        return layout;
    }

    private void configureDisplay()
    {
        display = (EditText) layout.findViewById( R.id.display );
        clearDisplayedValue();
    }

    public void clearDisplayedValue()
    {
        setDisplay( getResources().getString( R.string.EMPTY_STRING ) );
    }

    public void setDisplay( String result )
    {
        display.setText( result );
    }

    public String getValue()
    {
        return display.getText().toString();
    }

    public boolean isDisplayingOperation()
    {
        String value = getValue();
        return value.equals( getString( R.string.plus ) ) ||
               value.equals( getString( R.string.minus ) ) ||
               value.equals( getString( R.string.multiply ) ) ||
               value.equals( getString( R.string.divide ) ) ||
               value.equals( getString( R.string.modulo ) );
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getApp().getBus().register( this );
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getApp().getBus().unregister( this );
    }

    /**
     * Handles the selection of a number.
     *
     * @param number - number entered
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
            setDisplay( number.getNumber() );
            operation = Operation.NUMBER;
        }
        else if ( isDisplayingOperation() )
        {
            setDisplay( number.getNumber() );
        }
        else
        {
            appendNumber( number.getNumber() );
            operation = Operation.NUMBER;
        }
    }

    private void clearErroredState()
    {
        clearDisplayedValue();
        isInErrorState = false;
    }

    private void appendNumber( String number )
    {
        setDisplay( getValue() + number );
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
               !isDisplayingOperation() &&
               operation != Operation.NONE &&
               !TextUtils.isEmpty( getValue() );
    }

    private void performCalculation()
    {
        // Attempt to obtain integer from current display
        int value;
        try
        {
            value = Integer.parseInt( getValue() );
        }
        catch ( NumberFormatException e )
        {
            startErrorState( R.string.ERROR, getValue() );
            return;
        }

        // Perform the operation
        switch ( operation )
        {
            case PLUS:
                setDisplay( String.valueOf( storedValue + value ) );
                break;

            case MINUS:
                setDisplay( String.valueOf( storedValue - value ) );
                break;

            case MULTIPLY:
                setDisplay( String.valueOf( storedValue * value ) );
                break;

            case DIVIDE:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    setDisplay( String.valueOf( storedValue / value ) );
                }
                break;

            case MODULO:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    setDisplay( String.valueOf( storedValue % value ) );
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
        setDisplay( getString( stringId ) );
        isInErrorState = true;
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
        clearDisplayedValue();
        storedValue = 0;
        operation = Operation.NONE;
        isInErrorState = false;
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
             TextUtils.isEmpty( getValue() ) )
        {
            return;
        }

        // Store value
        if ( !isDisplayingOperation() )
        {
            storeDisplayedValue();
        }

        // Store operation & update display. Having the operator
        // update outside of the above condition means that
        // the user can change their mind.
        // NOTE: Can enter error state when storing the value
        this.operation = operationEvent.getOperator();
        if ( !isInErrorState )
        {
            setDisplay( operation.getOperationString() );
        }
    }

    private void storeDisplayedValue()
    {
        try
        {
            storedValue = Integer.parseInt( getValue() );
        }
        catch ( NumberFormatException e )
        {
            startErrorState( R.string.ERROR );
        }
    }
}
