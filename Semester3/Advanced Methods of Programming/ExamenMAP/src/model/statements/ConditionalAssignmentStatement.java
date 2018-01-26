package model.statements;

import model.ProgramState;
import model.expressions.Expression;
import myCollections.MyIStack;

public class ConditionalAssignmentStatement implements IStatement {
    private String var;
    private Expression condition;
    private Expression trueCondition;
    private Expression falseCondition;

    public ConditionalAssignmentStatement(String var, Expression condition, Expression trueCondition, Expression falseCondition){
        this.var = var;
        this.condition = condition;
        this.trueCondition = trueCondition;
        this.falseCondition = falseCondition;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        IStatement newStatement = new IfStatement(condition, new AssignStatement(var, trueCondition), new AssignStatement(var, falseCondition));
        executionStack.push(newStatement);
        state.setExecutionStack(executionStack);
        return null;
    }

    @Override
    public String toString() {
        return var + " = " + condition.toString() + " ? " + trueCondition.toString() + " : " + falseCondition.toString();
    }
}
