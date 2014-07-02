package com.colabug.calc;

import android.view.View;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.colabug.calc.support.Assert.assertViewIsVisible;
import static com.colabug.calc.support.ResourceLocator.getResourceString;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * {@link ButtonFragment} test suite.
 * <p/>
 * TODO: Do I want to teach support fragments too?
 *
 * @since 1.0
 */
@RunWith (RobolectricTestRunner.class)

public class ButtonFragmentTest
{
    private TestButtonFragment buttonFragment;

    // Buttons
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

    @Before
    public void setUp() throws Exception
    {
        // Start fragment
        buttonFragment = new TestButtonFragment();
        startFragment( buttonFragment );

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
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( buttonFragment );
    }

    @Test
    public void shouldHave1Key() throws Exception
    {
        assertViewIsVisible( key1 );
    }

    @Test
    public void oneShouldPostEvent() throws Exception
    {
        key1.performClick();
        verifyNumberButtonEvent( R.string.key1 );
    }

    private void verifyNumberButtonEvent( int id )
    {
        BaseViewEvent event = buttonFragment.getEvent();
        assertTrue( event instanceof NumberEnteredEvent );
        assertThat( ( (NumberEnteredEvent) event ).getNumber(),
                    equalTo( getResourceString( id ) ) );
    }

    @Test
    public void shouldHave2Key() throws Exception
    {
        assertViewIsVisible( key2 );
    }

    @Test
    public void twoShouldPostEvent() throws Exception
    {
        key2.performClick();
        verifyNumberButtonEvent( R.string.key2 );
    }

    @Test
    public void shouldHave3Key() throws Exception
    {
        assertViewIsVisible( key3 );
    }

    @Test
    public void threeShouldPostEvent() throws Exception
    {
        key3.performClick();
        verifyNumberButtonEvent( R.string.key3 );
    }

    @Test
    public void shouldHave4Key() throws Exception
    {
        assertViewIsVisible( key4 );
    }

    @Test
    public void fourShouldPostEvent() throws Exception
    {
        key4.performClick();
        verifyNumberButtonEvent( R.string.key4 );
    }

    @Test
    public void shouldHave5Key() throws Exception
    {
        assertViewIsVisible( key5 );
    }

    @Test
    public void fiveShouldPostEvent() throws Exception
    {
        key5.performClick();
        verifyNumberButtonEvent( R.string.key5 );
    }

    @Test
    public void shouldHave6Key() throws Exception
    {
        assertViewIsVisible( key6 );
    }

    @Test
    public void sixShouldPostEvent() throws Exception
    {
        key6.performClick();
        verifyNumberButtonEvent( R.string.key6 );
    }

    @Test
    public void shouldHave7Key() throws Exception
    {
        assertViewIsVisible( key7 );
    }

    @Test
    public void sevenShouldPostEvent() throws Exception
    {
        key7.performClick();
        verifyNumberButtonEvent( R.string.key7 );
    }

    @Test
    public void shouldHave8Key() throws Exception
    {
        assertViewIsVisible( key8 );
    }

    @Test
    public void eightShouldPostEvent() throws Exception
    {
        key8.performClick();
        verifyNumberButtonEvent( R.string.key8 );
    }

    @Test
    public void shouldHave9Key() throws Exception
    {
        assertViewIsVisible( key9 );
    }

    @Test
    public void nineShouldPostEvent() throws Exception
    {
        key9.performClick();
        verifyNumberButtonEvent( R.string.key9 );
    }

    @Test
    public void shouldHave0Key() throws Exception
    {
        assertViewIsVisible( key0 );
    }

    @Test
    public void zeroShouldPostEvent() throws Exception
    {
        key0.performClick();
        verifyNumberButtonEvent( R.string.key0 );
    }

    @Test
    public void shouldHavePlusKey() throws Exception
    {
        assertViewIsVisible( plus );
    }

    @Test
    public void plusShouldPostEvent() throws Exception
    {
        plus.performClick();
        verifyOperatorEvent( Operation.PLUS );
    }

    private void verifyOperatorEvent( Operation operation )
    {
        BaseViewEvent event = buttonFragment.getEvent();
        assertTrue( event instanceof OperationSelectedEvent );
        assertThat( ( (OperationSelectedEvent) event ).getOperator(),
                    equalTo( operation ) );
    }

    @Test
    public void shouldHaveMinusKey() throws Exception
    {
        assertViewIsVisible( minus );
    }

    @Test
    public void minusShouldPostEvent() throws Exception
    {
        minus.performClick();
        verifyOperatorEvent( Operation.MINUS );
    }

    @Test
    public void shouldHaveMultiplyKey() throws Exception
    {
        assertViewIsVisible( multiply );
    }

    @Test
    public void multiplyShouldPostEvent() throws Exception
    {
        multiply.performClick();
        verifyOperatorEvent( Operation.MULTIPLY );
    }

    @Test
    public void shouldHaveDivideKey() throws Exception
    {
        assertViewIsVisible( divide );
    }

    @Test
    public void divideShouldPostEvent() throws Exception
    {
        divide.performClick();
        verifyOperatorEvent( Operation.DIVIDE );
    }

    @Test
    public void shouldHaveModuloKey() throws Exception
    {
        assertViewIsVisible( modulo );
    }

    @Test
    public void moduloShouldPostEvent() throws Exception
    {
        modulo.performClick();
        verifyOperatorEvent( Operation.MODULO );
    }

    @Test
    public void shouldHaveEqualKey() throws Exception
    {
        assertViewIsVisible( equal );
    }

    @Test
    public void equalsShouldPostEvent() throws Exception
    {
        equal.performClick();
        assertTrue( buttonFragment.getEvent() instanceof EqualsEvent );
    }

    @Test
    public void shouldHaveClearKey() throws Exception
    {
        assertViewIsVisible( clear );
    }

    @Test
    public void clearShouldPostEvent() throws Exception
    {
        clear.performClick();
        assertTrue( buttonFragment.getEvent() instanceof ClearEvent );
    }

    private View getViewById( int id )
    {
        return buttonFragment.getView().findViewById( id );
    }

    class TestButtonFragment extends ButtonFragment
    {
        private BaseViewEvent event;

        @Override
        public void postToBus( BaseViewEvent event )
        {
            this.event = event;
        }

        BaseViewEvent getEvent()
        {
            return event;
        }
    }
}
