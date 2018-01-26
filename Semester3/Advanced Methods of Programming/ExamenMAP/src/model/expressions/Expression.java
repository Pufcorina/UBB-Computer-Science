package model.expressions;

import model.exceptions.DivisionByZeroException;
import myCollections.MyIDictionary;

public abstract class Expression {
    abstract public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws Exception;
    abstract public String toString();
}
