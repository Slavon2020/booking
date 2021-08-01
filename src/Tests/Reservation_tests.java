import controller.ReservationController;
import dao.ReservationDAOImpl;
import model.Reservation;
import org.junit.jupiter.api.Test;
import service.ReservationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

public class Reservation_tests {
    ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
    ReservationService reservationService = new ReservationService();

    void testReserve (int id) {
        ArrayList<HashMap> passengersList = new ArrayList<>();
        HashMap passenger1 = new HashMap();
        passenger1.put("name","test");
        passenger1.put("surname","test");
        passengersList.add(passenger1);
        reservationDAO.reserveFlight(id,passengersList);
    }

    @Test
    void createNewReservation_Test (){
        int countOfUserReservations = reservationDAO
                .getReservations("test","test").size();

        testReserve(121);

        int countOfUserReservationsAfterCreatingNewReservation = reservationDAO
                .getReservations("test","test").size();

        assertNotEquals(countOfUserReservations,countOfUserReservationsAfterCreatingNewReservation);

        reservationDAO.getReservations("test","test")
                .forEach(e -> reservationDAO.declineReservation(e.getId()));
    }

    @Test
    void getReservationId_Test (){
        testReserve(1210);
        ArrayList<Reservation> reservations = reservationDAO.getReservations("test","test");
        assertNotNull(reservations.get(reservations.size() -1 ));

        reservationDAO.getReservations("test","test")
                .forEach(e -> reservationDAO.declineReservation(e.getId()));
    }

    @Test
    void reservationToString_Test (){
        testReserve(1210);
        ArrayList<Reservation> reservations = reservationDAO.getReservations("test","test");
        assertNotNull(reservations.get(reservations.size() -1 ).toString());
    }

    @Test
    void getPassengersCountById_Test (){
        testReserve(7695);
        ArrayList<Reservation> userReservations = reservationDAO.getReservations("test","test");
        int countOfPassengers = reservationService.getPassengersCountByReservationId(userReservations.get(0).getId());
        assertNotEquals(countOfPassengers,0);

        reservationDAO.getReservations("test","test")
                .forEach(e -> reservationDAO.declineReservation(e.getId()));
    }

    @Test
    void getFlightIdByReservationId_Test (){
        int flightId = 7695;
        testReserve(flightId);
        ArrayList<Reservation> userReservations = reservationDAO.getReservations("test","test");

        assertEquals(userReservations.get(0).getFlightId(),flightId);
    }

    @Test
    void getFlightId_Test (){
        testReserve(7659);
        ArrayList<Reservation> userReservations = reservationDAO.getReservations("test","test");
        int flightId = userReservations.get(0).getFlightId();

        assertNotNull(flightId);
    }

    @Test
    void declineReservations_Test (){
        testReserve(1410);
        testReserve(1951);

        int countOfUserReservationsAfterCreatingNewReservation = reservationDAO
                .getReservations("test","test").size();

        assertNotEquals(countOfUserReservationsAfterCreatingNewReservation,0);

        reservationDAO.getReservations("test","test")
                .forEach(e -> reservationDAO.declineReservation(e.getId()));

        assertEquals(reservationDAO.getReservations("test","test").size(),0);
    }

}
