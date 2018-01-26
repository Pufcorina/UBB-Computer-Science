package View;

import Controller.Controller;
import Model.Exceptions.ToyLanguageInterpreterException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RunCommand extends Command{
    private Controller controller;

    public RunCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try{
            controller.allSteps();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
