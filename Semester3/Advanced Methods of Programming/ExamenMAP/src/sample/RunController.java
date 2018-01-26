package sample;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Pair;
import model.ProgramState;
import model.exceptions.ADTEmptyException;
import model.statements.IStatement;
import model.util.MyFilePair;
import myCollections.MyIDictionary;
import myCollections.MyIStack;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class RunController implements Initializable{
    private Controller ctrl;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> heapTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> heapAddressTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> heapValueTableColumn;

    @FXML
    private ListView<Integer> outputListView;

    @FXML
    private TableView<Map.Entry<Integer, String>> fileTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> fileIdentifierTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> fileNameTableColumn;

    @FXML
    private ListView<Integer> programStateIdentifiersListView;

    @FXML
    private TableView<Map.Entry<String, Integer>> symbolTableView;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> symbolNameTableColumn;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> symbolValueTableColumn;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> latchTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> valueLatchTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> locationLatchTableColumn;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> lockTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> valueLockTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> locationLockTableColumn;

    @FXML
    private TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> barrierTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> indexBarrierTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> valueBarrierTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, List<Integer>> listBarrierTableColumn;

    @FXML
    private TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> semaphorTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> indexSemaphorTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> valueSemaphorTableColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, List<Integer>> listSemaphorTableColumn;

    @FXML
    private TextField numberOfProgramStatesTextField;

    @FXML
    private Button runOneStepButton;

    @FXML
    private ListView<String> executionStackListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        heapAddressTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapValueTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        locationLatchTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueLatchTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        locationLockTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueLockTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        indexBarrierTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueBarrierTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue().getKey()).asObject());
        listBarrierTableColumn.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getValue().getValue()));

        indexSemaphorTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueSemaphorTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue().getKey()).asObject());
        listSemaphorTableColumn.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getValue().getValue()));


        fileIdentifierTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        fileNameTableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

        symbolNameTableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        symbolValueTableColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        programStateIdentifiersListView.setOnMouseClicked(mouseEvent -> { changeProgramState(getCurrentProgramState()); });

        runOneStepButton.setOnMouseClicked(mouseEvent -> {executeOneStep();});
    }

    private void executeOneStep() {
        if(ctrl == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try {
            ProgramState state = getCurrentProgramState();
            if(state == null)
                throw new ADTEmptyException("Stack empty!");
            boolean programStateLeft = state.getExecutionStack().isEmpty();
            if (programStateLeft) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            try {
                ctrl.oneStepForAllPrograms(ctrl.getRepository().getProgramStateList());
                System.out.println(ctrl.getRepository().getCurrentProgram().toString());
                changeProgramState(getCurrentProgramState());
                populateProgramStateIdentifiers();
            } catch (InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProgramState(ProgramState currentProgramState) {
        if(currentProgramState == null)
            return;
        populateProgramStateIdentifiers();
        populateExecutionStack(currentProgramState);
        populateSymbolTable(currentProgramState);
        populateOutput(currentProgramState);
        populateFileTable(currentProgramState);
        populateHeapTable(currentProgramState);
        populateLatchTable(currentProgramState);
        populateLockTable(currentProgramState);
        populateBarrierTable(currentProgramState);
        populateSemaphorTable(currentProgramState);
    }

    private void populateSemaphorTable(ProgramState currentProgramState) {
        MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphorTable = currentProgramState.getSemaphoreTable().getSemaphore();
        List<Map.Entry<Integer, Pair<Integer, List<Integer>>>> semaphorList = new ArrayList<>();

        for (Map.Entry<Integer, Pair<Integer, List<Integer>>> entry : semaphorTable.getAll())
            semaphorList.add(entry);
        semaphorTableView.setItems(FXCollections.observableList(semaphorList));
        semaphorTableView.refresh();
    }

    private void populateBarrierTable(ProgramState currentProgramState) {
        MyIDictionary<Integer, Pair<Integer, List<Integer>>> barrierTable = currentProgramState.getBarrierTable();
        List<Map.Entry<Integer, Pair<Integer, List<Integer>>>> barrierList = new ArrayList<>();

        for (Map.Entry<Integer, Pair<Integer, List<Integer>>> entry : barrierTable.getAll())
            barrierList.add(entry);
        barrierTableView.setItems(FXCollections.observableList(barrierList));
        barrierTableView.refresh();
    }

    private void populateLockTable(ProgramState currentProgramState) {
        MyIDictionary<Integer, Integer> lockTable = currentProgramState.getLockTable();

        List<Map.Entry<Integer, Integer>> lockList = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : lockTable.getAll())
            lockList.add(entry);

        lockTableView.setItems(FXCollections.observableList(lockList));
        lockTableView.refresh();
    }

    private void populateLatchTable(ProgramState currentProgramState) {
        MyIDictionary<Integer, Integer> latchTable = currentProgramState.getLatchTable();

        List<Map.Entry<Integer, Integer>> latchList = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : latchTable.getAll())
            latchList.add(entry);

        latchTableView.setItems(FXCollections.observableList(latchList));
        latchTableView.refresh();
    }

    private void populateHeapTable(ProgramState currentProgramState) {
        MyIDictionary<Integer, Integer> heapTable = currentProgramState.getHeapTable();

        List<Map.Entry<Integer, Integer>> heapList = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : heapTable.getAll())
            heapList.add(entry);

        heapTableView.setItems(FXCollections.observableList(heapList));
        heapTableView.refresh();
    }

    private void populateFileTable(ProgramState currentProgramState) {
        MyIDictionary<Integer, MyFilePair> fileTable = currentProgramState.getFileTable();

        HashMap<Integer, String> fileDictionary = new HashMap<>();

        for(Map.Entry<Integer, MyFilePair> entry : fileTable.getAll())
            fileDictionary.put(entry.getKey(), entry.getValue().getFileName());

        List<Map.Entry<Integer, String>> fileList = new ArrayList<>();
        fileList.addAll(fileDictionary.entrySet());

        fileTableView.setItems(FXCollections.observableList(fileList));
        fileTableView.refresh();
    }

    private void populateOutput(ProgramState currentProgramState) {
        outputListView.setItems(FXCollections.observableList(currentProgramState.getOutputList()));
        outputListView.refresh();
    }

    private void populateSymbolTable(ProgramState currentProgramState) {
        MyIDictionary<String, Integer> symbolTable = currentProgramState.getSymbolTable();

        List<Map.Entry<String, Integer>> symbolTableList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : symbolTable.getAll())
            symbolTableList.add(entry);

        symbolTableView.setItems(FXCollections.observableList(symbolTableList));
        symbolTableView.refresh();
    }

    private void populateExecutionStack(ProgramState currentProgramState) {
        MyIStack<IStatement> executionStack = currentProgramState.getExecutionStack();

        List<String> executionStackList = new ArrayList<>();

        for(IStatement statement : executionStack.getAll())
            executionStackList.add(0,statement.toString());

        executionStackListView.setItems(FXCollections.observableList(executionStackList));
        executionStackListView.refresh();
    }

    private ProgramState getCurrentProgramState() {
        if(programStateIdentifiersListView.getSelectionModel().getSelectedIndex() == -1)
            return null;

        int currentId = programStateIdentifiersListView.getSelectionModel().getSelectedItem();
        return ctrl.getRepository().getProgramStateWIthId(currentId);
    }

    void setController(Controller ctrl) {
        this.ctrl = ctrl;
        populateProgramStateIdentifiers();
    }

    private void populateProgramStateIdentifiers() {
        List<ProgramState> programStates = ctrl.getRepository().getProgramStateList();
        programStateIdentifiersListView.setItems(FXCollections.observableList(getProgramStateIds(programStates)));

        numberOfProgramStatesTextField.setText("" + programStates.size());
    }

    private List<Integer> getProgramStateIds(List<ProgramState> programStates) {
        return programStates.stream().map(ProgramState::getIdThread).collect(Collectors.toList());
    }
}
