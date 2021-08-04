package interfaces;

import model.Flight;

import java.io.IOException;
import java.util.List;

public interface FlightDao {
    void setFlights(List<Flight> flights) throws IOException;
    List<Flight> getAllFlights() throws IOException, ClassNotFoundException;

}
