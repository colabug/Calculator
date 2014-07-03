package com.colabug.calc.events.button;

import com.colabug.calc.Operation;

/**
 * @since 1.0
 */
public class OperatorButtonEvent extends ButtonEvent
{
    private final Operation operator;

    public OperatorButtonEvent( Operation operator )
    {
        this.operator = operator;
    }

    public Operation getOperator()
    {
        return operator;
    }
}
