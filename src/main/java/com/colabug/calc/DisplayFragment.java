package com.colabug.calc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.colabug.calc.events.AppendDisplayEvent;
import com.colabug.calc.events.ErrorDisplayEvent;
import com.colabug.calc.events.ResetDisplayEvent;
import com.colabug.calc.events.SetDisplayValueEvent;

import com.squareup.otto.Subscribe;

/**
 * Dumb display that processes events.
 *
 * @since 1.0
 */
public class DisplayFragment extends BaseFragment
{
    // View
    private   View     layout;
    protected EditText display;

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

    protected void clearDisplayedValue()
    {
        setDisplay( getResources().getString( R.string.EMPTY_STRING ) );
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getApp().getBus().register( this );
    }

    /**
     * Display should be updated.
     *
     * @param event - holds data to change view value
     */
    @Subscribe
    public void onSetDisplayValue( SetDisplayValueEvent event )
    {
        setDisplay( event.getValue() );
    }

    protected void setDisplay( String result )
    {
        display.setText( result );
    }

    /**
     * Display should be appended.
     *
     * @param event - holds data to append
     */
    @Subscribe
    public void onAppendDisplayEvent( AppendDisplayEvent event )
    {
        appendValue( event.getTextToAppend() );
    }

    protected void appendValue( String valueToAppend )
    {
        setDisplay( getValueString() + valueToAppend );
    }

    private String getValueString()
    {
        return display.getText().toString();
    }

    /**
     * Display should be cleared.
     *
     * @param event - clear display
     */
    @Subscribe
    public void onResetDisplay( ResetDisplayEvent event )
    {
        clearDisplayedValue();
    }

    /**
     * Display should show error.
     *
     * @param event - clear display
     */
    @Subscribe
    public void onErrorDisplay( ErrorDisplayEvent event )
    {
        showError( event.getError() );
    }

    protected void showError( String error )
    {
        setDisplay( error );
    }

    @Override
    public void onPause()
    {
        super.onPause();
        getApp().getBus().unregister( this );
    }
}
