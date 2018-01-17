package View;

import Controller.Controller;
import Model.Vehicle;

import java.util.Scanner;

public class Console {
    private Controller ctrl;

    public Console(Controller c){
        this.ctrl = c;
    }

    public void runApplication()
    {
        int option = 3;
        Scanner scan = new Scanner(System.in);
        while (true)
        {
            showMenu();
            option = scan.nextInt();

            switch (option){
                case 1: showAll();
                case 2: showRed();
                case 0: return;
                default: System.out.println("Option is not available yet!");
            }
            scan.close();
        }
    }

    private void showRed() {
        Vehicle[] elements = ctrl.getRedVehicles();
        System.out.println(String.format("We found %d red vehicles", elements.length));
        for (Vehicle v : elements)
            System.out.println(v);
    }

    private void showAll() {
        Vehicle[] elements = ctrl.getElements();
        System.out.println(String.format("We found %d vehicles", elements.length));
        for (Vehicle v : elements)
            System.out.println(v);
    }

    private void showMenu() {
        System.out.println("Choose an option:");
        System.out.println("0. EXIT");
        System.out.println("1. Show all vehicles");
        System.out.println("2. Show all red vehicles");
    }
}
