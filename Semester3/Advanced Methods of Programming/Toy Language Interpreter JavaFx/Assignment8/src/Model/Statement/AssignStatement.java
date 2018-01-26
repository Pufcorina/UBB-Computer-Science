package Model.Statement;

import Collection.Dictionary.MyIDictionary;
import Collection.Stack.MyIStack;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.Expression.Expression;
import Model.ProgramState;

public class AssignStatement implements IStatement {
    private String name;
    private Expression expression;

    public AssignStatement(String name, Expression expression){
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();
        int value = 0;
        try{
            value = expression.evaluate(symbolTable, heapTable);
        }
        catch (Exception e){
            throw new ToyLanguageInterpreterException(e.getMessage());
        }
        symbolTable.put(name, value);
        return null;
    }

    @Override
    public String toString(){
        return name + " = " + expression.toString();
    }
}
