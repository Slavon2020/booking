package dao;

import interfaces.FlightDao;
import model.Flight;
import utils.Utils;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDao {
    String fileName = "flightsDb.txt";
//    File file = FlightDaoImpl.getFileFromResource(fileName);
    File file = new File("src/main/resources/flightsDb.txt");
    public FlightDaoImpl() throws URISyntaxException, IOException {
    }

//    private static File getFileFromResource(String fileName) throws URISyntaxException, IOException {
//        ClassLoader classLoader = FlightDaoImpl.class.getClassLoader();
//        URL resource = classLoader.getResource(fileName);
//        if (resource == null) {
//            throw new IllegalArgumentException("file not found! " + fileName);
//        } else {
//            // failed if files have whitespaces or special characters
//            //return new File(resource.getFile());
//            File file = new File(resource.toURI());
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(Utils.getRandomFlightsList());
//            return file;
//        }
//
//    }

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
