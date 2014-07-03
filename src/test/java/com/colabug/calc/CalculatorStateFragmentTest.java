package com.colabug.calc;

import android.widget.Button;
import org.hamcrest.CoreMatchers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.colabug.calc.support.ResourceLocator.getResourceString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * @since 1.0
 */
@RunWith (RobolectricTestRunner.class)

public class CalculatorStateFragmentTest
{
    private static final String EMPTY_STRING = "";

    private static final String STARTING_VALUE             = "123";
    private static final String ADDITION_FINAL_VALUE       = "131";
    private static final String SUBTRACTION_FINAL_VALUE    = "115";
    private static final String MULTIPLICATION_FINAL_VALUE = "984";
    private static final String DIVISION_FINAL_VALUE       = "15";
    private static final String MODULO_FINAL_VALUE         = "3";

    private CalculatorStateFragment calculatorState;
    private Button                  clear;

    @Before
    public void setUp() throws Exception
    {
        // Start fragment
        calculatorState = new CalculatorStateFragment();
        startFragment( calculatorState );
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( calculatorState );
    }

    @Test
    public void plusShouldStoreTheDisplayedValue() throws Exception
    {
        dispatchAdditionEvent();
        assertThat( getDisplayValue(), equalTo( STARTING_VALUE ) );
    }

    private void dispatchAdditionEvent()
    {
        //        calculatorState.dispatchOperation( Operation.PLUS );
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
        //        assertThat( calculatorState.getStoredValue(),
        //                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void minusShouldStoreOperationType() throws Exception
    {
        //        minus.performClick();
        //        assertThat( calculatorState.getOperation(),
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
        //        assertThat( calculatorState.getStoredValue(),
        //                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void multiplyShouldStoreOperationType() throws Exception
    {
        //        multiply.performClick();
        //        assertThat( calculatorState.getOperation(),
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
        //        assertThat( calculatorState.getStoredValue(),
        //                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void divideShouldStoreOperationType() throws Exception
    {
        //        divide.performClick();
        //        assertThat( calculatorState.getOperation(),
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
        //        assertThat( calculatorState.getStoredValue(),
        //                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void moduloShouldStoreOperationType() throws Exception
    {
        //        modulo.performClick();
        //        assertThat( calculatorState.getOperation(),
        //                    equalTo( Operation.MODULO ) );
    }

    @Test
    public void moduloShouldUpdateDisplayCharacter() throws Exception
    {
        //        modulo.performClick();
        //        assertThat( getDisplayText(),
        //                    equalTo( getResourceString( R.string.modulo ) ) );
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
                    CoreMatchers.equalTo( getResourceString( R.string.ERROR ) ) );
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
                    CoreMatchers.equalTo( getResourceString( R.string.NAN ) ) );
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
                    CoreMatchers.equalTo( getResourceString( R.string.NAN ) ) );
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
                    CoreMatchers.equalTo( getResourceString( R.string.ERROR ) ) );
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
        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void subtractingBeforeANumberShouldNotUpdateDisplay() throws
                                                                 Exception
    {
        clear.performClick();
        //        minus.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void multiplyingBeforeANumberShouldNotUpdateDisplay() throws
                                                                 Exception
    {
        clear.performClick();
        //        multiply.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void dividingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        //        divide.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalShouldShowCurrentlyDisplayedValue() throws Exception
    {
        //        equal.performClick();
        //        equal.performClick();
        //        equal.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo( STARTING_VALUE ) );
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
        //        display.setDisplay( "" );
        //        equal.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenAdding() throws Exception
    {
        addEightToStartingValue();
        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        ADDITION_FINAL_VALUE ) );
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
        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        SUBTRACTION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenMultiplying() throws Exception
    {
        //        multiply.performClick();
        //        key8.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        MULTIPLICATION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenDividing() throws Exception
    {
        //        divide.performClick();
        //        key8.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        DIVISION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenUsingModulo() throws Exception
    {
        //        modulo.performClick();
        //        key8.performClick();
        //        equal.performClick();
        assertThat( getDisplayValue(),
                    CoreMatchers.equalTo( MODULO_FINAL_VALUE ) );
    }

    @Test
    public void postEqualsNewNumbersShouldUpdateDisplay() throws Exception
    {
        addEightToStartingValue();
        //        key1.performClick();
        //        assertThat( getDisplayValue(), equalTo( key1.getText() ) );
    }

    @Test
    public void clearShouldClearDisplay() throws Exception
    {
        clear.performClick();
        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    private String getDisplayValue()
    {
//        return calculatorState.getValueString();
        return "";
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
//        calculatorState.setValue( 123 );
//        clear.performClick();
//        assertThat( calculatorState.getValueString(), equalTo( "0" ) );
    }
}
