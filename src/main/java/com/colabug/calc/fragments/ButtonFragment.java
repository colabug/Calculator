package com.colabug.calc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.colabug.calc.R;
import com.colabug.calc.events.button.ClearButtonEvent;
import com.colabug.calc.events.button.EqualsButtonEvent;
import com.colabug.calc.events.button.NumberButtonEvent;
import com.colabug.calc.events.button.OperatorButtonEvent;
import com.colabug.calc.model.Operation;

public class ButtonFragment extends BaseFragment
{
    private View layout;

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        super.onCreateView( inflater, container, savedInstanceState );

        layout = inflater.inflate( R.layout.calculator, container, false );

        configureNumberKeys();
        configureOperationKeys();
        configureEqualsKey();
        configureClearKey();

        return layout;
    }

    private void configureNumberKeys()
    {
        configureNumberKey( R.id.key1 );
        configureNumberKey( R.id.key2 );
        configureNumberKey( R.id.key3 );
        configureNumberKey( R.id.key4 );
        configureNumberKey( R.id.key5 );
        configureNumberKey( R.id.key6 );
        configureNumberKey( R.id.key7 );
        configureNumberKey( R.id.key8 );
        configureNumberKey( R.id.key9 );
        configureNumberKey( R.id.key0 );
    }

    private void configureNumberKey( int id )
    {
        layout.findViewById( id )
              .setOnClickListener( createNumberOnClickListener() );
    }

    private View.OnClickListener createNumberOnClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                String number = ( (Button) view ).getText().toString();
                postToBus( new NumberButtonEvent( number ) );
            }
        };
    }

    private void configureOperationKeys()
    {
        configureOperationKey( R.id.plus, Operation.PLUS );
        configureOperationKey( R.id.minus, Operation.MINUS );
        configureOperationKey( R.id.multiply, Operation.MULTIPLY );
        configureOperationKey( R.id.divide, Operation.DIVIDE );
        configureOperationKey( R.id.modulo, Operation.MODULO );
    }

    private void configureOperationKey( int id, Operation operation )
    {
        layout.findViewById( id )
              .setOnClickListener( createOperationClickListener( operation ) );
    }

    private View.OnClickListener createOperationClickListener( final Operation operation )
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                postToBus( new OperatorButtonEvent( operation ) );
            }
        };
    }

    private void configureEqualsKey()
    {
        layout.findViewById( R.id.equal ).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                postToBus( new EqualsButtonEvent() );
            }
        } );
    }

    private void configureClearKey()
    {
        View clear = layout.findViewById( R.id.clear );
        clear.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                postToBus( new ClearButtonEvent() );
            }
        } );
    }
}
