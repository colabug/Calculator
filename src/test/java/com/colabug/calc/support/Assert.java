package com.colabug.calc.support;

import android.view.View;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Assert helper methods for testing.
 *
 * @since 1.0
 */
public final class Assert
{
    public static void assertViewIsVisible( View view )
    {
        assertNotNull( view );
        assertThat( view.getVisibility(), equalTo( View.VISIBLE ) );
    }
}
