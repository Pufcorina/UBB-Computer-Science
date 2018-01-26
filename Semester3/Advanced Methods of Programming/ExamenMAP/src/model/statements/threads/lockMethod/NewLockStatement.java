package model.statements.threads.lockMethod;

import model.ProgramState;
import model.statements.IStatement;
import myCollections.MyIDictionary;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public NewLockStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        MyIDictionary<Integer, Integer> lockTable = state.getLockTable();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();

        Integer location = state.getLockAddress();

        lockTable.put(location, -1);
        symbolTable.put(var, location);

        state.setSymbolTable(symbolTable);
        state.setLockTable(lockTable);
        lock.unlock();
        return null;
    }

    @Override
    public String toString(){
        return "newLock( " + var + " )";
    }
}
