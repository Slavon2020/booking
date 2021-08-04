package controller;

import destination.Destination;
import model.Flight;
import service.FlightService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightController {
    FlightService flightService = new FlightService();

    public FlightController() throws URISyntaxException, IOException {
    }

    public List<Flight> getAllFlights() throws IOException, ClassNotFoundException {
        return flightService.getAllFlights();
    }
    public List<Flight> getFilteredFlight(Predicate<Flight> predicate) throws IOException, ClassNotFoundException {
        return flightService.getFilteredFlight(predicate);
    }

    public Flight getFlightById(int id) throws IOException, ClassNotFoundException {
        return flightService.getFlightById(id);
    }

    public List<Flight> findFlights(Destination destination, LocalDate localDate, int passengersNum) throws IOException, ClassNotFoundException {
        return flightService.findFlights(destination, localDate, passengersNum);
    }

    public List<Flight> getClosesFlights() throws IOException, ClassNotFoundException {
        return flightService.getClosesFlights();
    }
    public void addFlight(Flight flight) throws IOException, ClassNotFoundException {
        flightService.addFlight(flight);
    }
    public void addListFlights(List<Flight> flights) throws IOException, ClassNotFoundException {
        flightService.addListFlights(flights);
    }
    public void decreaseFreeTickets(int id, int countTickets) throws IOException, ClassNotFoundException {
        flightService.decreaseFreeTickets(id, countTickets);
    }
    public void increaseFreeTickets(int id, int countTickets) throws IOException, ClassNotFoundException {
        flightService.increaseFreeTickets(id, countTickets);
    }
}
