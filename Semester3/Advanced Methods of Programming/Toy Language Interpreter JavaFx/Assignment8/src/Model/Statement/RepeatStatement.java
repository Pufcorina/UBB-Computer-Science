package Model.Statement;

import Model.Expression.Expression;
import Model.Expression.NotExpression;
import Model.ProgramState;

public class RepeatStatement implements IStatement {
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
        return "( repeat (" + statement.toString() + ") until " + expression.toString() + " )";
    }
}