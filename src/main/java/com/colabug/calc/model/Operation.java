package com.colabug.calc.model;

public enum Operation
{
    PLUS( "+" ), MINUS( "-" ), MULTIPLY( "*" ), DIVIDE( "/" ), MODULO( "%" ),
    NONE( "" );

    private String operationString;

    Operation( String operationString )
    {
        this.operationString = operationString;
    }

    public String getOperationString()
    {
        return operationString;
    }
}
