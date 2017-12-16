import Controller.Controller;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Statement.File.CloseStatement;
import Model.Statement.File.OpenStatement;
import Model.Statement.File.ReadStatement;
import Model.Statement.Heap.NewStatement;
import Model.Statement.Heap.WriteStatement;
import Repository.IRepository;
import Repository.Repository;
import View.ExitCommand;
import View.RunCommand;
import View.TextMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IStatement ex1 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(2)),
                new PrintStatement(new VariableExpression("v")));
        List<ProgramState> prg1 = new ArrayList<>();
        prg1.add(new ProgramState(ex1));
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);

        IStatement ex2 =  new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new
                ArithmeticExpression(new ConstantExpression(3), new ConstantExpression(5), OperationEnum.MULTIPLY), OperationEnum.PLUS)),
                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new
                        ConstantExpression(1), OperationEnum.PLUS)), new PrintStatement(new VariableExpression("b"))));
        List<ProgramState> prg2 = new ArrayList<>();
        prg2.add(new ProgramState(ex2));
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctrl2 = new Controller(repo2);

        IStatement ex3 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new ConstantExpression(2), OperationEnum.MINUS)),
                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ConstantExpression(2)),
                        new AssignStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));
        List<ProgramState> prg3 = new ArrayList<>();
        prg3.add(new ProgramState(ex3));
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctrl3 = new Controller(repo3);

        IStatement ex4 = new CompoundStatement(new OpenStatement("var_f", "test.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(new IfStatement(new VariableExpression("var_c"),
                                        new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                                                new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0))),
                                        new CloseStatement(new VariableExpression("var_f"))))));
        List<ProgramState> prg4 = new ArrayList<>();
        prg4.add(new ProgramState(ex4));
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);


        IStatement ex5 = new CompoundStatement(new AssignStatement("v",
                new ConstantExpression(10)), new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new PrintStatement(new VariableExpression("v")))));
        List<ProgramState> prg5 = new ArrayList<>();
        prg5.add(new ProgramState(ex5));
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctrl5 = new Controller(repo5);


        IStatement ex6 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                                new CompoundStatement(new PrintStatement(
                                        new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("v"), OperationEnum.PLUS)),
                                        new PrintStatement(new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("a"), OperationEnum.PLUS))))));
        List<ProgramState> prg6 = new ArrayList<>();
        prg6.add(new ProgramState(ex6));
        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctrl6 = new Controller(repo6);

        IStatement ex7 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new HeapReadingExpression("a")))))));
        List<ProgramState> prg7 = new ArrayList<>();
        prg7.add(new ProgramState(ex7));
        IRepository repo7 = new Repository(prg7, "log7.txt");
        Controller ctrl7 = new Controller(repo7);

        IStatement ex8 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new CompoundStatement(new PrintStatement(new HeapReadingExpression("a")),
                                                        new AssignStatement("a", new ConstantExpression(0))))))));
        List<ProgramState> prg8 = new ArrayList<>();
        prg8.add(new ProgramState(ex8));
        IRepository repo8 = new Repository(prg8, "log8.txt");
        Controller ctrl8 = new Controller(repo8);

        IStatement ex9 = new PrintStatement(new ArithmeticExpression(new ConstantExpression(10),
                new BooleanExpression(new ConstantExpression(2), new ConstantExpression(6), "<"), OperationEnum.PLUS));
        List<ProgramState> prg9 = new ArrayList<>();
        prg9.add(new ProgramState(ex9));
        IRepository repo9 = new Repository(prg9, "log9.txt");
        Controller ctrl9 = new Controller(repo9);


        IStatement ex10 = new PrintStatement(new BooleanExpression(new ArithmeticExpression(new ConstantExpression(10), new ConstantExpression(2), OperationEnum.PLUS),
                new ConstantExpression(6), "<"));
        List<ProgramState> prg10 = new ArrayList<>();
        prg10.add(new ProgramState(ex10));
        IRepository repo10 = new Repository(prg10, "log10.txt");
        Controller ctrl10 = new Controller(repo10);


        IStatement ex11 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(6)),
                new CompoundStatement(new WhileStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(4), OperationEnum.MINUS),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",
                                new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)))), new PrintStatement(new VariableExpression("v"))));
        List<ProgramState> prg11 = new ArrayList<>();
        prg11.add(new ProgramState(ex11));
        IRepository repo11 = new Repository(prg11, "log11.txt");
        Controller ctrl11 = new Controller(repo11);

       /* IStatement ex12 = new CompoundStatement(new OpenStatement("var_f2", "test2.in"),new CompoundStatement(new OpenStatement("var_f", "test.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(new IfStatement(new VariableExpression("var_c"),
                                        new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                                                new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0))),
                                        new CloseStatement(new VariableExpression("var_f")))))));*/

        IStatement ex12 = new CompoundStatement(new OpenStatement("var_f", "test2.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))));
        List<ProgramState> prg12 = new ArrayList<>();
        prg12.add(new ProgramState(ex12));
        IRepository repo12 = new Repository(prg12, "log12.txt");
        Controller ctrl12 = new Controller(repo12);


        IStatement ex13 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                        new CompoundStatement(
                                new ForkStatement(new CompoundStatement(new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression("a"))))), new PrintStatement(new ArithmeticExpression(new ConstantExpression(23), new VariableExpression("c"), OperationEnum.PLUS)))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression("a"))))));
        List<ProgramState> prg13 = new ArrayList<>();
        prg13.add(new ProgramState(ex13));
        IRepository repo13 = new Repository(prg13, "log13.txt");
        Controller ctrl13 = new Controller(repo13);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunCommand("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunCommand("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunCommand("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunCommand("4", ex4.toString(), ctrl4));
        menu.addCommand(new RunCommand("5", ex5.toString(), ctrl5));
        menu.addCommand(new RunCommand("6", ex6.toString(), ctrl6));
        menu.addCommand(new RunCommand("7", ex7.toString(), ctrl7));
        menu.addCommand(new RunCommand("8", ex8.toString(), ctrl8));
        menu.addCommand(new RunCommand("9", ex9.toString(), ctrl9));
        menu.addCommand(new RunCommand("10", ex10.toString(), ctrl10));
        menu.addCommand(new RunCommand("11", ex11.toString(), ctrl11));
        menu.addCommand(new RunCommand("12", ex12.toString(), ctrl12));
        menu.addCommand(new RunCommand("13", ex13.toString(), ctrl13));
        menu.show();
    }
}
