package Model;

import Collection.Dictionary.MyDictionary;
import Collection.Dictionary.MyIDictionary;
import Collection.List.MyIList;
import Collection.List.MyList;
import Collection.Stack.MyIStack;
import Collection.Stack.MyStack;
import Model.Exceptions.ADTEmptyException;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.Statement.File.MyFilePair;
import Model.Statement.Heap.HeapAddressBuilder;
import Model.Statement.IStatement;

import java.io.FileNotFoundException;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

public class ProgramState {
    private MyIStack<IStatement> executionStack;
    private MyIDictionary<String, Integer> symbolTable;
    private List<Integer> outputList;
    private MyIDictionary<Integer, MyFilePair> fileTable;
    private IStatement originalProgram;
    private MyIDictionary<Integer, Integer> heapTable;
    private Integer id_thread = 1;
    private HeapAddressBuilder b = new HeapAddressBuilder();

    public ProgramState(MyStack<IStatement> programStateMyStack, MyIDictionary<String, Integer> symbolTable, List<Integer> outputList, IStatement originalProgram, MyIDictionary<Integer, MyFilePair> fileTable, MyIDictionary<Integer, Integer> heap, Integer id) {
        executionStack = programStateMyStack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        heapTable = heap;
        this.originalProgram = originalProgram;
        this.id_thread = id;

        executionStack.push(originalProgram);
    }

    public Integer getNewAddress(){
        return b.getFreeAddress();
    }

    public ProgramState(IStatement originalProgram){
        executionStack = new MyStack<>();
        symbolTable = new MyDictionary<>();
        outputList = new ArrayList<>();
        fileTable = new MyDictionary<>();
        heapTable = new MyDictionary<>();
        this.originalProgram = originalProgram;

        executionStack.push(originalProgram);
    }

    public Integer getId_thread() {
        return id_thread;
    }

    public void setId_thread(Integer id_thread) {
        this.id_thread = id_thread;
    }

    public MyIStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(MyStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public MyIDictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(MyIDictionary<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public List<Integer> getOutputList() {
        return outputList;
    }

    public void setOutputList(List<Integer> outputList) {
        this.outputList = outputList;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyIDictionary<Integer, MyFilePair> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<Integer, MyFilePair> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIDictionary<Integer, Integer> getHeap() {
        return heapTable;
    }

    public void setHeap(MyIDictionary<Integer, Integer> heapTable) {
        this.heapTable = heapTable;
    }

    public void addOut(Integer number) {
        this.outputList.add(number);
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws ToyLanguageInterpreterException, FileNotFoundException {
        if (executionStack.isEmpty())
            throw new ADTEmptyException("Stack empty");
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return "Thread number id: " + id_thread.toString() + "\n" +
                "------------------------------------------------------\n" +
                "*****OutputList*****\n" +
                outputList.toString() + "\n" +
                "*****SymbolTable*****\n" +
                symbolTable.toString() + "\n" +
                "*****ExectutionStack*****\n" +
                executionStack.toString() + "\n" +
                "*****FileTable*****\n" +
                fileTable.toString() + "\n" +
                "*****HeapTable*****\n" +
                heapTable.toString() + "\n" +
                "------------------------------------------------------\n\n\n";
    }
}
