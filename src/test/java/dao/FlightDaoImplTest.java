package dao;

import interfaces.FlightDao;
import model.Flight;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightDaoImplTest {

    @Test
    public void should_loadFlights_when_addFlights() throws URISyntaxException, IOException, ClassNotFoundException {
        //given
        String fileName = "flightsDb.txt";
        List<Flight> generatedFlightsList = Utils.getRandomFlightsList();
        FlightDao flightDao = new FlightDaoImpl(fileName);
        //when
        flightDao.setFlights(generatedFlightsList);
        List<Flight> allFlights = flightDao.getAllFlights();
        //then
        assertArrayEquals(generatedFlightsList.toArray(), allFlights.toArray());
    }
}