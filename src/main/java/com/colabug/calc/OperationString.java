package com.colabug.calc;

public class OperationString
{
    public static String PLUS     = "+";
    public static String MINUS    = "-";
    public static String DIVIDE   = "/";
    public static String MULTIPLY = "*";

    public static String getOperationString( Operation operation )
    {
        switch ( operation )
        {
            case PLUS:
                return PLUS;

            case MINUS:
                return MINUS;

            case MULTIPLY:
                return MULTIPLY;

            case DIVIDE:
                return DIVIDE;
        }

        return "";
    }
}
