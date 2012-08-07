package com.colabug.calc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.colabug.calc.OperationString.getOperationString;

public class CalculatorFragment extends Fragment
{
    private static final String TAG = ClassFormatError.class.getSimpleName();

    private View layout;

    private TextView display;

    protected int       storedValue;
    protected Operation operation;

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
        display = (TextView) layout.findViewById( R.id.display );
        display.setText( "" );
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
                String number = ((Button) view).getText().toString();

                if ( displayingOperation() ) {
                    display.setText( number );
                } else {
                    appendNumber( number );
                }
            }
        };
    }

    private boolean displayingOperation()
    {
        String currentDisplay = display.getText().toString();
        return currentDisplay.equals( OperationString.PLUS ) ||
               currentDisplay.equals( OperationString.MINUS ) ||
               currentDisplay.equals( OperationString.MULTIPLY ) ||
               currentDisplay.equals( OperationString.DIVIDE );
    }

    private void appendNumber( String number )
    {
        display.setText( display.getText().toString() + number );
    }

    private void configureMathOperationKeys()
    {
        configurePlusKey();
        configureMinusKey();
        configureMultiplyKey();
        configureDivideKey();
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

    private View.OnClickListener createOperationClickListener( final Operation op )
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                storeDisplayedValue();
                operation = op;
                display.setText( getOperationString( operation ) );
            }
        };
    }

    private void storeDisplayedValue()
    {
        storedValue = Integer.parseInt( display.getText().toString() );
    }

    private void configureEqualsKey()
    {
        View equal = layout.findViewById( R.id.equal );
        equal.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                performCalculation( Integer.parseInt( display.getText().toString() ) );
            }
        } );
    }

    private void performCalculation( int value )
    {
        int result = 0;
        switch ( operation )
        {
            case MULTIPLY:
                result = storedValue * value;
                break;

            default:
                Log.d( TAG, "Unknown operation" );
                break;
        }

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
                display.setText( "" );
                storedValue = 0;
            }
        } );
    }
}
