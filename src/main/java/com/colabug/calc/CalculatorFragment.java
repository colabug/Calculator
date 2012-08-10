package com.colabug.calc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorFragment extends Fragment
{
    private static final String TAG = CalculatorFragment.class.getSimpleName();

    private View     layout;
    private EditText display;

    // Calculations
    protected int       storedValue = 0;
    protected Operation operation   = Operation.NONE;

    // Error state tracking
    protected boolean isInErrorState = false;

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        super.onCreateView( inflater, container, savedInstanceState );

        layout = inflater.inflate( R.layout.calculator, container, false );

        configureDisplay();
        configureNumberKeys();
        configureMathOperationKeys();
        configureEqualsKey();
        configureClearKey();

        return layout;
    }

    private void configureDisplay()
    {
        display = (EditText) layout.findViewById( R.id.display );
        clearDisplayedValue();
    }

    private void clearDisplayedValue()
    {
        setDisplay( getResources().getString( R.string.EMPTY_STRING ) );
    }

    private void configureNumberKeys()
    {
        View key1 = layout.findViewById( R.id.key1 );
        key1.setOnClickListener( createNumberOnClickListener() );

        View key2 = layout.findViewById( R.id.key2 );
        key2.setOnClickListener( createNumberOnClickListener() );

        View key3 = layout.findViewById( R.id.key3 );
        key3.setOnClickListener( createNumberOnClickListener() );

        View key4 = layout.findViewById( R.id.key4 );
        key4.setOnClickListener( createNumberOnClickListener() );

        View key5 = layout.findViewById( R.id.key5 );
        key5.setOnClickListener( createNumberOnClickListener() );

        View key6 = layout.findViewById( R.id.key6 );
        key6.setOnClickListener( createNumberOnClickListener() );

        View key7 = layout.findViewById( R.id.key7 );
        key7.setOnClickListener( createNumberOnClickListener() );

        View key8 = layout.findViewById( R.id.key8 );
        key8.setOnClickListener( createNumberOnClickListener() );

        View key9 = layout.findViewById( R.id.key9 );
        key9.setOnClickListener( createNumberOnClickListener() );

        View key0 = layout.findViewById( R.id.key0 );
        key0.setOnClickListener( createNumberOnClickListener() );
    }

    private View.OnClickListener createNumberOnClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                String number = ( (Button) view ).getText().toString();
                if ( isInErrorState )
                {
                    clearDisplayedValue();
                    isInErrorState = false;
                }

                if ( operation == Operation.NONE || operation == Operation.EQUAL )
                {
                    setDisplay( number );
                    operation = Operation.NUMBER;
                }
                else if ( isDisplayingOperation() )
                {
                    setDisplay( number );
                }
                else
                {
                    appendNumber( number );
                }
            }
        };
    }

    private boolean isDisplayingOperation()
    {
        String currentDisplay = getCurrentDisplayString();
        return currentDisplay.equals( getActivity().getString( R.string.plus     ) ) ||
               currentDisplay.equals( getActivity().getString( R.string.minus    ) ) ||
               currentDisplay.equals( getActivity().getString( R.string.multiply ) ) ||
               currentDisplay.equals( getActivity().getString( R.string.divide   ) ) ||
               currentDisplay.equals( getActivity().getString( R.string.modulo   ) );
    }

    private void appendNumber( String number )
    {
        setDisplay( getCurrentDisplayString() + number );
    }

    private String getCurrentDisplayString()
    {
        return display.getText().toString();
    }

    private void configureMathOperationKeys()
    {
        configurePlusKey();
        configureMinusKey();
        configureMultiplyKey();
        configureDivideKey();
        configureModuloKey();
    }

    private void configurePlusKey()
    {
        View plus = layout.findViewById( R.id.plus );
        plus.setOnClickListener( createOperationClickListener( Operation.PLUS ) );
    }

    private void configureMinusKey()
    {
        View minus = layout.findViewById( R.id.minus );
        minus.setOnClickListener( createOperationClickListener( Operation.MINUS ) );
    }

    private void configureMultiplyKey()
    {
        View multiply = layout.findViewById( R.id.multiply );
        multiply.setOnClickListener( createOperationClickListener( Operation.MULTIPLY ) );
    }

    private void configureDivideKey()
    {
        View divide = layout.findViewById( R.id.divide );
        divide.setOnClickListener( createOperationClickListener( Operation.DIVIDE ) );
    }

    private void configureModuloKey()
    {
        View modulo = layout.findViewById( R.id.modulo );
        modulo.setOnClickListener( createOperationClickListener( Operation.MODULO ) );
    }

    private View.OnClickListener createOperationClickListener( final Operation op )
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                // Ignore clicks when in an error state, when no number
                // entered, or when no operation has been set
                if ( isInErrorState ||
                     operation == Operation.NONE ||
                     TextUtils.isEmpty( display.getText() ))
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
                if (!isInErrorState) {
                    operation = op;
                    setDisplay( ( (Button) view ).getText() );
                }
            }
        };
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

    private void startErrorState( int stringId )
    {
        startErrorState( stringId, String.valueOf( storedValue ) );
    }

    // NOTE: Robolectric doesn't currently support the fragment getString()
    //       functionality. I submitted a pull request here:
    //       https://github.com/pivotal/robolectric/pull/300
    private void startErrorState( int stringId, String number )
    {
        Log.d( TAG, "Can't format number = " + number );
        setDisplay( getActivity().getString( stringId ) );
        isInErrorState = true;
    }

    private void configureEqualsKey()
    {
        View equal = layout.findViewById( R.id.equal );
        equal.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                // They must have entered a number
                if ( shouldPerformCalculation() )
                {
                    performCalculation();
                }
            }
        } );
    }

    private boolean shouldPerformCalculation()
    {
        return !isInErrorState &&
               !isDisplayingOperation() &&
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
                setDisplay( storedValue + value );
                break;

            case MINUS:
                setDisplay( storedValue - value );
                break;

            case MULTIPLY:
                setDisplay( storedValue * value );
                break;

            case DIVIDE:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    setDisplay( storedValue / value );
                }
                break;

            case MODULO:
                if ( willEquateToNan( value ) )
                {
                    startNanState();
                }
                else
                {
                    setDisplay( storedValue % value );
                }

                break;
        }

        storedValue = 0;
        operation = Operation.EQUAL;
    }

    private boolean willEquateToNan( int value )
    {
        return value == 0;
    }

    private void startNanState()
    {
        startErrorState( R.string.NAN );
    }

    private void setDisplay( Object result )
    {
        display.setText( String.valueOf( result ) );
    }

    private void configureClearKey()
    {
        View clear = layout.findViewById( R.id.clear );
        clear.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                clearDisplayedValue();
                storedValue = 0;
                operation = Operation.NONE;
                isInErrorState = false;
            }
        } );
    }
}
