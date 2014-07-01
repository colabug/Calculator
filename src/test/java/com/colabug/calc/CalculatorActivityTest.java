package com.colabug.calc;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.colabug.calc.support.Assert.assertViewIsVisible;
import static com.colabug.calc.support.ResourceLocator.getResourceString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.robolectric.Robolectric.buildActivity;

@RunWith (RobolectricTestRunner.class)

public class CalculatorActivityTest
{
    private static final String EMPTY_STRING               = "";
    private static final String STARTING_VALUE             = "123";
    private static final String ADDITION_FINAL_VALUE       = "131";
    private static final String SUBTRACTION_FINAL_VALUE    = "115";
    private static final String MULTIPLICATION_FINAL_VALUE = "984";
    private static final String DIVISION_FINAL_VALUE       = "15";
    private static final String MODULO_FINAL_VALUE         = "3";

    // Activity and Fragments
    private CalculatorActivity activity;
    private ButtonFragment     buttons;
    private DisplayFragment    display;

    private Button clear;

    @Before
    public void setUp() throws Exception
    {
        activity = buildActivity( CalculatorActivity.class ).create()
        .start()
        .resume()
        .get();

        // Fragments
        buttons = (ButtonFragment) activity.getSupportFragmentManager().findFragmentById(
        R.id.calculator_fragment );
        display = (DisplayFragment) activity.getSupportFragmentManager().findFragmentById(
        R.id.display_fragment );

        // Views
//        clear = (Button) getViewById( R.id.clear );
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

    // TODO: Figure out how to support these in the new world order
    @Test
    public void plusShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        //        plus.performClick();
        //        assertTrue( activity.getIsInErrorState() );
    }

    @Test
    public void minusShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        //        minus.performClick();
        //        assertTrue( activity.getIsInErrorState() );
    }

    @Test
    public void multiplyShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        //        multiply.performClick();
        //        assertTrue( activity.getIsInErrorState() );
    }

    @Test
    public void divideShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        //        divide.performClick();
        //        assertTrue( activity.getIsInErrorState() );
    }

    @Test
    public void moduloShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        //        modulo.performClick();
        //        assertTrue( activity.getIsInErrorState() );
    }

    // Ignore equals when nothing or just an operation is entered

    @Test
    public void equalShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        //        equal.performClick();
        //        assertTrue( activity.getIsInErrorState() );
    }

    @Test
    public void enteringLargeSecondOperandShouldDisplayError() throws Exception
    {
        startErrorStateFromCalculation();
        assertThat( getDisplayValue(),
                    equalTo( getResourceString( R.string.ERROR ) ) );
    }

    @Test
    public void enteringLargeSecondOperandShouldStartErrorState() throws
                                                                  Exception
    {
        startErrorStateFromCalculation();
        //        assertTrue( activity.getIsInErrorState() );
    }

    private void startErrorStateFromCalculation()
    {
        //        multiply.performClick();
        enterNumberGreaterThanLargestInt();
        //        equal.performClick();
    }

    @Test
    public void numberShouldClearErrorStateAndUpdateDisplay() throws Exception
    {
        divideByZero();
        //        key1.performClick();
        //        assertFalse( activity.getIsInErrorState() );
        //        assertThat( getDisplayValue(), equalTo( key1.getText() ) );
    }

    @Test
    public void whenDividingByZeroEqualShouldDisplayNaN() throws Exception
    {
        divideByZero();
        assertThat( getDisplayValue(),
                    equalTo( getResourceString( R.string.NAN ) ) );
    }

    @Test
    public void whenDividingByZeroEqualShouldStartErrorState() throws Exception
    {
        divideByZero();
        //        assertTrue( activity.getIsInErrorState() );
    }

    private void divideByZero()
    {
        //        divide.performClick();
        //        key0.performClick();
        //        equal.performClick();
    }

    @Test
    public void whenModuloingByZeroEqualShouldDisplayNaN() throws Exception
    {
        moduloByZero();
        assertThat( getDisplayValue(),
                    equalTo( getResourceString( R.string.NAN ) ) );
    }

    @Test
    public void whenModuloingByZeroEqualShouldStartErrorState() throws Exception
    {
        moduloByZero();
        //        assertTrue( activity.getIsInErrorState() );
    }

    private void moduloByZero()
    {
        //        modulo.performClick();
        //        key0.performClick();
        //        equal.performClick();
    }

    @Test
    public void clearShouldClearErrorState() throws Exception
    {
        divideByZero();
        clear.performClick();
        //        assertFalse( activity.getIsInErrorState() );
    }

    @Test
    public void enteringLargerThanGreatestIntShouldResultInErrorState() throws
                                                                        Exception
    {
        enterNumberGreaterThanLargestInt();
        //        multiply.performClick();
        //        assertTrue( activity.getIsInErrorState() );
    }

    @Test
    public void enteringLargerThanGreatestIntShouldDisplayError() throws
                                                                  Exception
    {
        enterNumberGreaterThanLargestInt();
        //        multiply.performClick();
        assertThat( getDisplayValue(),
                    equalTo( getResourceString( R.string.ERROR ) ) );
    }

    private void enterNumberGreaterThanLargestInt()
    {
        clear.performClick();
        //        key2.performClick();
        //        key1.performClick();
        //        key4.performClick();
        //        key7.performClick();
        //        key4.performClick();
        //        key8.performClick();
        //        key3.performClick();
        //        key6.performClick();
        //        key5.performClick();
        //        key9.performClick();
    }

    @Test
    public void multipleOperationPressesShouldUpdateOperationTypeToLastType() throws
                                                                              Exception
    {
        //        divide.performClick();
        //        multiply.performClick();
        //        divide.performClick();
        //        plus.performClick();
        //        assertThat( activity.getOperation(),
        //                    equalTo( Operation.PLUS ) );
    }

    @Test
    public void addingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        //        plus.performClick();
        assertThat( getDisplayValue(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void subtractingBeforeANumberShouldNotUpdateDisplay() throws
                                                                 Exception
    {
        clear.performClick();
        //        minus.performClick();
        assertThat( getDisplayValue(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void multiplyingBeforeANumberShouldNotUpdateDisplay() throws
                                                                 Exception
    {
        clear.performClick();
        //        multiply.performClick();
        assertThat( getDisplayValue(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void dividingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        //        divide.performClick();
        assertThat( getDisplayValue(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalShouldShowCurrentlyDisplayedValue() throws Exception
    {
        //        equal.performClick();
        //        equal.performClick();
        //        equal.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), equalTo( STARTING_VALUE ) );
    }

    @Test
    public void equalBeforeSecondOperandShouldWaitForNumber() throws Exception
    {
        //        plus.performClick();
        //        equal.performClick();
        //        assertThat( getDisplayValue(), equalTo( plus.getText() ) );
    }

    @Test
    public void equalWithBlankScreenShouldNotUpdateDisplay() throws Exception
    {
        display.setDisplay( "" );
        //        equal.performClick();
        assertThat( getDisplayValue(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenAdding() throws Exception
    {
        addEightToStartingValue();
        assertThat( getDisplayValue(), equalTo( ADDITION_FINAL_VALUE ) );
    }

    private void addEightToStartingValue()
    {
        //        plus.performClick();
        //        key8.performClick();
        //        equal.performClick();
    }

    @Test
    public void equalShouldGiveCorrectResultWhenSubtracting() throws Exception
    {
        //        minus.performClick();
        //        key8.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), equalTo( SUBTRACTION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenMultiplying() throws Exception
    {
        //        multiply.performClick();
        //        key8.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), equalTo( MULTIPLICATION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenDividing() throws Exception
    {
        //        divide.performClick();
        //        key8.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), equalTo( DIVISION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenUsingModulo() throws Exception
    {
        //        modulo.performClick();
        //        key8.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), equalTo( MODULO_FINAL_VALUE ) );
    }

    @Test
    public void postEqualsNewNumbersShouldUpdateDisplay() throws Exception
    {
        addEightToStartingValue();
        //        key1.performClick();
        //        assertThat( getDisplayValue(), equalTo( key1.getText() ) );
    }

    @Test
    public void shouldHaveClearKey() throws Exception
    {
        assertViewIsVisible( clear );
    }

    @Test
    public void clearShouldClearDisplay() throws Exception
    {
        clear.performClick();
        assertThat( getDisplayValue(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void postClearNewNumbersShouldUpdateDisplay() throws Exception
    {
        clear.performClick();
        //        key1.performClick();
        //        assertThat( getDisplayValue(), equalTo( key1.getText() ) );
    }

    @Test
    public void clearShouldClearStoredValue() throws Exception
    {
//        activity.setStoredValue( 123 );
        clear.performClick();
//        assertThat( activity.getStoredValue(), equalTo( "0" ) );
    }

//    private View getViewById( int id )
//    {
        //        return activity.findViewById( id );
//    }

    private String getDisplayValue()
    {
        return display.getValue();
    }

    //    class TestCalculatorActivity extends CalculatorActivity
    //    {
    //        public String getStoredValue()
    //        {
    //            return String.valueOf( storedValue );
    //        }
    //
    //        public Operation getOperation()
    //        {
    //            return operation;
    //        }
    //
    //        public void setStoredValue( int storedValue )
    //        {
    //            this.storedValue = storedValue;
    //        }
    //
    //        public boolean getIsInErrorState()
    //        {
    //            return isInErrorState;
    //        }
    //    }
}
