package Model.Expression;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.ADTEmptyException;

public class VariableExpression extends Expression {
    private String name;

    public VariableExpression(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
