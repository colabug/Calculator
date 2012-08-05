package com.colabug.calc;

import android.view.View;
import com.colabug.calc.support.CalculatorTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.colabug.calc.support.CalculatorTestRunner.assertViewIsVisible;
import static com.colabug.calc.support.CalculatorTestRunner.startFragment;
import static org.junit.Assert.assertNotNull;

/**
 * {@link CalculatorFragment} test suite.
 *
 * @since 1.0
 */
@RunWith (CalculatorTestRunner.class)

public class CalculatorFragmentTest
{
    private CalculatorFragment calculatorFragment;

    private View key1;
    private View key2;
    private View key3;
    private View key4;
    private View key5;
    private View key6;
    private View key7;
    private View key8;
    private View key9;
    private View key0;
    private View clear;
    private View equal;
    private View display;


    @Before
    public void setUp() throws Exception
    {
        // Start fragment
        calculatorFragment = new CalculatorFragment();
        startFragment( calculatorFragment );

        // Result display
        display = calculatorFragment.getView().findViewById( R.id.display );

        // Number, clear, & equal keys
        key1 = calculatorFragment.getView().findViewById( R.id.key1 );
        key2 = calculatorFragment.getView().findViewById( R.id.key2 );
        key3 = calculatorFragment.getView().findViewById( R.id.key3 );
        key4 = calculatorFragment.getView().findViewById( R.id.key4 );
        key5 = calculatorFragment.getView().findViewById( R.id.key5 );
        key6 = calculatorFragment.getView().findViewById( R.id.key6 );
        key7 = calculatorFragment.getView().findViewById( R.id.key7 );
        key8 = calculatorFragment.getView().findViewById( R.id.key8 );
        key9 = calculatorFragment.getView().findViewById( R.id.key9 );
        key0 = calculatorFragment.getView().findViewById( R.id.key0 );
        clear = calculatorFragment.getView().findViewById( R.id.clear );
        equal = calculatorFragment.getView().findViewById( R.id.equal );
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( calculatorFragment );
    }

    @Test
    public void shouldHaveResultDisplay() throws Exception
    {
        assertViewIsVisible( display );
    }

    @Test
    public void shouldHave1Key() throws Exception
    {
        assertViewIsVisible( key1 );
    }

    @Test
    public void shouldHave2Key() throws Exception
    {
        assertViewIsVisible( key2 );
    }

    @Test
    public void shouldHave3Key() throws Exception
    {
        assertViewIsVisible( key3 );
    }

    @Test
    public void shouldHave4Key() throws Exception
    {
        assertViewIsVisible( key4 );
    }

    @Test
    public void shouldHave5Key() throws Exception
    {
        assertViewIsVisible( key5 );
    }

    @Test
    public void shouldHave6Key() throws Exception
    {
        assertViewIsVisible( key6 );
    }

    @Test
    public void shouldHave7Key() throws Exception
    {
        assertViewIsVisible( key7 );
    }

    @Test
    public void shouldHave8Key() throws Exception
    {
        assertViewIsVisible( key8 );
    }

    @Test
    public void shouldHave9Key() throws Exception
    {
        assertViewIsVisible( key9 );
    }

    @Test
    public void shouldHave0Key() throws Exception
    {
        assertViewIsVisible( key0 );
    }

    @Test
    public void shouldHaveClearKey() throws Exception
    {
        assertViewIsVisible( clear );
    }

    @Test
    public void shouldHaveEqualKey() throws Exception
    {
        assertViewIsVisible( equal );
    }
}
