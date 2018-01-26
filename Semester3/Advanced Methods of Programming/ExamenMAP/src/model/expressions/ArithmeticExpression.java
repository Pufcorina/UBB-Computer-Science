package model.expressions;

import model.exceptions.DivisionByZeroException;
import myCollections.MyIDictionary;

public class ArithmeticExpression extends Expression {
    private Expression leftExpression;
    private Expression rightExpression;
    private OperationEnum operation;

    public ArithmeticExpression(Expression leftExpression, Expression rightExpression, OperationEnum operation)
    {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws Exception {
        switch (operation)
        {
            case PLUS:
                return leftExpression.evaluate(symbolTable, heapTable) + rightExpression.evaluate(symbolTable, heapTable);
            case MINUS:
                return leftExpression.evaluate(symbolTable, heapTable) - rightExpression.evaluate(symbolTable, heapTable);
            case DIVIDE:
                if (rightExpression.evaluate(symbolTable, heapTable) == 0)
                    throw new DivisionByZeroException("Cannot divide by 0!");
                return leftExpression.evaluate(symbolTable, heapTable) / rightExpression.evaluate(symbolTable, heapTable);
            case MULTIPLY:
                return leftExpression.evaluate(symbolTable, heapTable) * rightExpression.evaluate(symbolTable, heapTable);
            default:
                return -1;
        }
    }

    @Override
    public String toString() {
        String buffer = "( " + leftExpression.toString();
        switch (operation)
        {
            case PLUS:
                buffer += " + ";
                break;
            case MINUS:
                buffer += " - ";
                break;
            case MULTIPLY:
                buffer += " * ";
                break;
            case DIVIDE:
                buffer += " / ";
                break;
            default:
                break;
        }
        buffer += rightExpression.toString() + " )";
        return buffer;
    }
}
