package com.colabug.calc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colabug.calc.CalculatorActivity;
import com.colabug.calc.exceptions.CalculatorOverflowException;
import com.colabug.calc.R;
import com.colabug.calc.events.button.ClearButtonEvent;
import com.colabug.calc.events.button.EqualsButtonEvent;
import com.colabug.calc.events.button.NumberButtonEvent;
import com.colabug.calc.events.button.OperatorButtonEvent;
import com.colabug.calc.events.display.DisplayAppendEvent;
import com.colabug.calc.events.display.DisplayErrorEvent;
import com.colabug.calc.events.display.DisplayResetEvent;
import com.colabug.calc.events.display.DisplaySetValueEvent;
import com.colabug.calc.model.KeyEvent;
import com.colabug.calc.model.Operation;

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
    protected int       storedValue = 0;
    protected Operation operation   = Operation.NONE;

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

    /**
     * Handles the selection of a number.
     *
     * @param event - number entered
     */
    @Subscribe
    public void onNumberSelected( NumberButtonEvent event )
    {
        processNumberEvent( event.getNumber() );
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

        lastKeyEvent = KeyEvent.NUMBER;
    }

    private void clearErrorIfExists()
    {
        if ( isInErrorState )
        {
            postToBus( new DisplayResetEvent() );
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
        postToBus( new DisplayAppendEvent( number ) );
    }

    private void setDisplayToNumber( String number )
    {
        postToBus( new DisplaySetValueEvent( number ) );
    }

    /**
     * Handles the selection of an operator.
     *
     * @param operationEvent - operation selected
     */
    @Subscribe
    public void onOperatorSelected( OperatorButtonEvent operationEvent )
    {
        processOperation( operationEvent.getOperator() );
    }

    protected void processOperation( Operation operation )
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

        // If the last operation was a number, then store it for the
        // calculation
        if ( lastKeyEvent == KeyEvent.NUMBER )
        {
            storeDisplayedValue();
        }

        // Store operation & update display. Having the operator update
        // outside of the above condition means that the user can change
        // their mind.
        // NOTE: Can enter error state when storing the value
        lastKeyEvent = KeyEvent.OPERATION;
        this.operation = operation;
        if ( !isInErrorState )
        {
            postToBus( new DisplaySetValueEvent( this.operation.getOperationString() ) );
        }
    }

    protected void storeDisplayedValue()
    {
        try
        {
            storedValue = Integer.parseInt( getDisplayValueFromActivity() );
        }
        catch ( NumberFormatException e )
        {
            startErrorState( R.string.ERROR );
        }
    }

    protected String getDisplayValueFromActivity()
    {
        return ( (CalculatorActivity) getActivity() ).getDisplayValue();
    }

    /**
     * Handles the selection of the equals key.
     */
    @Subscribe
    public void onEqualSelected( EqualsButtonEvent equals )
    {
        processEqual();
    }

    protected void processEqual()
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
            value = Integer.parseInt( getDisplayValueFromActivity() );
        }
        catch ( NumberFormatException e )
        {
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

    protected void startErrorState( int stringId )
    {
        postToBus( new DisplayErrorEvent( getString( stringId ) ) );
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
        postToBus( new DisplaySetValueEvent( value ) );
    }

    private boolean willEquateToNan( int value )
    {
        return value == 0;
    }

    private void startNanState()
    {
        postToBus( new DisplayErrorEvent( getString( R.string.NAN ) ) );
    }

    /**
     * Handles the selection of the clear button.
     *
     * @param clearButtonEvent - clear selected
     */
    @Subscribe
    public void onClearSelected( ClearButtonEvent clearButtonEvent )
    {
        clearCalculatorState();
    }

    protected void clearCalculatorState()
    {
        lastKeyEvent = KeyEvent.NONE;
        operation = Operation.NONE;

        postToBus( new DisplayResetEvent() );
        storedValue = 0;
        isInErrorState = false;
    }
}
