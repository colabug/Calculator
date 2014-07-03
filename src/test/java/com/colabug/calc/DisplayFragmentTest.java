package com.colabug.calc;

import android.view.View;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.colabug.calc.support.Assert.assertViewIsVisible;
import static com.colabug.calc.support.ResourceLocator.getResourceString;
import static org.hamcrest.core.IsEqual.equalTo;
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
    private static final String MAX_INT_VALUE   = "2147483647";
    private static final String STARTING_VALUE  = "123";
    private static final String APPENDED_VALUE  = "1230";
    private static final String VALUE_TO_APPEND = "0";

    private DisplayFragment displayFragment;

    private EditText display;

    @Before
    public void setUp() throws Exception
    {
        displayFragment = new DisplayFragment();
        startFragment( displayFragment );

        display = (EditText) findViewById( R.id.display );
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

    @Test
    public void shouldHaveBlankDisplayOnStart() throws Exception
    {
        assertThat( display.getText().toString(),
                    equalTo( getResourceString( R.string.EMPTY_STRING ) ) );
    }

    @Test
    public void shouldSetDisplayToValue() throws Exception
    {
        setDisplayValue();
        assertThat( display.getText().toString(),
                    equalTo( STARTING_VALUE ) );
    }

    private void setDisplayValue()
    {
        displayFragment.setDisplay( STARTING_VALUE );
    }

    @Test
    public void shouldAppendDisplayedValue() throws Exception
    {
        setDisplayValue();
        displayFragment.appendValue( VALUE_TO_APPEND );
        assertThat( display.getText().toString(),
                    equalTo( APPENDED_VALUE ) );
    }

    @Test
    public void displayShouldLimitEntryTo10Characters() throws Exception
    {
        enterLargestInt();
        displayFragment.appendValue( VALUE_TO_APPEND );
        assertThat( display.getText().toString(),
                    equalTo( MAX_INT_VALUE ) );
    }

    private void enterLargestInt()
    {
        displayFragment.setDisplay( MAX_INT_VALUE );
    }

    @Test
    public void shouldClearDisplayedValue() throws Exception
    {
        setDisplayValue();
        displayFragment.clearDisplayedValue();
        assertThat( display.getText().toString(),
                    equalTo( getResourceString( R.string.EMPTY_STRING ) ) );
    }

    @Test
    public void shouldShowError() throws Exception
    {
        String errorString = getResourceString( R.string.ERROR );
        displayFragment.showError( errorString );
        assertThat( display.getText().toString(),
                    equalTo( errorString ) );
    }

    private View findViewById( int id )
    {
        return displayFragment.getView().findViewById( id );
    }
}
