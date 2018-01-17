package Repository;

import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRepository extends Repository {
    private String filename;

    public FileRepository(String file)
    {
        super();
        this.filename = file;
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(filename);

        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String[] split = scan.nextLine().split(",");
                Vehicle v = null;
                split[0] = split[0].toLowerCase();

                switch (split[0]){
                    case "car": v = new Car(Integer.parseInt(split[1]), split[2], split[3], split[4], split[5]); break;
                    case "bicycle": v = new Bicycle(Integer.parseInt(split[1]), split[2], split[3], split[4], split[5]); break;
                    case "motorbike": v = new Motorbike(Integer.parseInt(split[1]), split[2], split[3], split[4], split[5]); break;
                    default: v = new Vehicle(Integer.parseInt(split[1]), split[2], split[3], split[4], split[5]); break;
                }

                try{
                    super.add(v);
                } catch (DuplicateIDException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
