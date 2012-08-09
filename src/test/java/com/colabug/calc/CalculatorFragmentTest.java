package com.colabug.calc;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.colabug.calc.support.CalculatorTestRunner;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.colabug.calc.support.CalculatorTestRunner.assertViewIsVisible;
import static com.colabug.calc.support.CalculatorTestRunner.getResourceString;
import static com.colabug.calc.support.CalculatorTestRunner.startFragment;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * {@link CalculatorFragment} test suite.
 *
 * @since 1.0
 */
@RunWith (CalculatorTestRunner.class)

public class CalculatorFragmentTest
{
    private static final String EMPTY_STRING = "";

    private static final String STARTING_VALUE             = "123";
    private static final String ADDITION_FINAL_VALUE       = "131";
    private static final String SUBTRACTION_FINAL_VALUE    = "115";
    private static final String MULTIPLICATION_FINAL_VALUE = "984";
    private static final String DIVISION_FINAL_VALUE       = "15";
    private static final String MODULO_FINAL_VALUE         = "3";
    private static final String MAX_INT_VALUE              = "2147483647";

    private TestCalculatorFragment calculatorFragment;

    private Button key1;
    private Button key2;
    private Button key3;
    private Button key4;
    private Button key5;
    private Button key6;
    private Button key7;
    private Button key8;
    private Button key9;
    private Button key0;

    // Operations
    private Button plus;
    private Button minus;
    private Button multiply;
    private Button divide;
    private Button modulo;
    private Button equal;
    private Button clear;

    private EditText display;

    @Before
    public void setUp() throws Exception
    {
        // Start fragment
        calculatorFragment = new TestCalculatorFragment();
        startFragment( calculatorFragment );

        // Display
        display = (EditText) getViewById( R.id.display );

        // Number keys
        key1 = (Button) getViewById( R.id.key1 );
        key2 = (Button) getViewById( R.id.key2 );
        key3 = (Button) getViewById( R.id.key3 );
        key4 = (Button) getViewById( R.id.key4 );
        key5 = (Button) getViewById( R.id.key5 );
        key6 = (Button) getViewById( R.id.key6 );
        key7 = (Button) getViewById( R.id.key7 );
        key8 = (Button) getViewById( R.id.key8 );
        key9 = (Button) getViewById( R.id.key9 );
        key0 = (Button) getViewById( R.id.key0 );

        // Operations
        plus = (Button) getViewById( R.id.plus );
        minus = (Button) getViewById( R.id.minus );
        multiply = (Button) getViewById( R.id.multiply );
        divide = (Button) getViewById( R.id.divide );
        modulo = (Button) getViewById( R.id.modulo );
        equal = (Button) getViewById( R.id.equal );
        clear = (Button) getViewById( R.id.clear );

        enterDefaultValueWithKeys();
    }

    private void enterDefaultValueWithKeys()
    {
        key1.performClick();
        key2.performClick();
        key3.performClick();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( calculatorFragment );
    }

    @Test
    public void shouldHaveDisplay() throws Exception
    {
        assertViewIsVisible( display );
    }

    @Test
    public void displayShouldLimitEntryTo10Characters() throws Exception
    {
        clear.performClick();
        enterLargestInt();
        key0.performClick();
        assertThat( getDisplayText(), equalTo( MAX_INT_VALUE ) );
    }

    private void enterLargestInt()
    {
        key2.performClick();
        key1.performClick();
        key4.performClick();
        key7.performClick();
        key4.performClick();
        key8.performClick();
        key3.performClick();
        key6.performClick();
        key4.performClick();
        key7.performClick();
    }

    @Test
    public void shouldShowDefaultDisplay() throws Exception
    {
        assertThat( getDisplayText(), equalTo( STARTING_VALUE ) );
    }

    @Test
    public void shouldHave1Key() throws Exception
    {
        assertViewIsVisible( key1 );
    }

    @Test
    public void oneShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key1 ) );
    }

    @Test
    public void oneShouldUpdateDisplay() throws Exception
    {
        key1.performClick();
        String expected = STARTING_VALUE + key1.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave2Key() throws Exception
    {
        assertViewIsVisible( key2 );
    }

    @Test
    public void twoShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key2 ) );
    }

    @Test
    public void twoShouldUpdateDisplay() throws Exception
    {
        key2.performClick();
        String expected = STARTING_VALUE + key2.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave3Key() throws Exception
    {
        assertViewIsVisible( key3 );
    }

    @Test
    public void threeShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key3 ) );
    }

    @Test
    public void threeShouldUpdateDisplay() throws Exception
    {
        key3.performClick();
        String expected = STARTING_VALUE + key3.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave4Key() throws Exception
    {
        assertViewIsVisible( key4 );
    }

    @Test
    public void fourShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key4 ) );
    }

    @Test
    public void fourShouldUpdateDisplay() throws Exception
    {
        key4.performClick();
        String expected = STARTING_VALUE + key4.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave5Key() throws Exception
    {
        assertViewIsVisible( key5 );
    }

    @Test
    public void fiveShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key5 ) );
    }

    @Test
    public void fiveShouldUpdateDisplay() throws Exception
    {
        key5.performClick();
        String expected = STARTING_VALUE + key5.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave6Key() throws Exception
    {
        assertViewIsVisible( key6 );
    }

    @Test
    public void sixShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key6 ) );
    }

    @Test
    public void sixShouldUpdateDisplay() throws Exception
    {
        key6.performClick();
        String expected = STARTING_VALUE + key6.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave7Key() throws Exception
    {
        assertViewIsVisible( key7 );
    }

    @Test
    public void sevenShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key7 ) );
    }

    @Test
    public void sevenShouldUpdateDisplay() throws Exception
    {
        key7.performClick();
        String expected = STARTING_VALUE + key7.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave8Key() throws Exception
    {
        assertViewIsVisible( key8 );
    }

    @Test
    public void eightShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key8 ) );
    }

    @Test
    public void eightShouldUpdateDisplay() throws Exception
    {
        key8.performClick();
        String expected = STARTING_VALUE + key8.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave9Key() throws Exception
    {
        assertViewIsVisible( key9 );
    }

    @Test
    public void nineShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key9 ) );
    }

    @Test
    public void nineShouldUpdateDisplay() throws Exception
    {
        key9.performClick();
        String expected = STARTING_VALUE + key9.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave0Key() throws Exception
    {
        assertViewIsVisible( key0 );
    }

    @Test
    public void zeroShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key0 ) );
    }

    @Test
    public void zeroShouldUpdateDisplay() throws Exception
    {
        key0.performClick();
        String expected = STARTING_VALUE + key0.getText();
        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void enteringLargerThanGreatestIntShouldResultInErrorState() throws Exception
    {
        enterNumberGreaterThanLargestInt();
        multiply.performClick();
        assertTrue( calculatorFragment.isInErrorState );
    }

    @Test
    public void enteringLargerThanGreatestIntShouldDisplayError() throws Exception
    {
        enterNumberGreaterThanLargestInt();
        multiply.performClick();
        assertThat( getDisplayText(),
                    equalTo( getResourceString( R.string.ERROR ) ) );
    }

    private void enterNumberGreaterThanLargestInt()
    {
        clear.performClick();
        key2.performClick();
        key1.performClick();
        key4.performClick();
        key7.performClick();
        key4.performClick();
        key8.performClick();
        key3.performClick();
        key6.performClick();
        key5.performClick();
        key9.performClick();
    }

    @Test
    public void shouldHavePlusKey() throws Exception
    {
        assertViewIsVisible( plus );
    }

    @Test
    public void plusShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( plus ) );
    }

    @Test
    public void plusShouldStoreTheDisplayedValue() throws Exception
    {
        plus.performClick();
        assertThat( calculatorFragment.getStoredValue(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void plusShouldStoreOperationType() throws Exception
    {
        plus.performClick();
        assertThat( calculatorFragment.getOperation(),
                    equalTo( Operation.PLUS ) );
    }

    @Test
    public void plusShouldUpdateDisplayCharacter() throws Exception
    {
        plus.performClick();
        assertThat( getDisplayText(), equalTo( OperationString.PLUS ) );
    }

    @Test
    public void addingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        plus.performClick();
        assertThat( getDisplayText(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void shouldHaveMinusKey() throws Exception
    {
        assertViewIsVisible( minus );
    }

    @Test
    public void minusShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( minus ) );
    }

    @Test
    public void minusShouldStoreTheDisplayedValue() throws Exception
    {
        minus.performClick();
        assertThat( calculatorFragment.getStoredValue(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void minusShouldStoreOperationType() throws Exception
    {
        minus.performClick();
        assertThat( calculatorFragment.getOperation(),
                    equalTo( Operation.MINUS ) );
    }

    @Test
    public void minusShouldUpdateDisplayCharacter() throws Exception
    {
        minus.performClick();
        assertThat( getDisplayText(), equalTo( OperationString.MINUS ) );
    }

    @Test
    public void subtractingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        minus.performClick();
        assertThat( getDisplayText(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void shouldHaveMultiplyKey() throws Exception
    {
        assertViewIsVisible( multiply );
    }

    @Test
    public void multiplyShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( multiply ) );
    }

    @Test
    public void multiplyShouldStoreTheDisplayedValue() throws Exception
    {
        multiply.performClick();
        assertThat( calculatorFragment.getStoredValue(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void multiplyShouldStoreOperationType() throws Exception
    {
        multiply.performClick();
        assertThat( calculatorFragment.getOperation(),
                    equalTo( Operation.MULTIPLY ) );
    }

    @Test
    public void multiplyShouldUpdateDisplayCharacter() throws Exception
    {
        multiply.performClick();
        assertThat( getDisplayText(), equalTo( OperationString.MULTIPLY ) );
    }

    @Test
    public void multiplyingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        multiply.performClick();
        assertThat( getDisplayText(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void shouldHaveDivideKey() throws Exception
    {
        assertViewIsVisible( divide );
    }

    @Test
    public void divideShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( divide ) );
    }

    @Test
    public void divideShouldStoreTheDisplayedValue() throws Exception
    {
        divide.performClick();
        assertThat( calculatorFragment.getStoredValue(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void divideShouldStoreOperationType() throws Exception
    {
        divide.performClick();
        assertThat( calculatorFragment.getOperation(),
                    equalTo( Operation.DIVIDE ) );
    }

    @Test
    public void divideShouldUpdateDisplayCharacter() throws Exception
    {
        divide.performClick();
        assertThat( getDisplayText(), equalTo( OperationString.DIVIDE ) );
    }

    @Test
    public void dividingBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        divide.performClick();
        assertThat( getDisplayText(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void shouldHaveModuloKey() throws Exception
    {
        assertViewIsVisible( modulo );
    }

    @Test
    public void moduloShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( modulo ) );
    }

    @Test
    public void moduloShouldStoreTheDisplayedValue() throws Exception
    {
        modulo.performClick();
        assertThat( calculatorFragment.getStoredValue(),
                    equalTo( STARTING_VALUE ) );
    }

    @Test
    public void moduloShouldStoreOperationType() throws Exception
    {
        modulo.performClick();
        assertThat( calculatorFragment.getOperation(),
                    equalTo( Operation.MODULO ) );
    }

    @Test
    public void moduloShouldUpdateDisplayCharacter() throws Exception
    {
        modulo.performClick();
        assertThat( getDisplayText(), equalTo( OperationString.MODULO ) );
    }

    @Test
    public void multipleOperationPressesShouldUpdateOperationTypeToLastType() throws Exception
    {
        divide.performClick();
        multiply.performClick();
        divide.performClick();
        plus.performClick();
        assertThat( calculatorFragment.getOperation(),
                    equalTo( Operation.PLUS ) );
    }

    @Test
    public void shouldHaveEqualKey() throws Exception
    {
        assertViewIsVisible( equal );
    }

    @Test
    public void equalShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( equal ) );
    }

    @Test
    public void equalShouldShowCurrentlyDisplayedValue() throws Exception
    {
        equal.performClick();
        equal.performClick();
        equal.performClick();
        equal.performClick();
        assertThat( getDisplayText(), equalTo( STARTING_VALUE ) );
    }

    @Test
    public void equalBeforeANumberShouldNotUpdateDisplay() throws Exception
    {
        clear.performClick();
        equal.performClick();
        assertThat( getDisplayText(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalBeforeSecondOperandShouldWaitForNumber() throws Exception
    {
        plus.performClick();
        equal.performClick();
        assertThat( getDisplayText(), equalTo( plus.getText() ) );
    }

    @Test
    public void equalWithBlankScreenShouldNotUpdateDisplay() throws Exception
    {
        display.setText( "" );
        equal.performClick();
        assertThat( getDisplayText(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenAdding() throws Exception
    {
        addEightToStartingValue();
        assertThat( getDisplayText(), equalTo( ADDITION_FINAL_VALUE ) );
    }

    private void addEightToStartingValue()
    {
        plus.performClick();
        key8.performClick();
        equal.performClick();
    }

    @Test
    public void equalShouldGiveCorrectResultWhenSubtracting() throws Exception
    {
        minus.performClick();
        key8.performClick();
        equal.performClick();
        assertThat( getDisplayText(), equalTo( SUBTRACTION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenMultiplying() throws Exception
    {
        multiply.performClick();
        key8.performClick();
        equal.performClick();
        assertThat( getDisplayText(), equalTo( MULTIPLICATION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenDividing() throws Exception
    {
        divide.performClick();
        key8.performClick();
        equal.performClick();
        assertThat( getDisplayText(), equalTo( DIVISION_FINAL_VALUE ) );
    }

    @Test
    public void equalShouldGiveCorrectResultWhenUsingModulo() throws Exception
    {
        modulo.performClick();
        key8.performClick();
        equal.performClick();
        assertThat( getDisplayText(), equalTo( MODULO_FINAL_VALUE ) );
    }

    @Test
    public void whenDividingByZeroEqualShouldDisplayNaN() throws Exception
    {
        divideByZero();
        assertThat( getDisplayText(),
                    equalTo( getResourceString( R.string.NAN ) ) );
    }

    @Test
    public void whenDividingByZeroEqualShouldStartErrorState() throws Exception
    {
        divideByZero();
        assertTrue( calculatorFragment.isInErrorState );
    }

    private void divideByZero()
    {
        divide.performClick();
        key0.performClick();
        equal.performClick();
    }

    @Test
    public void whenModuloingByZeroEqualShouldDisplayNaN() throws Exception
    {
        moduloByZero();
        assertThat( getDisplayText(),
                    equalTo( getResourceString( R.string.NAN ) ) );
    }

    @Test
    public void whenModuloingByZeroEqualShouldStartErrorState() throws Exception
    {
        moduloByZero();
        assertTrue( calculatorFragment.isInErrorState );
    }

    private void moduloByZero()
    {
        modulo.performClick();
        key0.performClick();
        equal.performClick();
    }

    @Test
    public void postEqualsNewNumbersShouldUpdateDisplay() throws Exception
    {
        addEightToStartingValue();
        key1.performClick();
        assertThat( getDisplayText(), equalTo( key1.getText() ) );
    }

    @Test
    public void plusShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        plus.performClick();
        assertTrue( calculatorFragment.isInErrorState );
    }

    @Test
    public void minusShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        minus.performClick();
        assertTrue( calculatorFragment.isInErrorState );
    }

    @Test
    public void multiplyShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        multiply.performClick();
        assertTrue( calculatorFragment.isInErrorState );
    }

    @Test
    public void divideShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        divide.performClick();
        assertTrue( calculatorFragment.isInErrorState );
    }

    @Test
    public void moduloShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        modulo.performClick();
        assertTrue( calculatorFragment.isInErrorState );
    }

    @Test
    public void equalShouldNotClearErrorState() throws Exception
    {
        divideByZero();
        equal.performClick();
        assertTrue( calculatorFragment.isInErrorState );
    }

    @Test
    public void enteringLargeSecondOperandShouldDisplayError() throws Exception
    {
        startErrorStateFromCalculation();
        assertThat( getDisplayText(),
                    equalTo( getResourceString( R.string.ERROR ) ) );
    }

    @Test
    public void enteringLargeSecondOperandShouldStartErrorState() throws Exception
    {
        startErrorStateFromCalculation();
        assertTrue( calculatorFragment.isInErrorState );
    }

    private void startErrorStateFromCalculation()
    {
        multiply.performClick();
        enterNumberGreaterThanLargestInt();
        equal.performClick();
    }

    // Ignore equals when nothing or just an operation is entered


    @Test
    public void numberShouldClearErrorStateAndUpdateDisplay() throws Exception
    {
        divideByZero();
        key1.performClick();
        assertFalse( calculatorFragment.isInErrorState );
        assertThat( getDisplayText(), equalTo( key1.getText() ) );
    }

    @Test
    public void shouldHaveClearKey() throws Exception
    {
        assertViewIsVisible( clear );
    }

    @Test
    public void clearShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( clear ) );
    }

    @Test
    public void clearShouldClearDisplay() throws Exception
    {
        clear.performClick();
        assertThat( getDisplayText(), equalTo( EMPTY_STRING ) );
    }

    @Test
    public void postClearNewNumbersShouldUpdateDisplay() throws Exception
    {
        clear.performClick();
        key1.performClick();
        assertThat( getDisplayText(), equalTo( key1.getText() ) );
    }

    @Test
    public void clearShouldClearStoredValue() throws Exception
    {
        calculatorFragment.setStoredValue( 123 );
        clear.performClick();
        assertThat( calculatorFragment.getStoredValue(), equalTo( "0" ) );
    }

    @Test
    public void clearShouldClearErrorState() throws Exception
    {
        divideByZero();
        clear.performClick();
        assertFalse( calculatorFragment.getErrorState() );
    }

    private View getViewById( int id )
    {
        return calculatorFragment.getView().findViewById( id );
    }

    private String getDisplayText()
    {
        return display.getText().toString();
    }

    private View.OnClickListener getViewOnClickListener( View view )
    {
        ShadowView shadowView = Robolectric.shadowOf( view );
        return shadowView.getOnClickListener();
    }

    class TestCalculatorFragment extends CalculatorFragment
    {
        public String getStoredValue()
        {
            return String.valueOf( storedValue );
        }

        public Operation getOperation()
        {
            return operation;
        }

        public void setStoredValue( int storedValue )
        {
            this.storedValue = storedValue;
        }

        public boolean getErrorState()
        {
            return isInErrorState;
        }
    }
}
