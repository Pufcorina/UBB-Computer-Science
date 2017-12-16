package View;

import Collection.List.MyList;
import Controller.Controller;
import Model.Exceptions.BadInputException;
import Model.Exceptions.ToyLanguageInterpreterException;
import Model.ProgramState;
import Model.Statement.IStatement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Console {
    private Controller ctrl;
    private MyList<IStatement> statements;

    public Console(Controller ctrl, MyList<IStatement> statements){
        this.ctrl = ctrl;
        this.statements = statements;
    }

    private void printMenu(){
        System.out.println("\n=========Toy Language Interpretor=========");
        for (int i = 0; i < statements.size(); i++)
        {
            System.out.println(String.format("%d: %s", i, statements.get(i).toString()));
        }
        System.out.println("-1: Exit.");
    }

    private int getInteger(Scanner scanner) throws BadInputException{
        try {
            return Integer.parseInt((scanner.nextLine()));
        }
        catch (NumberFormatException e){
            throw new BadInputException("Invalid integer");
        }
    }

    private void infiniteLoop(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            printMenu();
            System.out.print("Choose one oprion: ");
            int option = -1;
            try{
                option = getInteger(scanner);
                if(option == -1) break;
                if(option < statements.size()){
                    try{
                        //ctrl.setProgram(new ProgramState(statements.get(option)));
                        ctrl.allSteps();
                    } catch (InterruptedException | ToyLanguageInterpreterException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (BadInputException e){
                System.out.print(e.getMessage());
            }


        }

        scanner.close();
    }

    public void runApp(){
        infiniteLoop();
    }
}
