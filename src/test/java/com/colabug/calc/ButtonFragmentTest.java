package com.colabug.calc;

import android.view.View;
import android.widget.Button;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.colabug.calc.support.Assert.assertViewIsVisible;
import static org.junit.Assert.*;
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
    private static final String STARTING_VALUE = "123";

    // TODO: Rename
    private ButtonFragment buttonFragment;

    Bus bus;

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

    @Before
    public void setUp() throws Exception
    {
        // Start fragment
        buttonFragment = ButtonFragment.newInstance();
        startFragment( buttonFragment );

        //        reset( bus );

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

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration level.
    @Test
    public void oneShouldFireEvent() throws Exception
    {
        String expected = STARTING_VALUE + key1.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );


//        key1.performClick();
//        Capture<BaseViewEvent> event = new Capture<BaseViewEvent>();
//        bus.post(and(capture(event), isA(NumberEnteredEvent.class)));
//        replay(bus);
//        verify(bus);
    }

    @Test
    public void shouldHave2Key() throws Exception
    {
        assertViewIsVisible( key2 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void twoShouldFireEvent() throws Exception
    {
        key2.performClick();
        String expected = STARTING_VALUE + key2.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave3Key() throws Exception
    {
        assertViewIsVisible( key3 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void threeShouldUpdateDisplay() throws Exception
    {
        key3.performClick();
        String expected = STARTING_VALUE + key3.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave4Key() throws Exception
    {
        assertViewIsVisible( key4 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void fourShouldUpdateDisplay() throws Exception
    {
        key4.performClick();
        String expected = STARTING_VALUE + key4.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave5Key() throws Exception
    {
        assertViewIsVisible( key5 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void fiveShouldUpdateDisplay() throws Exception
    {
        key5.performClick();
        String expected = STARTING_VALUE + key5.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave6Key() throws Exception
    {
        assertViewIsVisible( key6 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void sixShouldUpdateDisplay() throws Exception
    {
        key6.performClick();
        String expected = STARTING_VALUE + key6.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave7Key() throws Exception
    {
        assertViewIsVisible( key7 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void sevenShouldUpdateDisplay() throws Exception
    {
        key7.performClick();
        String expected = STARTING_VALUE + key7.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave8Key() throws Exception
    {
        assertViewIsVisible( key8 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void eightShouldUpdateDisplay() throws Exception
    {
        key8.performClick();
        String expected = STARTING_VALUE + key8.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave9Key() throws Exception
    {
        assertViewIsVisible( key9 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void nineShouldUpdateDisplay() throws Exception
    {
        key9.performClick();
        String expected = STARTING_VALUE + key9.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHave0Key() throws Exception
    {
        assertViewIsVisible( key0 );
    }

    // TODO: Check that this invoked the bus, not that they updated the
    //       display. This belongs more at the integration leve.
    @Test
    public void zeroShouldUpdateDisplay() throws Exception
    {
        key0.performClick();
        String expected = STARTING_VALUE + key0.getText();
//        assertThat( getDisplayText(), equalTo( expected ) );
    }

    @Test
    public void shouldHavePlusKey() throws Exception
    {
        assertViewIsVisible( plus );
    }

    @Test
    public void shouldHaveMinusKey() throws Exception
    {
        assertViewIsVisible( minus );
    }

    @Test
    public void shouldHaveMultiplyKey() throws Exception
    {
        assertViewIsVisible( multiply );
    }

    @Test
    public void shouldHaveDivideKey() throws Exception
    {
        assertViewIsVisible( divide );
    }

    @Test
    public void shouldHaveModuloKey() throws Exception
    {
        assertViewIsVisible( modulo );
    }

    @Test
    public void shouldHaveEqualKey() throws Exception
    {
        assertViewIsVisible( equal );
    }

    private View getViewById( int id )
    {
        return buttonFragment.getView().findViewById( id );
    }
}
