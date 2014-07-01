package com.colabug.calc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
import static org.robolectric.Robolectric.buildActivity;

@RunWith (RobolectricTestRunner.class)

public class CalculatorActivityTest
{
    private CalculatorActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = buildActivity( CalculatorActivity.class ).create()
                                                            .start()
                                                            .resume()
                                                            .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveCalculatorFragment() throws Exception
    {
        assertNotNull( activity.getSupportFragmentManager().findFragmentById( R.id.calculator_fragment ) );
    }
}
