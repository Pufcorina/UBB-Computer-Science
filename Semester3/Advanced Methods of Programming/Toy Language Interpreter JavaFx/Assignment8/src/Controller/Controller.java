package Controller;

import Collection.Dictionary.MyIDictionary;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.File.CloseStatement;
import Repository.IRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository){
        this.repository = repository;
    }


    public void executeOneStep()
    {
        executor = Executors.newFixedThreadPool(8);
        removeCompletedProgram(repository.getProgramStateList());
        List<ProgramState> programStates = repository.getProgramStateList();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllProgram(repository.getProgramStateList());
            } catch (InterruptedException e) {
                System.out.println();
            }
            programStates.forEach(e -> {
                try {
                    repository.logPrgStateExec(e);
                } catch (IOException e1) {
                    System.out.println();
                }
            });
            removeCompletedProgram(repository.getProgramStateList());
            executor.shutdownNow();
        }
    }

    private Set conservativeGarbageCollector(Collection<Integer> symbolTableValues, MyIDictionary<Integer, Integer> heapTable){
        return heapTable.entrySet().stream().filter(e->symbolTableValues.contains(e.getKey()))
                .collect(Collectors.toSet());

    }

    private void closeAllFiles(Collection<Integer> fileTable, MyIDictionary<String, Integer> symbolTable, ProgramState programState) throws Exception {
        System.out.println(fileTable);
        List<Map.Entry<String,Integer>> keys = symbolTable.entrySet().stream().filter(e->fileTable.contains(e.getValue())).collect(Collectors.toList());

        for(Map.Entry<String,Integer> e : keys){
            if(programState.getFileTable().containsKey(e.getValue()))
                new CloseStatement(new VariableExpression(e.getKey())).execute(programState);
        }
    }


    @SuppressWarnings("unchecked")
    private void oneStepForAllProgram(List<ProgramState> states) throws InterruptedException {
        List<Callable<ProgramState>>
                callList =
                states.stream().map(
                        (ProgramState programState) -> (Callable<ProgramState>) (programState::oneStep))
                        .collect(Collectors.toList());

        List<ProgramState> newProgramStates = null;
        try {
            newProgramStates = executor.invokeAll(callList).stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (InterruptedException e) {
            System.out.println("Could not invoke the callables!");
        }

        newProgramStates.forEach(repository::addProgramState);
    }


    @SuppressWarnings("unchecked")
    public void allSteps() throws Exception {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> states = removeCompletedProgram(repository.getProgramStateList());
        // List<ProgramState> prgList = null;
        while(states.size() > 0){
            oneStepForAllProgram(states);
            states = removeCompletedProgram(repository.getProgramStateList());
        }
        closeAllFiles(repository.getCurrentProgram().getFileTable().keySet(), repository.getCurrentProgram().getSymbolTable(), repository.getCurrentProgram());
        repository.logPrgStateExec(repository.getCurrentProgram());

        executor.shutdownNow();
        repository.setProgramStateList(states);

    }

    private void printThings(){
        ProgramState programState = repository.getCurrentProgram();
        System.out.print("------------------------------------------------------\n");
        System.out.print("*****OutputList*****\n");
        System.out.print(programState.getOutputList() + "\n");

        System.out.print("*****SymbolTable*****\n");
        System.out.print(programState.getSymbolTable().toString() + "\n");

        System.out.print("*****ExectutionStack*****\n");
        System.out.print(programState.getExecutionStack().toString() + "\n");

        System.out.print("*****FileTable*****\n");
        System.out.print(programState.getFileTable().toString() + "\n");

        System.out.print("*****HeapTable*****\n");
        System.out.print(programState.getHeap().toString() + "\n");

        System.out.print("------------------------------------------------------\n");
    }

    private List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList)
    {
        return inProgramList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    public IRepository getRepository() {
        return repository;
    }
}
