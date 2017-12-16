package Model.Expression;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.ADTEmptyException;
import Model.Exceptions.DivisionByZero;
import Model.Exceptions.VariableNotFoundException;

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
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws ADTEmptyException, DivisionByZero, VariableNotFoundException {
        if(!symbolTable.containsKey(variableName))
            throw new VariableNotFoundException(variableName + " not found in Symbol Table");

        Integer address = symbolTable.get(variableName);

        if(!heapTable.containsKey(address))
            throw new VariableNotFoundException("Memory not allocated in segment address " + address);
        return heapTable.get(address);
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
}
