package Model;

public class Car extends Vehicle{
    public Car(int id, String brand, String model, String engineSize, String color){
        super(id, brand, model, engineSize, color);
    }
    @Override
    public String toString(){
        return "Car: " + super.toString();
    }
}
