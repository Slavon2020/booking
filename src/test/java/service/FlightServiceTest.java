package service;

import dao.FlightDaoImpl;
import destination.Destination;
import interfaces.FlightDao;
import model.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FlightServiceTest {
    LocalDateTime now = LocalDateTime.now();
    Flight flight1 = new Flight(13, 7, now, Destination.BERLIN);
    Flight flight2 = new Flight(7, 13, now, Destination.PARIS);
    List<Flight> flights = new ArrayList<>();

    @Test
    public void should_getAllFlights_when_hasFlights() throws IOException, ClassNotFoundException, URISyntaxException {
        //given
        flights.add(flight1);
        flights.add(flight2);
        FlightDao flightDao = mock(FlightDaoImpl.class);
        when(flightDao.getAllFlights()).thenReturn(flights);

        //when
        FlightService flightService = new FlightService(flightDao);
        List<Flight> allFlights = flightService.getAllFlights();
        //then
        assertArrayEquals(flights.toArray(), allFlights.toArray());
    }

    @Test
    public void should_getFilteredFlight_when_foundFlights() throws IOException, ClassNotFoundException, URISyntaxException {
        //given
        flights.add(flight1);
        flights.add(flight2);
        FlightDao flightDao = mock(FlightDaoImpl.class);
        when(flightDao.getAllFlights()).thenReturn(flights);

        //when
        FlightService flightService = new FlightService(flightDao);
        List<Flight> allFlights = flightService.getFilteredFlight( e->e.getFreeTickets()<10);
        List<Flight> result = new ArrayList<>();
        result.add(flight1);
        //then
        assertArrayEquals(result.toArray(), allFlights.toArray());
    }
    @Test
    public void should_getFlightById_when_hasFlight() throws URISyntaxException, IOException, ClassNotFoundException {
        //given
        flights.add(flight1);
        flights.add(flight2);
        FlightDao flightDao = mock(FlightDaoImpl.class);
        when(flightDao.getAllFlights()).thenReturn(flights);
        //when
        FlightService flightService = new FlightService(flightDao);
        Flight flight = flightService.getFlightById(13);
        //then
        assertEquals(flight, flight1);
    }

    @Test
    public void should_findFlights_when_hasFlights() throws IOException, ClassNotFoundException, URISyntaxException {
        //given
        flights.add(flight1);
        flights.add(flight2);
        FlightDao flightDao = mock(FlightDaoImpl.class);
        when(flightDao.getAllFlights()).thenReturn(flights);
        //when
        FlightService flightService = new FlightService(flightDao);
        List<Flight> allFlights = flightService.findFlights(Destination.BERLIN, now.toLocalDate(),3);
        List<Flight> result = new ArrayList<>();
        result.add(flight1);
        //then
        assertArrayEquals(result.toArray(), allFlights.toArray());
    }

    @Test
    public void should_getClosesFlights_when_hasFlights() throws IOException, ClassNotFoundException, URISyntaxException {
        //given
        flights.add(flight1);
        flights.add(flight2);
        FlightDao flightDao = mock(FlightDaoImpl.class);
        when(flightDao.getAllFlights()).thenReturn(flights);
        //when
        FlightService flightService = new FlightService(flightDao);
        List<Flight> allFlights = flightService.getClosesFlights();
        //then
        assertArrayEquals(flights.toArray(), allFlights.toArray());
    }

}