package com.colabug.calc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.colabug.calc.support.CalculatorTestRunner;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.colabug.calc.support.CalculatorTestRunner.assertViewIsVisible;
import static com.colabug.calc.support.CalculatorTestRunner.startFragment;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * {@link CalculatorFragment} test suite.
 *
 * @since 1.0
 */
@RunWith (CalculatorTestRunner.class)

public class CalculatorFragmentTest
{
    private static final String STARTING_VALUE = "123";

    private CalculatorFragment calculatorFragment;

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
    private Button clear;
    private Button equal;

    private TextView display;

    @Before
    public void setUp() throws Exception
    {
        // Start fragment
        calculatorFragment = new CalculatorFragment();
        startFragment( calculatorFragment );

        // Result display
        display = (TextView) getViewById( R.id.display );
        display.setText( STARTING_VALUE );

        // Number, clear, & equal keys
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
        clear = (Button) getViewById( R.id.clear );
        equal = (Button) getViewById( R.id.equal );
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
    public void oneShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( key1 ) );
    }

    @Test
    public void oneShouldUpdateDisplay() throws Exception
    {
        key1.performClick();
        String expected = STARTING_VALUE + key1.getText();
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
        assertThat( display.getText().toString(), equalTo( expected ) );
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
    public void shouldHaveEqualKey() throws Exception
    {
        assertViewIsVisible( equal );
    }

    @Test
    public void clearShouldClearDisplay() throws Exception
    {
        clear.performClick();
        assertThat( display.getText().toString(), equalTo( "" ));
    }

    @Test
    public void equalShouldHaveClickListener() throws Exception
    {
        assertNotNull( getViewOnClickListener( equal ) );
    }

    private View getViewById( int id )
    {
        return calculatorFragment.getView().findViewById( id );
    }

    private View.OnClickListener getViewOnClickListener( View view )
    {
        ShadowView shadowView = Robolectric.shadowOf( view );
        return shadowView.getOnClickListener();
    }
}
