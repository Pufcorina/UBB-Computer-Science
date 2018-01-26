package model.expressions;

import model.exceptions.DivisionByZeroException;
import myCollections.MyIDictionary;

public class BooleanExpression extends Expression {
    private Expression expression_left;
    private Expression expression_right;
    private String comparisonOperator;

    public BooleanExpression(Expression expression_left, Expression expression_right, String comparisonOperator){
        this.comparisonOperator = comparisonOperator;
        this.expression_left = expression_left;
        this.expression_right = expression_right;
    }

    @Override
    public String toString() {
        return "( " + expression_left.toString() + " " + comparisonOperator + " " + expression_right.toString() + " )";
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws Exception {
        Integer expr1 = expression_left.evaluate(symbolTable, heapTable);
        Integer expr2 = expression_right.evaluate(symbolTable, heapTable);
        boolean booleanEvaluationResult;

        switch (comparisonOperator){
            case "<" :
                booleanEvaluationResult = expr1 < expr2;
                break;
            case "<=" :
                booleanEvaluationResult = expr1 <= expr2;
                break;
            case ">" :
                booleanEvaluationResult = expr1 > expr2;
                break;
            case ">=" :
                booleanEvaluationResult = expr1 >= expr2;
                break;
            case "==" :
                booleanEvaluationResult = expr1.equals(expr2);
                break;
            case "!=" :
                booleanEvaluationResult = !expr1.equals(expr2);
                break;
            default:
                throw new Exception(comparisonOperator + " is not a valid comparison operator.");
        }

        return booleanEvaluationResult ? 1 : 0;
    }
}
