package service;

import dao.ReservationDAOImpl;
import model.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {
    private final ReservationDAOImpl reservationDAO = new ReservationDAOImpl();

    public void reserveFlight(int flightId, List<HashMap> passengers) {
        reservationDAO.reserveFlight(flightId,passengers);
    }
    public void declineReservation(String reservationId) {
        reservationDAO.declineReservation(reservationId);
    }
    public int getPassengersCountByReservationId (String id) { return reservationDAO.getReservationById(id).getPassengers().size(); }
    public int getFlightIdByReservationId (String id ){ return reservationDAO.getReservationById(id).getFlightId(); }
    public void showReservations(String name,String surname){
        ArrayList<Reservation> reservations = reservationDAO.getReservations(name,surname);
        reservations.forEach(e -> System.out.println(e.toString()));
    }
}
