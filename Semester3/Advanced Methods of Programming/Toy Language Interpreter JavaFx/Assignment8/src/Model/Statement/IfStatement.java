package Model.Statement;

import Collection.Stack.MyIStack;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.Expression.Expression;
import Model.ProgramState;

public class IfStatement implements IStatement{
    private Expression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        int value = 0;
        try{
            value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        }
        catch (Exception e){
            throw new ToyLanguageInterpreterException(e.getMessage());
        }
        if(value != 0) stack.push(thenStatement);
        else stack.push(elseStatement);
        return null;
    }

    @Override
    public String toString(){
        return "if(" + expression.toString() + ") then " + thenStatement.toString() + " else " + elseStatement.toString();
    }
}
