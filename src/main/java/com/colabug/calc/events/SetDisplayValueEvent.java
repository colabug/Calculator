package com.colabug.calc.events;


import com.colabug.calc.BaseViewEvent;

/**
 * @since 1.0
 */
public class SetDisplayValueEvent extends BaseViewEvent
{
    private String value;

    public SetDisplayValueEvent( String value )
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
