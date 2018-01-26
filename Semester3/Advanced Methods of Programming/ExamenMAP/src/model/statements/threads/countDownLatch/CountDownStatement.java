package model.statements.threads.countDownLatch;

import model.ProgramState;
import model.expressions.ConstantExpression;
import model.statements.IStatement;
import model.statements.PrintStatement;
import myCollections.MyIDictionary;
import myCollections.MyIStack;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public CountDownStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        MyIDictionary<Integer, Integer> latchTable = state.getLatchTable();

        Integer foundIndex = state.getSymbolTable().get(var);

        if(foundIndex == null)
            throw new Exception("No such variable in symbolTable");
        Integer latchValue = latchTable.get(foundIndex);
        if (latchValue == null)
            throw new Exception("No such index in latchTable");
        if (latchValue > 0) {
            latchTable.put(foundIndex, latchValue - 1);
            executionStack.push(new PrintStatement(new ConstantExpression(state.getLast_id())));
            state.setExecutionStack(executionStack);
            state.setLatchTable(latchTable);
        }
        lock.unlock();
        return null;
    }

    @Override
    public String toString(){
        return "countDown( " + var + " )";
    }
}
