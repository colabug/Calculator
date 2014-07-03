package com.colabug.calc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colabug.calc.events.AppendDisplayEvent;
import com.colabug.calc.events.ErrorDisplayEvent;
import com.colabug.calc.events.ResetDisplayEvent;
import com.colabug.calc.events.SetDisplayValueEvent;

import com.squareup.otto.Subscribe;

/**
 * Logic engine to track the state of the calculator and it's operations.
 *
 * @since 1.0
 */
public class CalculatorStateFragment extends BaseFragment
{
    // Calculations
    protected Operation operation   = Operation.NONE;
    protected int       storedValue = 0;

    // Error state tracking
    protected boolean isInErrorState = false;

    public static CalculatorStateFragment newInstance()
    {
        return new CalculatorStateFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        return null; // No view provided
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getApp().getBus().register( this );
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
            postToBus( new ResetDisplayEvent() );
            isInErrorState = false;
        }

        if ( operation == Operation.NONE ||
             operation == Operation.EQUAL )
        {
            postToBus( new SetDisplayValueEvent( number.getNumber() ) );
            operation = Operation.NUMBER;
        }
        // TODO: I shouldn't know about the display fragment, so this
        // logic needs to be distributed somewhere.
        else if ( isDisplayingOperation() )
        {
            postToBus( new SetDisplayValueEvent( number.getNumber() ) );
        }
        else
        {
            postToBus( new AppendDisplayEvent( number.getNumber() ) );
            operation = Operation.NUMBER;
        }
    }

        // TODO: Shouldn't rely on the string, should instead
    //       rely on the current operation? Do I need another state to track this?
    public boolean isDisplayingOperation()
    {
        //            String value = getValue();
        //            return value.equals( getString( R.string.plus ) ) ||
        //                   value.equals( getString( R.string.minus ) ) ||
        //                   value.equals( getString( R.string.multiply ) ) ||
        //                   value.equals( getString( R.string.divide ) ) ||
        //                   value.equals( getString( R.string.modulo ) );
        return operation == Operation.PLUS     ||
               operation == Operation.MINUS    ||
               operation == Operation.DIVIDE   ||
               operation == Operation.MULTIPLY ||
               operation == Operation.MODULO;
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
        //        return !isInErrorState &&
        //               !isDisplayingOperation() &&
        //               operation != Operation.NONE &&
        //               !TextUtils.isEmpty( getValueString() );
        return !isInErrorState &&
               !isDisplayingOperation() &&
               operation != Operation.NONE;
    }

    private void performCalculation()
    {
        // Attempt to obtain integer from current display
        int value;
        try
        {
            // TODO: Fix this
            //            value = Integer.parseInt( getValueString() );
            value = 25;
        }
        catch ( NumberFormatException e )
        {
            // TODO: What was the old break down in states for?
            //            startErrorState( R.string.ERROR, getValueString() );
            startErrorState( R.string.ERROR );
            return;
        }

        // Perform the operation
        switch ( operation )
        {
            case PLUS:
                postCalculatedValue( String.valueOf( storedValue + value ) );
                break;

            case MINUS:
                postCalculatedValue( String.valueOf( storedValue - value ) );
                break;

            case MULTIPLY:
                postCalculatedValue( String.valueOf( storedValue * value ) );
                break;

            case DIVIDE:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    postCalculatedValue( String.valueOf(
                    storedValue / value ) );
                }
                break;

            case MODULO:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    postCalculatedValue( String.valueOf(
                    storedValue % value ) );
                }

                break;
        }

        storedValue = 0;
        operation = Operation.EQUAL;
    }

    private void postCalculatedValue( String value )
    {
        postToBus( new SetDisplayValueEvent( value ) );
    }

    // NOTE: Robolectric doesn't currently support the fragment getString()
    //       functionality. I submitted a pull request here:
    //       https://github.com/pivotal/robolectric/pull/300
    // TODO: Maybe a lie now, try it!
    private void startErrorState( int stringId )
    {
        //        startErrorState( stringId, String.valueOf( storedValue ) );
        //        Log.d( TAG, "Can't format number = " + number );
        postToBus( new ErrorDisplayEvent( getString( stringId ) ) );
        isInErrorState = true;
    }

    private boolean willEquateToNan( int value )
    {
        return value == 0;
    }

    private void startNanState()
    {
        postToBus( new ErrorDisplayEvent( getString( R.string.NAN ) ) );
    }

    /**
     * Handles the selection of the clear button.
     *
     * @param clearEvent - clear selected
     */
    @Subscribe
    public void onClearSelected( ClearEvent clearEvent )
    {
        postToBus( new ResetDisplayEvent() );
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
        //        if ( isInErrorState ||
        //             operation == Operation.NONE ||
        //             TextUtils.isEmpty( getValueString() ) )
        if ( isInErrorState ||
             operation == Operation.NONE )
        {
            return;
        }

        // Store value
        // TODO: How will I break out the logic of fetching display value?
//        if ( !isDisplayingOperation() )
        // If the last operation was a number, then store it for the calculation
        if ( operation == Operation.NUMBER )
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
            postToBus( new SetDisplayValueEvent( operation.getOperationString() ) );
        }
    }

    // TODO: Would I use the produce model here?
    private void storeDisplayedValue()
    {
        try
        {
            //            storedValue = Integer.parseInt( getValueString() );
            storedValue = 25;
        }
        catch ( NumberFormatException e )
        {
            startErrorState( R.string.ERROR );
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getApp().getBus().unregister( this );
    }
}
