package model.expressions;

import myCollections.MyIDictionary;

public class ConstantExpression extends Expression {
    private Integer value;

    public ConstantExpression(Integer value)
    {
        this.value = value;
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
