package com.colabug.calc;

import android.view.View;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.colabug.calc.support.Assert.assertViewIsVisible;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * {@link ButtonFragment} test suite.
 *
 * @since 1.0
 */
@RunWith (RobolectricTestRunner.class)

public class DisplayFragmentTest
{
    private static final String MAX_INT_VALUE = "2147483647";

    private DisplayFragment displayFragment;

    private EditText display;

    @Before
    public void setUp() throws Exception
    {
        displayFragment = new DisplayFragment();
        startFragment( displayFragment );

        display = (EditText) findViewById( R.id.display );
        enterDefaultValueWithKeys();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( displayFragment );
    }

    @Test
    public void shouldHaveDisplay() throws Exception
    {
        assertViewIsVisible( display );
    }

    private void enterDefaultValueWithKeys()
    {
//        key1.performClick();
//        key2.performClick();
//        key3.performClick();
    }

    @Test
    public void displayShouldLimitEntryTo10Characters() throws Exception
    {
//        clear.performClick();
//        enterLargestInt();
//        key0.performClick();
//        assertThat( getDisplayText(), equalTo( MAX_INT_VALUE ) );
    }

    private void enterLargestInt()
    {
//        key2.performClick();
//        key1.performClick();
//        key4.performClick();
//        key7.performClick();
//        key4.performClick();
//        key8.performClick();
//        key3.performClick();
//        key6.performClick();
//        key4.performClick();
//        key7.performClick();
    }

    @Test
    public void shouldShowDefaultDisplay() throws Exception
    {
//        assertThat( getDisplayText(), equalTo( STARTING_VALUE ) );
    }

    @Test
    public void plusShouldStoreTheDisplayedValue() throws Exception
    {
//        plus.performClick();
//        assertThat( calculatorFragment.getStoredValue(),
//                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void plusShouldStoreOperationType() throws Exception
    {
//        plus.performClick();
//        assertThat( calculatorFragment.getOperation(),
//                    equalTo( Operation.PLUS ) );
    }

    @Test
    public void plusShouldUpdateDisplayCharacter() throws Exception
    {
//        plus.performClick();
//        assertThat( getDisplayText(),
//                    equalTo( getResourceString( R.string.plus ) ) );
    }

    @Test
    public void minusShouldStoreTheDisplayedValue() throws Exception
    {
//        minus.performClick();
//        assertThat( calculatorFragment.getStoredValue(),
//                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void minusShouldStoreOperationType() throws Exception
    {
//        minus.performClick();
//        assertThat( calculatorFragment.getOperation(),
//                    equalTo( Operation.MINUS ) );
    }

    @Test
    public void minusShouldUpdateDisplayCharacter() throws Exception
    {
//        minus.performClick();
//        assertThat( getDisplayText(),
//                    equalTo( getResourceString( R.string.minus ) ) );
    }

    @Test
    public void multiplyShouldStoreTheDisplayedValue() throws Exception
    {
//        multiply.performClick();
//        assertThat( calculatorFragment.getStoredValue(),
//                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void multiplyShouldStoreOperationType() throws Exception
    {
//        multiply.performClick();
//        assertThat( calculatorFragment.getOperation(),
//                    equalTo( Operation.MULTIPLY ) );
    }

    @Test
    public void multiplyShouldUpdateDisplayCharacter() throws Exception
    {
//        multiply.performClick();
//        assertThat( getDisplayText(),
//                    equalTo( getResourceString( R.string.multiply ) ) );
    }

    @Test
    public void divideShouldStoreTheDisplayedValue() throws Exception
    {
//        divide.performClick();
//        assertThat( calculatorFragment.getStoredValue(),
//                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void divideShouldStoreOperationType() throws Exception
    {
//        divide.performClick();
//        assertThat( calculatorFragment.getOperation(),
//                    equalTo( Operation.DIVIDE ) );
    }

    @Test
    public void divideShouldUpdateDisplayCharacter() throws Exception
    {
//        divide.performClick();
//        assertThat( getDisplayText(),
//                    equalTo( getResourceString( R.string.divide ) ) );
    }


    @Test
    public void moduloShouldStoreTheDisplayedValue() throws Exception
    {
//        modulo.performClick();
//        assertThat( calculatorFragment.getStoredValue(),
//                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void moduloShouldStoreOperationType() throws Exception
    {
//        modulo.performClick();
//        assertThat( calculatorFragment.getOperation(),
//                    equalTo( Operation.MODULO ) );
    }

    @Test
    public void moduloShouldUpdateDisplayCharacter() throws Exception
    {
//        modulo.performClick();
//        assertThat( getDisplayText(),
//                    equalTo( getResourceString( R.string.modulo ) ) );
    }

    private View findViewById( int id )
    {
        return displayFragment.getView().findViewById( id );
    }
}
