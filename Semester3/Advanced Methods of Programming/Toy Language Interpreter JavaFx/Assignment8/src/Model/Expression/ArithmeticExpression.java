package Model.Expression;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.*;

public class ArithmeticExpression extends Expression {
    private Expression exp1;
    private Expression exp2;
    private OperationEnum operation;

    public ArithmeticExpression(Expression exp1, Expression exp2, OperationEnum operation){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operation = operation;
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws ADTEmptyException, DivisionByZero, VariableNotFoundException, ComparisonExpression {
        Integer eval1 = exp1.evaluate(symbolTable, heapTable);
        Integer eval2 = exp2.evaluate(symbolTable, heapTable);

        if(operation == OperationEnum.PLUS) return eval1 + eval2;
        else if(operation == OperationEnum.MINUS) return eval1 - eval2;
        else if(operation == OperationEnum.MULTIPLY) return eval1 * eval2;
        else if(operation == OperationEnum.DIVIDE){
            if(eval2 == 0) throw new DivisionByZero("Error trying to divide by zero.");
            return eval1 / eval2;
        }
        return -1;
    }

    @Override
    public String toString(){
        String result = "( " + exp1.toString();
        if(operation == OperationEnum.PLUS) result += " + ";
        else if(operation == OperationEnum.MINUS) result += " - ";
        else if(operation == OperationEnum.MULTIPLY) result += " * ";
        else if(operation == OperationEnum.DIVIDE) result += " / ";
        result += exp2.toString() + " )";
        return result;
    }
}
