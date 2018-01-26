package model.statements.threads.countDownLatch;

import model.ProgramState;
import model.statements.IStatement;
import myCollections.MyIStack;
import myCollections.MyStack;


public class AwaitStatement implements IStatement {
    private String var;

    public AwaitStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        Integer foundIndex = state.getSymbolTable().get(var);
        if(foundIndex == null)
            throw new Exception("No such variable in symbolTable");
        Integer latchIndex = state.getLatchTable().get(foundIndex);
        if (latchIndex == null)
            throw new Exception("No such index in latchTable");
        if (latchIndex != 0) {
            executionStack.push(this);
            state.setExecutionStack(executionStack);
        }
        return null;
    }

    @Override
    public String toString(){
        return "await( " + var + " )";
    }
}
