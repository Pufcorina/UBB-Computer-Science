package model.statements;

import model.ProgramState;
import model.expressions.Expression;
import model.expressions.NotExpression;

public class RepeatStatement implements IStatement{
    private IStatement statement;
    private Expression expression;

    public RepeatStatement(IStatement statement, Expression expression)
    {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStatement act = new CompoundStatement(statement, new WhileStatement(new NotExpression(expression), statement));
        state.getExecutionStack().push(act);
        return null;
    }

    @Override
    public String toString(){
        return "repeat{ \n " + statement.toString() + "\n}until " + expression.toString();
    }
}
