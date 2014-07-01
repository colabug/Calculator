package com.colabug.calc.support;

import android.graphics.drawable.Drawable;

import org.robolectric.Robolectric;

/**
 * Locates resources for testing.
 *
 * @since 1.0
 */
public class ResourceLocator
{
    public static String getResourceString( int id )
    {
        return Robolectric.application
                          .getApplicationContext()
                          .getString( id );
    }

    public static Drawable getResourceDrawable( int id )
    {
        return Robolectric.application
                          .getApplicationContext()
                          .getResources()
                          .getDrawable( id );
    }
}
