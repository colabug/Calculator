package com.colabug.calc;

import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
import static org.robolectric.Robolectric.buildActivity;

@RunWith (RobolectricTestRunner.class)

public class CalculatorActivityTest
{
    private CalculatorActivity activity;
    private ButtonFragment     buttons;
    private DisplayFragment    display;

    @Before
    public void setUp() throws Exception
    {
        activity = buildActivity( CalculatorActivity.class ).create()
                                                            .start()
                                                            .resume()
                                                            .get();

        buttons = (ButtonFragment) getFragmentById( R.id.calculator_fragment );
        display = (DisplayFragment) getFragmentById( R.id.display_fragment );
    }

    private Fragment getFragmentById( int id )
    {
        return activity.getSupportFragmentManager().findFragmentById( id );
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveCalculatorFragment() throws Exception
    {
        assertNotNull( buttons );
    }

    @Test
    public void shouldHaveDisplay() throws Exception
    {
        assertNotNull( display );
    }
}
