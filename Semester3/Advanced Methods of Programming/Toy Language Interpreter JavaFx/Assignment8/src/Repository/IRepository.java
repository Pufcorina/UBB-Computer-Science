package Repository;

import Collection.List.MyIList;
import Model.ProgramState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    ProgramState getCurrentProgram();
    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> programStateList);
    void logPrgStateExec(ProgramState prgState) throws IOException;

    ProgramState getProgramStateWithId(int currentId);

    void addProgramState(ProgramState initialProgramState);
}
