package Model.Statement;

import Collection.Stack.MyStack;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.ProgramState;

import java.io.FileNotFoundException;
import java.util.Stack;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, FileNotFoundException {
        return new ProgramState(new MyStack<>(), state.getSymbolTable().clone_dict(),
                state.getOutputList(), this.statement, state.getFileTable(), state.getHeap(), state.getId_thread() * 10);
    }

    @Override
    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }
}
