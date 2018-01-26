package model.expressions;

import myCollections.MyIDictionary;

public class VariableExpression extends Expression {
    private String name;

    public VariableExpression(String name)
    {
        this.name = name;
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) {
        return symbolTable.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
