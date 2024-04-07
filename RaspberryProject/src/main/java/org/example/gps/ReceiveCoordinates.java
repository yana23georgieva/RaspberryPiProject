package org.example.gps;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReceiveCoordinates {
    private String filePath;
    private String gpgll;
    private int Latitude;
    private char LatitudeDirection;
    private int Longitude;
    private char LongitudeDirection;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        Latitude = latitude;
    }

    public int getLongitude() {
        return Longitude;
    }

    public void setLongitude(int longitude) {
        Longitude = longitude;
    }

    public char getLatitudeDirection() {
        return LatitudeDirection;
    }

    public void setLatitudeDirection(char latitudeDirection) {
        LatitudeDirection = latitudeDirection;
    }

    public char getLongitudeDirection() {
        return LongitudeDirection;
    }

    public void setLongitudeDirection(char longitudeDirection) {
        LongitudeDirection = longitudeDirection;
    }

    public void readFile() throws IOException {
        Path file = Paths.get(filePath);

        InputStream stream = Files.newInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (line.contains("GPGLL")) {
                gpgll = line;
                break;
            }
        }
    }

    // Specification - https://docs.novatel.com/OEM7/Content/Logs/GPGLL.htm#NMEAPositioningSystemModeIndicator
    public void receiveCoordinates() {
        if (gpgll == null) {
            throw new IllegalArgumentException("GPGLL not found!");
        }

        String[] data = gpgll.split(",");
        setLatitude(Integer.parseInt(data[1].substring(0, 2)));
        setLatitudeDirection(data[2].charAt(0));
        setLongitude(Integer.parseInt(data[3].substring(0, 3)));
        setLongitudeDirection(data[4].charAt(0));

        if (!data[6].equals("A")) {
            throw new IllegalArgumentException("Data is Invalid!");
        }
    }
}
