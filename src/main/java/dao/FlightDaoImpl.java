package dao;

import interfaces.FlightDao;
import model.Flight;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDao {
    String fileName = "flightsDb.txt";
    File file = FlightDaoImpl.getFileFromResource(fileName);

    public FlightDaoImpl() throws URISyntaxException {
    }

    private static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = FlightDaoImpl.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());
            return new File(resource.toURI());
        }

    }

    @Override
    public void setFlights(List<Flight> flights) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(flights);
    }

    @Override
    public List<Flight> getAllFlights() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream);
        List<Flight> flights = (ArrayList<Flight>) objectOutputStream.readObject();
        return flights;
    }

}
