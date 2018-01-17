package Model;

public class Vehicle {
    private int idVehicle;
    private String brand;
    private String model;
    private String engineSize;
    private String color;

    public Vehicle(int id, String b, String m, String e, String c)
    {
        this.idVehicle = id;
        this.brand = b;
        this.model = m;
        this.engineSize = e;
        this.color = c;
    }

    public int getIdVehicle(){
        return this.idVehicle;
    }
    public void setIdVehicle(int id)    {
        this.idVehicle = id;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineSize() {
        return engineSize;
    }
    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString()
    {
        return "id: " + idVehicle +
                ", brand: " + brand +
                "model: " + model +
                "engine size" + engineSize +
                "color: " + color;
    }

    @Override
    public boolean equals(Object o){
        Vehicle vehicul = (Vehicle)o;
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        return idVehicle == vehicul.idVehicle;
    }
}
