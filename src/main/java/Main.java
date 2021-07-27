import controller.FlightController;
import dao.FlightDaoImpl;
import destination.Destination;
import interfaces.FlightDao;
import model.Flight;
import service.FlightService;
import utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {
        List<Flight> generatedFlightsList = Utils.getRandomFlightsList();
        FlightDao flightDao = new FlightDaoImpl();
        flightDao.setFlights(generatedFlightsList);

    }
}
