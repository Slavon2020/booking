package dao;

import java.util.List;

public interface ReservationDAO {
    int reserveFlight(int flightId, List<String> passengers);
    int declineReservation(int reservationId);

    void showReservations(String name,String surname);
}
