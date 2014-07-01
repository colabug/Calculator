package com.colabug.calc;

/**
 * @since 1.0
 */
public class NumberEnteredEvent extends BaseViewEvent
{
    String number;

    public NumberEnteredEvent( String number )
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
}
