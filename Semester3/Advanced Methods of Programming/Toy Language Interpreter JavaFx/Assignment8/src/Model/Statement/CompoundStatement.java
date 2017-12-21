package Model.Statement;

import Collection.Stack.MyIStack;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.ProgramState;

public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public String toString(){
        return first.toString() + "; " + second.toString();
    }
}
