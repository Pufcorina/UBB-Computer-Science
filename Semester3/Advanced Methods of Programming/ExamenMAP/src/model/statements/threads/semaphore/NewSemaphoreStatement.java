package model.statements.threads.semaphore;

import javafx.util.Pair;
import model.ProgramState;
import model.expressions.Expression;
import model.statements.IStatement;
import myCollections.MyIDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewSemaphoreStatement implements IStatement {
    private String var;
    private Expression expression;
    private static Lock lock = new ReentrantLock();

    public NewSemaphoreStatement(String var, Expression expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable().getSemaphore();

        Integer value = expression.evaluate(symbolTable, state.getHeapTable());
        Integer location = state.getSemaphoreTable().getSemaphorAddress();
        semaphoreTable.put(location, new Pair<>(value, new ArrayList<>()));
        symbolTable.put(var, location);

        state.setSemaphoreTable(semaphoreTable);
        state.setSymbolTable(symbolTable);
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newSemaphore( " + var + ", " + expression.toString() + ")";
    }
}
