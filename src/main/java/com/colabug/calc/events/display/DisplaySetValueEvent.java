package com.colabug.calc.events.display;


import com.colabug.calc.events.display.DisplayEvent;

/**
 * @since 1.0
 */
public class DisplaySetValueEvent extends DisplayEvent
{
    private String value;

    public DisplaySetValueEvent( String value )
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
