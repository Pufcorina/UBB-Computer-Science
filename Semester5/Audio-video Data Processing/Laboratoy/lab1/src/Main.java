import domain.PPM;
import domain.Encoder;
import domain.Decoder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            PPM image = new PPM("./data/nt-P3.ppm");
            Encoder encoder = new Encoder(image);
            encoder.printMatrixes();
            Decoder decoder = new Decoder(encoder);
            decoder.getImage().getImageConvertedToRGB().writePPM("final.ppm");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
