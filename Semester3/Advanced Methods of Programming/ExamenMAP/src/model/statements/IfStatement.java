package model.statements;

import model.ProgramState;
import model.exceptions.DivisionByZeroException;
import model.expressions.Expression;

public class IfStatement implements IStatement {
    private Expression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement)
    {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        int value = 0;
        value = expression.evaluate(state.getSymbolTable(), state.getHeapTable());

        if(value != 0) state.getExecutionStack().push(thenStatement);
        else state.getExecutionStack().push(elseStatement);
        return null;
    }

    @Override
    public String toString()
    {
        return "if(" + expression.toString() + ") \n    then " + thenStatement.toString() + " \n    else " + elseStatement.toString();
    }
}
