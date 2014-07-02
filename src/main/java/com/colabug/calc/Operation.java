package com.colabug.calc;

public enum Operation
{
    NUMBER( "" ),
    PLUS( "+" ), MINUS( "-" ), MULTIPLY( "*" ), DIVIDE( "/" ), MODULO( "%" ),
    EQUAL( "" ),
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
