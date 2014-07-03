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

import java.math.BigInteger;

/**
 * Logic engine to track the state of the calculator and it's operations.
 *
 * @since 1.0
 */
public class CalculatorStateFragment extends BaseFragment
{
    // Key state tracking
    protected KeyEvent lastKeyEvent = KeyEvent.NONE;

    // Calculations
    protected int storedValue = 0;
    protected Operation operation = Operation.NONE;

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
     * @param event - number entered
     */
    @Subscribe
    public void onNumberSelected( NumberEnteredEvent event )
    {
        processNumberEvent( event.getNumber() );
        lastKeyEvent = KeyEvent.NUMBER;
    }

    protected void processNumberEvent( String number )
    {
        clearErrorIfExists();

        if ( shouldSetDisplayToNumber() )
        {
            setDisplayToNumber( number );
        }
        else
        {
            appendDisplayedNumber( number );
        }
    }

    private void clearErrorIfExists()
    {
        if ( isInErrorState )
        {
            postToBus( new ResetDisplayEvent() );
            isInErrorState = false;
        }
    }

    private boolean shouldSetDisplayToNumber()
    {
        return lastKeyEvent == KeyEvent.NONE ||
               lastKeyEvent == KeyEvent.OPERATION ||
               lastKeyEvent == KeyEvent.EQUAL;
    }

    private void appendDisplayedNumber( String number )
    {
        postToBus( new AppendDisplayEvent( number ) );
    }

    private void setDisplayToNumber( String number )
    {
        postToBus( new SetDisplayValueEvent( number ) );
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
        // TODO: Do I still need to track the state of the display here?
        //       Or will Operation.NON take care of that?
        //       TextUtils.isEmpty( getValueString() ) )
        if ( isInErrorState || lastKeyEvent == KeyEvent.NONE )
        {
            return;
        }

        // Store value
        // TODO: How will I break out the logic of fetching display value?
        //        if ( !operationSelectedLast() )
        // If the last operation was a number, then store it for the calculation
        if ( lastKeyEvent == KeyEvent.NUMBER )
        {
            storeDisplayedValue();
        }

        // Store operation & update display. Having the operator
        // update outside of the above condition means that
        // the user can change their mind.
        // NOTE: Can enter error state when storing the value
        lastKeyEvent = KeyEvent.OPERATION;
        this.operation = operationEvent.getOperator();
        if ( !isInErrorState )
        {
            postToBus( new SetDisplayValueEvent( operation.getOperationString() ) );
        }
    }

    // TODO: How do I get this value? I'm currently using a fire and
    // forget model.
    private void storeDisplayedValue()
    {
        try
        {
            // storedValue = Integer.parseInt( getValueString() );
            storedValue = 2147483647;
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

        lastKeyEvent = KeyEvent.EQUAL;
    }

    private boolean shouldPerformCalculation()
    {
        return !isInErrorState &&
               lastKeyEvent != KeyEvent.OPERATION &&
               operation != Operation.NONE;
    }

    private void performCalculation()
    {
        // Attempt to obtain integer from current display
        int value;
        try
        {
            // TODO: Fix this
            // value = Integer.parseInt( getValueString() );
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
        calculateAndUpdateDisplay( value );

        storedValue = 0;
        operation = Operation.NONE;
    }

    // TODO: Have this return a value and update the display separately?
    private void calculateAndUpdateDisplay( int value )
    {
        switch ( operation )
        {
            case PLUS:
                postCalculatedValue( String.valueOf( storedValue + value ) );
                break;

            case MINUS:
                postCalculatedValue( String.valueOf( storedValue - value ) );
                break;

            case MULTIPLY:
                int result;
                try
                {
                    result = safeMultiply( storedValue, value );
                }
                catch ( CalculatorOverflowException e )
                {
                    startErrorState( R.string.OVERFLOW );
                    return;
                }

                postCalculatedValue( String.valueOf( result ) );
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
    }

    private void startErrorState( int stringId )
    {
        postToBus( new ErrorDisplayEvent( getString( stringId ) ) );
        isInErrorState = true;
        operation = Operation.NONE;
        lastKeyEvent = KeyEvent.NONE;
    }

    private int safeMultiply( int left, int right ) throws CalculatorOverflowException
    {
        BigInteger bigC = BigInteger.valueOf( left )
                                    .multiply( BigInteger.valueOf( right ) );
        if ( bigC.compareTo( BigInteger.valueOf( Integer.MAX_VALUE ) ) > 0 )
        {
            throw new CalculatorOverflowException();
        }

        return bigC.intValue();
    }

    private void postCalculatedValue( String value )
    {
        postToBus( new SetDisplayValueEvent( value ) );
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
        lastKeyEvent = KeyEvent.NONE;
        operation = Operation.NONE;

        postToBus( new ResetDisplayEvent() );
        storedValue = 0;
        isInErrorState = false;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getApp().getBus().unregister( this );
    }
}
