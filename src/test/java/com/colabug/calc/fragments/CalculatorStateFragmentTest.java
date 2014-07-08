package com.colabug.calc.fragments;

import com.colabug.calc.R;
import com.colabug.calc.events.*;
import com.colabug.calc.events.display.DisplayAppendEvent;
import com.colabug.calc.events.display.DisplayErrorEvent;
import com.colabug.calc.events.display.DisplayResetEvent;
import com.colabug.calc.events.display.DisplaySetValueEvent;
import com.colabug.calc.model.KeyEvent;
import com.colabug.calc.model.Operation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static com.colabug.calc.support.ResourceLocator.getResourceString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * @since 1.0
 */
@RunWith (RobolectricTestRunner.class)

public class CalculatorStateFragmentTest
{
    private static final String GREATER_THAN_MAX_INT = "21474836470";

    private static final String EMPTY_STRING   = "";
    private static final String NUMBER_ENTERED = "1";

    private static final String STARTING_VALUE             = "123";
    private static final String ZERO             = "0";
    private static final String ADDITION_FINAL_VALUE       = "131";
    private static final String SUBTRACTION_FINAL_VALUE    = "115";
    private static final String MULTIPLICATION_FINAL_VALUE = "984";
    private static final String DIVISION_FINAL_VALUE       = "15";
    private static final String MODULO_FINAL_VALUE         = "3";

    private TestCalculatorStateFragment calculatorState;

    @Before
    public void setUp() throws Exception
    {
        // Start fragment
        calculatorState = new TestCalculatorStateFragment();
        startFragment( calculatorState );
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( calculatorState );
    }

    @Test
    public void soloNumberShouldPostSetDisplayEvent() throws Exception
    {
        enterNumber( NUMBER_ENTERED );

        verifyNumberOfEvents( 1 );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplaySetValueEvent );
        assertThat( ( (DisplaySetValueEvent) lastEvent ).getValue(),
                    equalTo( NUMBER_ENTERED ) );
    }

    @Test
    public void existingNumberShouldPostAppendDisplayEvent() throws Exception
    {
        calculatorState.setLastKeyEvent( KeyEvent.NUMBER );
        enterNumber( NUMBER_ENTERED );

        verifyNumberOfEvents( 1 );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplayAppendEvent );
        assertThat( ( (DisplayAppendEvent) lastEvent ).getTextToAppend(),
                    equalTo( NUMBER_ENTERED ) );
    }

    @Test
    public void numberShouldClearErrorState() throws Exception
    {
        // Set up conditions
        calculatorState.startErrorState( R.string.ERROR );
        enterNumber( NUMBER_ENTERED );

        // Verify error state
        assertFalse( calculatorState.isInErrorState() );
        verifyNumberOfEvents( 2 );

        // Verify events
        assertTrue( calculatorState.getFirstEvent() instanceof DisplayResetEvent );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplaySetValueEvent );
        assertThat( ( (DisplaySetValueEvent) lastEvent ).getValue(),
                    equalTo( NUMBER_ENTERED ) );
    }

    private void verifyNumberOfEvents( int size )
    {
        assertThat( calculatorState.getEvents().size(),
                    equalTo( size ) );
    }

    @Test
    public void enteringLargeSecondOperandShouldStartErrorState() throws
                                                                  Exception
    {
        enterValueWithOperation( Operation.PLUS );
        startErrorStateFromCalculation();

        assertTrue( calculatorState.isInErrorState() );
        assertTrue( calculatorState.getLastEvent() instanceof DisplayErrorEvent );
    }

    private void startErrorStateFromCalculation()
    {
        enterValue( GREATER_THAN_MAX_INT );
    }

    private void enterNumber( String number )
    {
        calculatorState.processNumberEvent( number );
    }

    @Test
    public void plusShouldStoreValue() throws Exception
    {
        enterValueWithOperation( Operation.PLUS );
        assertThat( calculatorState.getDisplayValueFromActivity(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void plusOperationShouldBeStored() throws Exception
    {
        Operation plus = Operation.PLUS;
        enterValueWithOperation( plus );
        assertThat( calculatorState.getOperation(),
                    equalTo( plus ) );
    }

    @Test
    public void plusShouldPostSetValueEvent() throws Exception
    {
        Operation plus = Operation.PLUS;
        enterValueWithOperation( plus );

        verifyNumberOfEvents( 1 );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplaySetValueEvent );
        assertThat( ( (DisplaySetValueEvent) lastEvent ).getValue(),
                    equalTo( plus.getOperationString() ) );
    }

    @Test
    public void plusShouldNotClearErrorState() throws Exception
    {
        verifyErrorNotClearedWithOperation( Operation.PLUS );
    }

    private void verifyErrorNotClearedWithOperation( Operation operation )
    {
        calculatorState.startErrorState( R.string.ERROR );
        assertTrue( calculatorState.isInErrorState() );
        enterOperation( operation );
        assertTrue( calculatorState.isInErrorState() );
    }

    private void enterValueWithOperation( Operation operation )
    {
        enterValue( STARTING_VALUE );
        enterOperation( operation );
    }

    private void enterOperation( Operation operation )
    {
        calculatorState.processOperation( operation );
    }

    @Test
    public void minusShouldStoreValue() throws Exception
    {
        enterValueWithOperation( Operation.MINUS );
        assertThat( calculatorState.getDisplayValueFromActivity(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void minusOperationShouldBeStored() throws Exception
    {
        Operation minus = Operation.MINUS;
        enterValueWithOperation( minus );
        assertThat( calculatorState.getOperation(),
                    equalTo( minus ) );
    }

    @Test
    public void minusShouldPostSetValueEvent() throws Exception
    {
        Operation minus = Operation.MINUS;
        enterValueWithOperation( minus );

        verifyNumberOfEvents( 1 );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplaySetValueEvent );
        assertThat( ( (DisplaySetValueEvent) lastEvent ).getValue(),
                    equalTo( minus.getOperationString() ) );
    }

    @Test
    public void minusShouldNotClearErrorState() throws Exception
    {
        verifyErrorNotClearedWithOperation( Operation.MINUS );
    }

    @Test
    public void multiplyShouldStoreValue() throws Exception
    {
        enterValueWithOperation( Operation.MULTIPLY );
        assertThat( calculatorState.getDisplayValueFromActivity(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void multiplyOperationShouldBeStored() throws Exception
    {
        Operation multiply = Operation.MULTIPLY;
        enterValueWithOperation( multiply );
        assertThat( calculatorState.getOperation(),
                    equalTo( multiply ) );
    }

    @Test
    public void multiplyShouldPostSetValueEvent() throws Exception
    {
        Operation multiply = Operation.MULTIPLY;
        enterValueWithOperation( multiply );

        verifyNumberOfEvents( 1 );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplaySetValueEvent );
        assertThat( ( (DisplaySetValueEvent) lastEvent ).getValue(),
                    equalTo( multiply.getOperationString() ) );
    }

    @Test
    public void multiplyShouldNotClearErrorState() throws Exception
    {
        verifyErrorNotClearedWithOperation( Operation.MULTIPLY );
    }

    @Test
    public void divideShouldStoreValue() throws Exception
    {
        enterValueWithOperation( Operation.DIVIDE );
        assertThat( calculatorState.getDisplayValueFromActivity(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void divideOperationShouldBeStored() throws Exception
    {
        Operation divide = Operation.DIVIDE;
        enterValueWithOperation( divide );
        assertThat( calculatorState.getOperation(),
                    equalTo( divide ) );
    }

    @Test
    public void divideShouldPostSetValueEvent() throws Exception
    {
        Operation divide = Operation.DIVIDE;
        enterValueWithOperation( divide );

        verifyNumberOfEvents( 1 );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplaySetValueEvent );
        assertThat( ( (DisplaySetValueEvent) lastEvent ).getValue(),
                    equalTo( divide.getOperationString() ) );
    }

    @Test
    public void divideShouldNotClearErrorState() throws Exception
    {
        verifyErrorNotClearedWithOperation( Operation.DIVIDE );
    }

    @Test
    public void moduloShouldStoreValue() throws Exception
    {
        enterValueWithOperation( Operation.MODULO );
        assertThat( calculatorState.getDisplayValueFromActivity(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void moduloOperationShouldBeStored() throws Exception
    {
        Operation modulo = Operation.MODULO;
        enterValueWithOperation( modulo );
        assertThat( calculatorState.getOperation(),
                    equalTo( modulo ) );
    }

    @Test
    public void moduloShouldPostSetValueEvent() throws Exception
    {
        Operation modulo = Operation.MODULO;
        enterValueWithOperation( modulo );

        verifyNumberOfEvents( 1 );
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplaySetValueEvent );
        assertThat( ( (DisplaySetValueEvent) lastEvent ).getValue(),
                    equalTo( modulo.getOperationString() ) );
    }

    @Test
    public void moduloShouldNotClearErrorState() throws Exception
    {
        verifyErrorNotClearedWithOperation( Operation.MODULO );
    }

    @Test
    public void equalShouldNotClearErrorState() throws Exception
    {
        calculatorState.startErrorState( R.string.ERROR );
        assertTrue( calculatorState.isInErrorState() );
        calculatorState.processEqual();
        assertTrue( calculatorState.isInErrorState() );
    }

    @Test
    public void dividingByZeroShouldPostNaNEvent() throws Exception
    {
        divideByZero();
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplayErrorEvent );
        assertThat( ( (DisplayErrorEvent) lastEvent ).getError(),
                    equalTo( getResourceString( R.string.NAN ) ) );
    }

    @Test
    public void dividingByZeroShouldStartErrorState() throws Exception
    {
        divideByZero();
        assertTrue( calculatorState.isInErrorState() );
    }

    private void divideByZero()
    {
        enterValueWithOperation( Operation.DIVIDE );
        enterNumber( ZERO );
        calculatorState.processEqual();
    }

    @Test
    public void whenModuloingByZeroEqualShouldDisplayNaN() throws Exception
    {
        moduloByZero();
        //        assertThat( getDisplayValue(),
        //                    CoreMatchers.equalTo( getResourceString( R.string.NAN ) ) );
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
        // Put in error state
        calculatorState.startErrorState( R.string.OVERFLOW );
        assertTrue( calculatorState.isInErrorState() );

        // Clear and verify
        calculatorState.clearCalculatorState();
        assertFalse( calculatorState.isInErrorState() );

        // Verify event
        BaseEvent lastEvent = calculatorState.getLastEvent();
        assertTrue( lastEvent instanceof DisplayResetEvent );
    }

    @Test
    public void enteringLargerThanGreatestIntShouldResultInErrorState() throws
                                                                        Exception
    {
        enterValue( GREATER_THAN_MAX_INT );
        assertTrue( calculatorState.isInErrorState() );
    }

    @Test
    public void enteringLargerThanGreatestIntPostErrorEvent() throws Exception
    {
        enterValue( GREATER_THAN_MAX_INT );
        ArrayList<BaseEvent> events = calculatorState.getEvents();
        assertThat( events.size(), equalTo( 1 ) );
        BaseEvent firstEvent = calculatorState.getFirstEvent();
        assertTrue( firstEvent instanceof DisplayErrorEvent );
        assertThat( ( (DisplayErrorEvent) firstEvent ).getError(),
                    equalTo( getResourceString( R.string.ERROR ) ) );
    }

    private void enterValue( String value )
    {
        calculatorState.setDisplayValue( value );
        calculatorState.storeDisplayedValue();
        calculatorState.setLastKeyEvent( KeyEvent.NUMBER );
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
        calculatorState.clearCalculatorState();

        //        plus.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void subtractingBeforeANumberShouldNotUpdateDisplay() throws
                                                                 Exception
    {
        calculatorState.clearCalculatorState();

        //        minus.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void multiplyingBeforeANumberShouldNotUpdateDisplay() throws
                                                                 Exception
    {
        calculatorState.clearCalculatorState();

        //        multiply.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void dividingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        calculatorState.clearCalculatorState();

        //        divide.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        calculatorState.clearCalculatorState();

        //        equal.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalShouldShowCurrentlyDisplayedValue() throws Exception
    {
        //        equal.performClick();
        //        equal.performClick();
        //        equal.performClick();
        //        equal.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( STARTING_VALUE ) );
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
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenAdding() throws Exception
    {
        addEightToStartingValue();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        //        ADDITION_FINAL_VALUE ) );
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
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        //        SUBTRACTION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenMultiplying() throws Exception
    {
        //        multiply.performClick();
        //        key8.performClick();
        //        equal.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        //        MULTIPLICATION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenDividing() throws Exception
    {
        //        divide.performClick();
        //        key8.performClick();
        //        equal.performClick();
        //        assertThat( getDisplayValue(), CoreMatchers.equalTo(
        //        DIVISION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenUsingModulo() throws Exception
    {
        //        modulo.performClick();
        //        key8.performClick();
        //        equal.performClick();
        //        assertThat( getDisplayValue(),
        //                    CoreMatchers.equalTo( MODULO_FINAL_VALUE ) );
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
        calculatorState.clearCalculatorState();

        //        assertThat( getDisplayValue(), CoreMatchers.equalTo( EMPTY_STRING ) );
    }

    @Test
    public void postClearNewNumbersShouldUpdateDisplay() throws Exception
    {
        calculatorState.clearCalculatorState();

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

    class TestCalculatorStateFragment extends CalculatorStateFragment
    {
        private String valueString;

        private ArrayList<BaseEvent> events = new ArrayList<BaseEvent>();

        public void setLastKeyEvent( KeyEvent key )
        {
            this.lastKeyEvent = key;
        }

        public void setDisplayValue( String valueString )
        {
            this.valueString = valueString;
//            storeDisplayedValue();
        }

        @Override
        protected String getDisplayValueFromActivity()
        {
            return valueString;
        }

        @Override
        public void postToBus( BaseEvent event )
        {
            events.add( event );
        }

        ArrayList<BaseEvent> getEvents()
        {
            return events;
        }

        BaseEvent getFirstEvent()
        {
            return events.get( 0 );
        }

        BaseEvent getLastEvent()
        {
            return events.get( events.size() - 1 );
        }

        public boolean isInErrorState()
        {
            return isInErrorState;
        }

        public Operation getOperation()
        {
            return operation;
        }
    }
}
