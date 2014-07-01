package com.colabug.calc;

/**
 * @since 1.0
 */
public class OperationSelectedEvent extends BaseViewEvent
{
    private final Operation operator;

    public OperationSelectedEvent( Operation operator )
    {
        this.operator = operator;
    }

    public Operation getOperator()
    {
        return operator;
    }
}
