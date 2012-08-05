package com.colabug.calc;

import com.colabug.calc.support.CalculatorTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(CalculatorTestRunner.class)

public class CalculatorActivityTest {

    private CalculatorActivity calculatorActivity;

    @Before
    public void setUp() throws Exception {
        calculatorActivity = new CalculatorActivity();
        calculatorActivity.onCreate(null);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(calculatorActivity);
    }

    @Test
    public void shouldHaveCalculatorFragment() throws Exception {
        assertNotNull(calculatorActivity.getSupportFragmentManager().findFragmentById(R.id.calculator_fragment));
    }
}
