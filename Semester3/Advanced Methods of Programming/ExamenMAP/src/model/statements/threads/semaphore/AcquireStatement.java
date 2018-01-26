package model.statements.threads.semaphore;

import javafx.util.Pair;
import model.ProgramState;
import model.statements.IStatement;
import myCollections.MyIDictionary;
import myCollections.MyIStack;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public AcquireStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        try{
            MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable().getSemaphore();
            MyIStack<IStatement> executionStack = state.getExecutionStack();

            Integer foundIndex = state.getSymbolTable().get(var);

            if(foundIndex == null)
                throw new Exception("No such variable in symbolTable");
            Pair<Integer, List<Integer>> semaphoreValue = semaphoreTable.get(foundIndex);
            List<Integer> threads = semaphoreValue.getValue();
            Integer nMax = semaphoreValue.getKey();
            if(nMax != threads.size())
            {
                if(threads.contains(state.getIdThread()))
                    throw new Exception("Already in process");
                threads.add(state.getIdThread());
                state.getSemaphoreTable().put(foundIndex, new Pair<>(nMax, threads));
            }else
            {
                executionStack.push(this);
                state.setExecutionStack(executionStack);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public String toString() {
        return "acquire( " + var + " )";
    }
}
