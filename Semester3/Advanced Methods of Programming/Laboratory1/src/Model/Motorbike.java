package Model;

public class Motorbike extends Vehicle {
    public Motorbike(int id, String brand, String model, String engineSize, String color){
        super(id, brand, model, engineSize, color);
    }
    @Override
    public String toString(){
        return "Motorbike: " + super.toString();
    }
}
