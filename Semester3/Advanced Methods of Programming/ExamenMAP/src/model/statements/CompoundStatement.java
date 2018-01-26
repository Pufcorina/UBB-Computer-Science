package model.statements;

import model.ProgramState;

public class CompoundStatement implements IStatement{
    private IStatement leftStatement;
    private IStatement rightStatement;

    public CompoundStatement(IStatement leftStatement, IStatement rightStatement)
    {
        this.leftStatement = leftStatement;
        this.rightStatement = rightStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        state.getExecutionStack().push(rightStatement);
        state.getExecutionStack().push(leftStatement);
        return null;
    }

    @Override
    public String toString()
    {
        return leftStatement.toString() + "\n" + rightStatement.toString();
    }
}
