package model.statements;

import model.ProgramState;
import model.expressions.BooleanExpression;
import model.expressions.Expression;
import model.expressions.VariableExpression;
import myCollections.MyIStack;

public class ForStatement implements IStatement {
    private String var;
    private Expression initialization;
    private Expression condition;
    private Expression increment;
    private IStatement statement;

    public ForStatement(String var, Expression initialization, Expression condition, Expression increment, IStatement statement){
        this.var = var;
        this.initialization = initialization;
        this.increment = increment;
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        IStatement newStatement = new CompoundStatement(new AssignStatement(var, initialization), new WhileStatement(new BooleanExpression(new VariableExpression("v"), condition, "<"),
                new CompoundStatement(statement, new AssignStatement(var, increment))));
        executionStack.push(newStatement);
        state.setExecutionStack(executionStack);
        return null;
    }

    @Override
    public String toString(){
        return "for( " + var + "=" + initialization.toString() + "; " + var + "<" + condition.toString() + "; " + var + "=" + increment.toString() + " ) \n " + statement.toString();
    }
}
