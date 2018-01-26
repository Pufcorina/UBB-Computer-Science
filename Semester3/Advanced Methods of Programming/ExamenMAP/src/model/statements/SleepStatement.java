package model.statements;

import model.ProgramState;
import myCollections.MyIStack;

public class SleepStatement implements IStatement {
    private Integer value;

    public SleepStatement(Integer value){
        this.value = value;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        if (value != 0)
        {
            MyIStack<IStatement> executionStack = state.getExecutionStack();
            executionStack.push(new SleepStatement(value - 1));
            state.setExecutionStack(executionStack);
        }
        return null;
    }

    @Override
    public String toString(){
        return "sleep( " + value.toString() + " )";
    }
}
