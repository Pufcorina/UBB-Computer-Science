package Model.Expression;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.ADTEmptyException;
import Model.Exceptions.ComparisonExpression;
import Model.Exceptions.DivisionByZero;
import Model.Exceptions.VariableNotFoundException;

import java.util.Objects;

public class BooleanExpression extends Expression {
    private Expression expression_left;
    private Expression expression_right;
    private String comparisonOperator;

    public BooleanExpression(Expression expression_left, Expression expression_right, String comparisonOperator){
        this.comparisonOperator = comparisonOperator;
        this.expression_left = expression_left;
        this.expression_right = expression_right;
    }

    public Expression getExpression_left() {
        return expression_left;
    }

    public Expression getExpression_right() {
        return expression_right;
    }

    public String getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public void setExpression_left(Expression expression_left) {
        this.expression_left = expression_left;
    }

    public void setExpression_right(Expression expression_right) {
        this.expression_right = expression_right;
    }

    @Override
    public String toString() {
        return "( " + expression_left.toString() + " " + comparisonOperator + " " + expression_right.toString() + " )";
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws ADTEmptyException, DivisionByZero, VariableNotFoundException, ComparisonExpression {
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
                throw new ComparisonExpression(comparisonOperator + " is not a valid comparison operator.");
        }

        return booleanEvaluationResult ? 1 : 0;
    }
}
