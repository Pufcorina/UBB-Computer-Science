package model.statements;

import model.ProgramState;
import model.exceptions.DivisionByZeroException;
import model.expressions.Expression;

public class PrintStatement implements IStatement{
    private Expression variableName;

    public PrintStatement(Expression variableName)
    {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        state.getOutputList().add(variableName.evaluate(state.getSymbolTable(), state.getHeapTable()));
        return null;
    }

    @Override
    public String toString(){
        return "print( " + variableName + " )";
    }
}
