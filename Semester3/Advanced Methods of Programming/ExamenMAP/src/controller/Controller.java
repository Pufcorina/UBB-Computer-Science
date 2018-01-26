package controller;

import model.ProgramState;
import model.exceptions.FileException;
import repository.IRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void  oneStepForAllPrograms(List<ProgramState> programStates) throws InterruptedException {
        removeCompletedPrograms(repository.getProgramStateList());
        executor = Executors.newFixedThreadPool(8);
        if(repository.getProgramStateList().size() == 0)
            return;
        try {
            List<Callable<ProgramState>> callList = programStates.stream()
                    .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                    .collect(Collectors.toList());

            List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                    .map(programStateFuture -> {
                        try {
                            return programStateFuture.get();
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull).collect(Collectors.toList());

            programStates.addAll(newProgramList);
            programStates.forEach(programState -> {
                try {
                    repository.logProgramStateExecution(programState);
                } catch (FileException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        programStates.forEach(e -> {
            try{
                repository.logProgramStateExecution(e);
            } catch (FileException e1) {
                System.out.println(e1.getMessage());
            }
        });
        removeCompletedPrograms(repository.getProgramStateList());
        executor.shutdownNow();
    }

    private void removeCompletedPrograms(List<ProgramState> programStateList) {
        programStateList.stream().filter(p -> !p.isCompleted()).collect(Collectors.toList());
    }

    public IRepository getRepository() {
        return repository;
    }
}
