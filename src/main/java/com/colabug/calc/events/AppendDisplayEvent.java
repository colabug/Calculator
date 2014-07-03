package com.colabug.calc.events;

import com.colabug.calc.BaseViewEvent;

/**
 * @since 1.0
 */
public class AppendDisplayEvent extends BaseViewEvent
{
    private String textToAppend;

    public AppendDisplayEvent( String textToAppend )
    {
        this.textToAppend = textToAppend;
    }

    public String getTextToAppend()
    {
        return textToAppend;
    }
}
