package sample;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.ProgramState;
import model.exceptions.FileException;
import model.expressions.*;
import model.statements.*;
import model.statements.file.CloseStatement;
import model.statements.file.OpenStatement;
import model.statements.file.ReadStatement;
import model.statements.heap.NewStatement;
import model.statements.heap.WriteStatement;
import model.statements.threads.*;
import model.statements.threads.countDownLatch.AwaitStatement;
import model.statements.threads.countDownLatch.CountDownStatement;
import model.statements.threads.countDownLatch.NewLatchStatement;
import model.statements.threads.cyclicBarrier.AwaitBarrierStatement;
import model.statements.threads.cyclicBarrier.NewBarrierStatement;
import model.statements.threads.lockMethod.LockStatement;
import model.statements.threads.lockMethod.NewLockStatement;
import model.statements.threads.lockMethod.UnlockStatement;
import model.statements.threads.semaphore.NewSemaphoreStatement;
import model.statements.threads.semaphore.AcquireStatement;
import model.statements.threads.semaphore.ReleaseStatement;
import repository.IRepository;
import repository.Repository;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectController implements Initializable{
    private RunController mainWindowController;

    @FXML
    private Button executeButton;

    @FXML
    private ListView<String> programListView;

    private List<IStatement> programStatements;

    public void setMainWindowController(RunController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildProgramStatements();

        programListView.setItems(FXCollections.observableList(getStringRepresentation()));

        executeButton.setOnMouseClicked(action -> {
            int index = programListView.getSelectionModel().getSelectedIndex();

            if(index < 0)
                return;

            ProgramState initialProgramState = new ProgramState(programStatements.get(index));
            try {
                IRepository repository = new Repository("log" + index + ".txt");
                repository.addProgramState(initialProgramState);
                Controller ctrl = new Controller(repository);

                mainWindowController.setController(ctrl);
            } catch (FileException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
        });
    }

    private List<String> getStringRepresentation() {
        return programStatements.stream().map(p->"======= Program " + (programStatements.indexOf(p) + 1) + "=======\n" + p.toString()).collect(Collectors.toList());
    }

    private void buildProgramStatements() {
        IStatement ex1 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(2)),
                new PrintStatement(new VariableExpression("v")));

        IStatement ex2 =  new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new
                ArithmeticExpression(new ConstantExpression(3), new ConstantExpression(5), OperationEnum.MULTIPLY), OperationEnum.PLUS)),
                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new
                        ConstantExpression(1), OperationEnum.PLUS)), new PrintStatement(new VariableExpression("b"))));

        IStatement ex3 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new ConstantExpression(2), OperationEnum.MINUS)),
                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ConstantExpression(2)),
                        new AssignStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));

        IStatement ex4 = new CompoundStatement(new OpenStatement("var_f", "test.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(new IfStatement(new VariableExpression("var_c"),
                                        new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                                                new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0))),
                                        new CloseStatement(new VariableExpression("var_f"))))));


        IStatement ex5 = new CompoundStatement(new AssignStatement("v",
                new ConstantExpression(10)), new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new PrintStatement(new VariableExpression("v")))));


        IStatement ex6 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                                new CompoundStatement(new PrintStatement(
                                        new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("v"), OperationEnum.PLUS)),
                                        new PrintStatement(new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("a"), OperationEnum.PLUS))))));

        IStatement ex7 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new HeapReadingExpression("a")))))));

        IStatement ex8 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new CompoundStatement(new PrintStatement(new HeapReadingExpression("a")),
                                                        new AssignStatement("a", new ConstantExpression(0))))))));

        IStatement ex9 = new PrintStatement(new ArithmeticExpression(new ConstantExpression(10),
                new BooleanExpression(new ConstantExpression(2), new ConstantExpression(6), "<"), OperationEnum.PLUS));


        IStatement ex10 = new PrintStatement(new BooleanExpression(new ArithmeticExpression(new ConstantExpression(10), new ConstantExpression(2), OperationEnum.PLUS),
                new ConstantExpression(6), "<"));


        IStatement ex11 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(6)),
                new CompoundStatement(new WhileStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(4), OperationEnum.MINUS),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",
                                new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)))), new PrintStatement(new VariableExpression("v"))));

        IStatement ex12 = new CompoundStatement(new OpenStatement("var_f", "test2.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))));

        IStatement ex13 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                        new CompoundStatement(
                                new ForkStatement(new CompoundStatement(new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression("a"))))), new PrintStatement(new ArithmeticExpression(new ConstantExpression(23), new VariableExpression("c"), OperationEnum.PLUS)))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression("a"))))));

        IStatement ex14 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(0)),
                new CompoundStatement(new RepeatStatement(new CompoundStatement(new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)))), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.PLUS))), new BooleanExpression(new VariableExpression("v"), new ConstantExpression(3), "==")),
                        new CompoundStatement(new AssignStatement("x", new ConstantExpression(1)),
                                new CompoundStatement(new AssignStatement("y", new ConstantExpression(2)),
                                        new CompoundStatement(new AssignStatement("z", new ConstantExpression(3)),
                                                new CompoundStatement(new AssignStatement("w", new ConstantExpression(4)),
                                                        new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), OperationEnum.MULTIPLY))))))));

        IStatement ex15 = new CompoundStatement(new NewStatement("v1", new ConstantExpression(2)),
                new CompoundStatement(new NewStatement("v2", new ConstantExpression(3)),
                        new CompoundStatement(new NewStatement("v3", new ConstantExpression(4)),
                                new CompoundStatement(new NewLatchStatement("cnt", new HeapReadingExpression("v2")),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new PrintStatement(new HeapReadingExpression("v1")))),
                                                new CompoundStatement(new CountDownStatement("cnt"),
                                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteStatement("v2", new ArithmeticExpression(new HeapReadingExpression("v2"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new PrintStatement(new HeapReadingExpression("v2")))),
                                                                new CompoundStatement(new CountDownStatement("cnt"),
                                                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteStatement("v3", new ArithmeticExpression(new HeapReadingExpression("v3"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new PrintStatement(new HeapReadingExpression("v3")))),
                                                                                new CompoundStatement(new CountDownStatement("cnt"),
                                                                                        new CompoundStatement(new AwaitStatement("cnt"),
                                                                                                new CompoundStatement(new PrintStatement(new ConstantExpression(100)),
                                                                                                        new CompoundStatement(new CountDownStatement("cnt"), new PrintStatement(new ConstantExpression(100)))))))))))))));

        IStatement ex16 = new CompoundStatement(new NewStatement("v1", new ConstantExpression(20)),
                new CompoundStatement(new NewStatement("v2", new ConstantExpression(30)),
                        new CompoundStatement(new NewLockStatement("x"), new CompoundStatement(
                                new ForkStatement( new CompoundStatement(new ForkStatement(new CompoundStatement(new LockStatement("x"),
                                        new CompoundStatement(new WriteStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(1), OperationEnum.MINUS)),
                                                new UnlockStatement("x")))),
                                        new CompoundStatement(new LockStatement("x"),
                                                new CompoundStatement(new WriteStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(1), OperationEnum.PLUS)),
                                                        new UnlockStatement("x"))))),
                                new CompoundStatement(new NewLockStatement("q"),
                                        new CompoundStatement(new ForkStatement(
                                                new CompoundStatement(new ForkStatement(new CompoundStatement(new LockStatement("q"),
                                                        new CompoundStatement(new WriteStatement("v2", new ArithmeticExpression(new HeapReadingExpression("v2"), new ConstantExpression(5), OperationEnum.PLUS)), new UnlockStatement("q")))),
                                                        new CompoundStatement(new AssignStatement("m", new ConstantExpression(100)),
                                                                new CompoundStatement(new LockStatement("q"),new CompoundStatement(new WriteStatement("v2", new ArithmeticExpression(new HeapReadingExpression("v2"), new ConstantExpression(1), OperationEnum.PLUS)),new UnlockStatement("q")))))),
                                                new CompoundStatement(new AssignStatement("z", new ConstantExpression(200)),
                                                        new CompoundStatement(new AssignStatement("z", new ConstantExpression(300)),
                                                                new CompoundStatement(new AssignStatement("z", new ConstantExpression(400)),
                                                                        new CompoundStatement(new LockStatement("x"),
                                                                                new CompoundStatement(new PrintStatement(new HeapReadingExpression("v1")),
                                                                                        new CompoundStatement(new UnlockStatement("x"),
                                                                                                new CompoundStatement(new LockStatement("q"),
                                                                                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression("v2")),
                                                                                                                new UnlockStatement("q")))))))))))))));
        IStatement ex17 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new ForStatement("v", new ConstantExpression(0), new ConstantExpression(3), new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.PLUS),
                        new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.PLUS))))),
                        new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), OperationEnum.MULTIPLY))));

        IStatement ex18 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)),
                        new CompoundStatement(new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)),
                                new PrintStatement(new VariableExpression("v"))))),
                        new CompoundStatement(new SleepStatement(10), new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), OperationEnum.MULTIPLY)))));

        IStatement ex19 = new CompoundStatement(new AssignStatement("a", new ConstantExpression(1)),
                new CompoundStatement(new AssignStatement("b", new ConstantExpression(2)),
                        new CompoundStatement(new AssignStatement("c", new ConstantExpression(5)),
                                new CompoundStatement(new Switch(new ArithmeticExpression(new VariableExpression("a"), new ConstantExpression(10), OperationEnum.MULTIPLY),
                                        new ArithmeticExpression(new VariableExpression("b"), new VariableExpression("c"), OperationEnum.MULTIPLY), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b"))),
                                        new ConstantExpression(10), new CompoundStatement(new PrintStatement(new ConstantExpression(100)), new PrintStatement(new ConstantExpression(300))),
                                        new PrintStatement(new ConstantExpression(300))),
                                        new PrintStatement(new ConstantExpression(300))))));

        IStatement ex20 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(1)),
                new CompoundStatement(new ForkStatement(new AssignStatement("v", new ConstantExpression(2))),
                        new ForkStatement(new AssignStatement("v", new ConstantExpression(3)))));

        IStatement ex21 = new CompoundStatement(
                new NewStatement("v1", new ConstantExpression(2)), new CompoundStatement(
                        new NewStatement("v2", new ConstantExpression(3)), new CompoundStatement(
                                new NewStatement("v3", new ConstantExpression(4)), new CompoundStatement(
                                        new NewBarrierStatement("cnt", new HeapReadingExpression("v2")), new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new AwaitBarrierStatement("cnt"), new CompoundStatement(
                                                                        new WriteStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new PrintStatement(new HeapReadingExpression("v1")))
                                                        )
                                                        ), new CompoundStatement(
                                                                new ForkStatement(
                                                                        new CompoundStatement(
                                                                                new AwaitBarrierStatement("cnt"), new CompoundStatement(
                                                                                new WriteStatement("v2", new ArithmeticExpression(new HeapReadingExpression("v2"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new CompoundStatement(
                                                                                new WriteStatement("v2", new ArithmeticExpression(new HeapReadingExpression("v2"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new PrintStatement(new HeapReadingExpression("v2"))
                                                                        )
                                                                        )
                                                                        )
                                                                ), new CompoundStatement(
                                                                        new AwaitBarrierStatement("cnt"), new PrintStatement(new HeapReadingExpression("v3"))
        )
        )
        )
        )
        )
        )
        );

        IStatement ex22 = new CompoundStatement(new NewSemaphoreStatement("v", new ConstantExpression(2)),
                new CompoundStatement(
                        new ForkStatement(
                                new CompoundStatement(
                                        new AcquireStatement("v"), new CompoundStatement(
                                                new PrintStatement(new ConstantExpression(1)), new ReleaseStatement("v")
                                )
                                )
                        ), new CompoundStatement(
                            new ForkStatement(
                                    new CompoundStatement(
                                            new AcquireStatement("v"), new CompoundStatement(
                                            new PrintStatement(new ConstantExpression(2)), new ReleaseStatement("v")
                                    )
                                    )
                            ),
                                new ForkStatement(
                                        new CompoundStatement(
                                                new AcquireStatement("v"), new CompoundStatement(
                                                new PrintStatement(new ConstantExpression(3)), new ReleaseStatement("v")
                                        )
                                        )
                                )
                )
                ));

        IStatement ex23 = new CompoundStatement(
                new NewStatement("v1", new ConstantExpression(1)), new CompoundStatement(
                        new NewSemaphoreStatement("cnt", new HeapReadingExpression("v1")), new CompoundStatement(
                                new ForkStatement(
                                        new CompoundStatement(
                                                new AcquireStatement("cnt"), new CompoundStatement(
                                                        new WriteStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new CompoundStatement(
                                                                new PrintStatement(new HeapReadingExpression("v1")), new ReleaseStatement("cnt")
                                        )
                                        )
                                        )
                                ), new CompoundStatement(
                                        new ForkStatement(
                                                new CompoundStatement(
                                                        new AcquireStatement("cnt"), new CompoundStatement(
                                                                new WriteStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(10), OperationEnum.MULTIPLY)), new CompoundStatement(
                                                                        new WriteStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(2), OperationEnum.MULTIPLY)), new CompoundStatement(
                                                                                new PrintStatement(new HeapReadingExpression("v1")), new ReleaseStatement("cnt")
                                                )
                                                )
                                                )
                                                )
                                        ), new CompoundStatement(
                                                new AcquireStatement("cnt"), new CompoundStatement(
                                                        new PrintStatement(new ArithmeticExpression(new HeapReadingExpression("v1"), new ConstantExpression(1), OperationEnum.MINUS)), new ReleaseStatement("cnt")
        )
        )
        )
        )
        )
        );

        IStatement ex24 = new CompoundStatement(
                new AssignStatement("a", new ConstantExpression(1)), new CompoundStatement(
                        new AssignStatement("b", new ConstantExpression(2)), new CompoundStatement(
                                new ConditionalAssignmentStatement("c", new VariableExpression("a"), new ConstantExpression(100), new ConstantExpression(200)), new CompoundStatement(
                                        new PrintStatement(new VariableExpression("c")), new CompoundStatement(
                                                new ConditionalAssignmentStatement("c", new ArithmeticExpression(new VariableExpression("b"), new ConstantExpression(2), OperationEnum.MINUS), new ConstantExpression(100), new ConstantExpression(200)),
                new PrintStatement(new VariableExpression("c")))
        )
        )
        )
        );

        programStatements = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10,
                ex11, ex12, ex13, ex14, ex15, ex16, ex17, ex18, ex19, ex20,
                ex21, ex22, ex23, ex24));
    }
}
