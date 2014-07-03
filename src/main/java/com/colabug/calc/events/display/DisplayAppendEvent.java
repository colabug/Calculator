package com.colabug.calc.events.display;

/**
 * @since 1.0
 */
public class DisplayAppendEvent extends DisplayEvent
{
    private String textToAppend;

    public DisplayAppendEvent( String textToAppend )
    {
        this.textToAppend = textToAppend;
    }

    public String getTextToAppend()
    {
        return textToAppend;
    }
}
