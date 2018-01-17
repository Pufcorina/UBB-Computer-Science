package Model;

public class Array {
    private Vehicle[] elements;
    private int total;

    public Array()
    {
        total = 0;
        this.elements = new Vehicle[total];
    }

    public void add(Vehicle v) throws DuplicateIDException{
        for(Vehicle vehicul : elements)
            if(v.equals(vehicul))
                throw new DuplicateIDException("Vehicle not unique!");
        Vehicle[] new_elems = new Vehicle[total + 1];
        System.arraycopy(elements, 0, new_elems, 0, total);
        new_elems[total] = v;
        elements = new Vehicle[total + 1];
        elements = new_elems;
        total++;
    }

    public int size(){return total;}

    public Vehicle[] getElements() {
        return elements;
    }
}
