package Model;

public class Bicycle extends Vehicle {
    public Bicycle(int id, String brand, String model, String engineSize, String color){
        super(id, brand, model, engineSize, color);
    }
    @Override
    public String toString(){
        return "Bicycle: " + super.toString();
    }
}
