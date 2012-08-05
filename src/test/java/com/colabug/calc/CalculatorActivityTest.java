package com.colabug.calc;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)

public class CalculatorActivityTest {
    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(new CalculatorActivity());
    }
}
