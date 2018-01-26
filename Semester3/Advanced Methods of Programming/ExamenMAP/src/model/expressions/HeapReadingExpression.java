package model.expressions;

import myCollections.MyIDictionary;

public class HeapReadingExpression extends Expression {
    private String variableName;

    @Override
    public String toString(){
        return "rH( " + variableName + ") ";
    }

    public HeapReadingExpression(String variableName){
        this.variableName = variableName;
    }

    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws Exception {
        if(!symbolTable.containsKey(variableName))
            throw new Exception(variableName + " not found in Symbol Table");

        Integer address = symbolTable.get(variableName);

        if(!heapTable.containsKey(address))
            throw new Exception("Memory not allocated in segment address " + address);
        return heapTable.get(address);
    }
}
