package model.statements;

import model.ProgramState;
import model.exceptions.DivisionByZeroException;
import model.expressions.Expression;
import myCollections.MyIDictionary;

public class AssignStatement implements IStatement {
    private String name;
    private Expression expression;

    public AssignStatement(String name, Expression expression)
    {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        int value = 0;
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        value = expression.evaluate(symbolTable, state.getHeapTable());
        symbolTable.put(name, value);
        return null;
    }

    @Override
    public String toString()
    {
        return name + " = " + expression.toString();
    }
}
