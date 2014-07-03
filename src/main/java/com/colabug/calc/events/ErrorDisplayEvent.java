package com.colabug.calc.events;

import com.colabug.calc.BaseViewEvent;

/**
 * @since 1.0
 */
public class ErrorDisplayEvent extends BaseViewEvent
{
    protected String errorToDisplay;

    public ErrorDisplayEvent( String string )
    {
        errorToDisplay = string;
    }

    public String getError()
    {
        return errorToDisplay;
    }
}
