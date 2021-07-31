package service;

import dao.FlightDaoImpl;
import destination.Destination;
import interfaces.FlightDao;
import model.Flight;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightService {
    FlightDao flightDao = new FlightDaoImpl();

    public FlightService() throws URISyntaxException, IOException {
    }

    public List<Flight> getAllFlights() throws IOException, ClassNotFoundException {
        return flightDao.getAllFlights();
    }

    public List<Flight> getFilteredFlight(Predicate<Flight> predicate) throws IOException, ClassNotFoundException {
          return flightDao.getAllFlights().stream().filter(predicate).collect(Collectors.toList());
    }

    public Flight getFlightById(int id) throws IOException, ClassNotFoundException {
        List<Flight> allFlights = flightDao.getAllFlights();
        for (Flight flight : allFlights) {
            if (flight.getId() == id) {
                return flight;
            }
        }
        return null;
    }

    public List<Flight> findFlights(Destination destination, LocalDate localDate, int passengersNum) throws IOException, ClassNotFoundException {
        return getFilteredFlight(flight -> ((flight.getDestination() == destination) &&
                    (flight.getDateTime().toLocalDate().equals(localDate)) &&
                    (flight.getFreeTickets() >= passengersNum))
        );
    }

    public List<Flight> getClosesFlights() throws IOException, ClassNotFoundException {
        return getFilteredFlight(flight -> flight.getDateTime().toLocalDate().equals(LocalDate.now()));
    }

    public void addFlight(Flight flight) throws IOException, ClassNotFoundException {
        List<Flight> allFlights = getAllFlights();
        int index = allFlights.indexOf(flight);
        if (index == -1) {
            allFlights.add(flight);
        } else {
            allFlights.set(index, flight);
        }
        flightDao.setFlights(allFlights);
    }
    public void addListFlights(List<Flight> flights) throws IOException, ClassNotFoundException {
        List<Flight> allFlights = getAllFlights();
        allFlights.addAll(flights);
        flightDao.setFlights(allFlights);
    }
    public void decreaseFreeTickets (int id, int countTickets) throws IOException, ClassNotFoundException {
        Flight flightById = getFlightById(id);
        Flight flight = new Flight(flightById.getId(), flightById.getFreeTickets() - countTickets, flightById.getDateTime(), flightById.getDestination());
        addFlight(flight);
    }


}
