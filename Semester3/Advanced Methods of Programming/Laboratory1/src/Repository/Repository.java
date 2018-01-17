package Repository;

import Model.Array;
import Model.DuplicateIDException;
import Model.Vehicle;

public class Repository {
    private Array vect;

    public Repository(){
        vect = new Array();
    }

    public Vehicle[] getElements(){return vect.getElements();}

    public void add(Vehicle v) throws DuplicateIDException{
        for(Vehicle vehicul : vect.getElements())
            if(v.equals(vehicul))
                throw new DuplicateIDException("Vehicle already added!");
        vect.add(v);
    }
}
