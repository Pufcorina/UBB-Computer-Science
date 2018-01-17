package Controller;

import Model.*;
import Repository.Repository;

public class Controller {
    private Repository repo;

    public Controller(Repository r){
        this.repo = r;
    }

    public void add(String type, int id, String brand, String model, String engineSize, String color) throws Exception{
        Vehicle v = null;
        type = type.toLowerCase();

        switch (type){
            case "car": v = new Car(id, brand, model, engineSize, color);
            case "bicycle": v = new Bicycle(id, model, model, engineSize, color);
            case "motorbike": v = new Motorbike(id, model, model, engineSize, color);
            default: v = new Vehicle(id, model, model, engineSize, color);
        }

        try{
            repo.add(v);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public Vehicle[] getRedVehicles(){
        Array redVehicles = new Array();
        for (Vehicle v : repo.getElements())
            if(v.getColor().toLowerCase().equals("red"))
                try{
                    redVehicles.add(v);
                } catch (DuplicateIDException e) {
                    System.out.println(e.getMessage());
                }
        return redVehicles.getElements();
    }

    public Vehicle[] getElements(){return repo.getElements();}
}
