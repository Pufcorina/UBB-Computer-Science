package model;

import javafx.util.Pair;
import model.exceptions.ADTEmptyException;
import model.statements.IStatement;
import model.util.AddressBuilder;
import model.util.MyFilePair;
import myCollections.*;

import java.util.ArrayList;
import java.util.List;

public class ProgramState {
    private MyIStack<IStatement> executionStack;
    private List<Integer> outputList;
    private MyIDictionary<String, Integer> symbolTable;
    private MyIDictionary<Integer, Integer> heapTable;
    private MyIDictionary<Integer, MyFilePair> fileTable;
    private AddressBuilder b = new AddressBuilder();
    private Integer id_thread = 1;
    private Integer last_id = 1;
    private MyIDictionary<Integer, Integer> latchTable;
    private AddressBuilder l = new AddressBuilder();
    private MyIDictionary<Integer, Integer> lockTable;
    private AddressBuilder lockAddress = new AddressBuilder();
    private MyIDictionary<Integer, Pair<Integer, List<Integer>>> barrierTable;
    private AddressBuilder barrierAddress = new AddressBuilder();
    private MyISemaphore semaphoreTable;

    public ProgramState(MyStack<IStatement> programStateMyStack, MyIDictionary<String, Integer> symbolTable, List<Integer> outputList, IStatement originalProgram,
                        MyIDictionary<Integer, MyFilePair> fileTable, MyIDictionary<Integer, Integer> heap, Integer id, MyIDictionary<Integer, Integer> latchTable,
                        MyIDictionary<Integer, Integer> lockTable, MyIDictionary<Integer, Pair<Integer, List<Integer>>> barrierTable, MyISemaphore semaphoreTable) {
        executionStack = programStateMyStack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        heapTable = heap;
        this.id_thread = id;
        this.latchTable = latchTable;
        this.lockTable = lockTable;
        this.barrierTable = barrierTable;
        this.semaphoreTable = semaphoreTable;

        executionStack.push(originalProgram);
    }

    public MyISemaphore getSemaphoreTable() {
        return semaphoreTable;
    }

    public Integer getBarrierAddress() {return barrierAddress.getFreeAddress(); }

    public void setBarrierTable(MyIDictionary<Integer, Pair<Integer, List<Integer>>> barrierTable) {
        this.barrierTable = barrierTable;
    }

    public MyIDictionary<Integer, Pair<Integer, List<Integer>>> getBarrierTable() {
        return barrierTable;
    }

    public void setLast_id(Integer last_id) {
        this.last_id = last_id;
    }

    public Integer getLast_id() {
        return last_id;
    }

    public MyIDictionary<Integer, Integer> getLockTable() {
        return lockTable;
    }

    public void setLockTable(MyIDictionary<Integer, Integer> lockTable) {
        this.lockTable = lockTable;
    }

    public Integer getLockAddress() { return lockAddress.getFreeAddress(); }

    public Integer getNewLatchAddress() { return l.getFreeAddress(); }

    public Integer getNewHeapAddress() {
        return b.getFreeAddress();
    }

    public ProgramState(IStatement originalProgram)
    {
        executionStack = new MyStack<>();
        outputList = new ArrayList<>();
        symbolTable = new MyDictionary<>();
        heapTable = new MyDictionary<>();
        fileTable = new MyDictionary<>();
        latchTable = new MyDictionary<>();
        lockTable = new MyDictionary<>();
        barrierTable = new MyDictionary<>();
        semaphoreTable = new MySemaphore();

        executionStack.push(originalProgram);
    }

    public MyIDictionary<Integer, Integer> getLatchTable() {
        return latchTable;
    }

    public void setHeapTable(MyIDictionary<Integer, Integer> heapTable) {
        this.heapTable = heapTable;
    }

    public void setFileTable(MyIDictionary<Integer, MyFilePair> fileTable) {
        this.fileTable = fileTable;
    }

    public void setExecutionStack(MyIStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public void setSymbolTable(MyIDictionary<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setOutputList(List<Integer> outputList) {
        this.outputList = outputList;
    }

    public void setLatchTable(MyIDictionary<Integer, Integer> latchTable) {
        this.latchTable = latchTable;
    }

    public MyIStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public List<Integer> getOutputList() {
        return outputList;
    }

    public MyIDictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    @Override
    public String toString()
    {

        return "Thread Id: " + id_thread + "\n" +
                "Execution Stack : \n" + executionStack.toString() + "\n" +
                "Symbol Table: \n" + symbolTable.toString() + "\n" +
                "Output List: \n" + outputList.toString() + "\n" +
                "file Table: \n" + fileTable.toString() + "\n" +
                "Heap Table: \n" + heapTable.toString() + "\n" +
                "Latch Table: \n" + latchTable.toString() + "\n" +
                "Lock Table: \n" + lockTable.toString() + "\n" +
                "*****Next Step*****\n";
    }

    public boolean isCompleted() {
        return executionStack.isEmpty();
    }

    public ProgramState oneStep() throws Exception {
        if(executionStack.isEmpty())
            throw new ADTEmptyException("Stack empty!");
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public Integer getIdThread() {
        return id_thread;
    }

    public MyIDictionary<Integer, MyFilePair> getFileTable() {
        return fileTable;
    }

    public MyIDictionary<Integer, Integer> getHeapTable() {
        return heapTable;
    }

    public void setSemaphoreTable(MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphoreTable) {
        this.semaphoreTable.setSemaphore(semaphoreTable);
    }
}
