package com.colabug.calc;

/**
 * @since 1.0
 */
public class StoreValueEvent extends BaseViewEvent
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
