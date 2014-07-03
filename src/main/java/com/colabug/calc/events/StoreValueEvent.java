package com.colabug.calc.events;

/**
 * @since 1.0
 */
public class StoreValueEvent extends BaseEvent
{
    private String value;

    public StoreValueEvent( String value )
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
