package com.colabug.calc.events.button;

/**
 * @since 1.0
 */
public class NumberButtonEvent extends ButtonEvent
{
    String number;

    public NumberButtonEvent( String number )
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
}
