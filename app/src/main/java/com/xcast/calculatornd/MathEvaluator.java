package com.xcast.calculatornd;

import org.mariuszgromada.math.mxparser.Expression;

import java.math.BigDecimal;

public class MathEvaluator
{
    public static int precision = 2;

    public static String evaluate(String expresion)
    {
        if(expresion.isEmpty()) {return "";}
        Expression e = new Expression(expresion);
        try
        {
            BigDecimal db = new BigDecimal(e.calculate())
                    .setScale(precision,BigDecimal.ROUND_HALF_UP);
            return db.toString();
        } catch (NumberFormatException ex)
        {
            return "";
        }
    }

    public static void setPrecision(int p)
    {
        precision = p;
    }
}
