import org.example.gps.ReceiveCoordinates;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

public class ReceiveCoordinatesTests {
    @Test
    public void checkOpenFile() {
        ReceiveCoordinates gps = new ReceiveCoordinates();
        gps.setFilePath("input.txt");

        try {
            gps.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void checkCoordinates() {
        ReceiveCoordinates gps = new ReceiveCoordinates();
        gps.setFilePath("input.txt");

        try {
            gps.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gps.receiveCoordinates();

        assert(gps.getLatitude() > 40 && gps.getLatitude() < 44);
        assert(gps.getLongitude() > 23 && gps.getLongitude() < 26);
    }
}
