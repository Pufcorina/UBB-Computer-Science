package Model.Expression;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.ADTEmptyException;
import Model.Exceptions.ComparisonExpression;
import Model.Exceptions.DivisionByZero;
import Model.Exceptions.VariableNotFoundException;


public abstract class Expression {
    abstract public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws ADTEmptyException, DivisionByZero, VariableNotFoundException, ComparisonExpression;
    abstract public String toString();
}
