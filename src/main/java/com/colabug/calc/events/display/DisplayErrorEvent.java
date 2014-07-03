package com.colabug.calc.events.display;

/**
 * @since 1.0
 */
public class DisplayErrorEvent extends DisplayEvent
{
    protected String errorToDisplay;

    public DisplayErrorEvent( String string )
    {
        errorToDisplay = string;
    }

    public String getError()
    {
        return errorToDisplay;
    }
}
