package interfaces;

import model.Reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ReservationDAO {
    void reserveFlight(int flightId, List<Map> passengers) throws IOException, ClassNotFoundException;
    void declineReservation(String reservationId) throws IOException;

    ArrayList<Reservation> getReservations(String name, String surname);
}
