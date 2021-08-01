package controller;

import service.ReservationService;

import java.util.HashMap;
import java.util.List;

public class ReservationController {
    ReservationService reservationService = new ReservationService();

    public int getFlightIdByReservationId (String reservationId) { return reservationService.getFlightIdByReservationId(reservationId);}
    public int getPassengersCountByReservationId (String reservationId){ return reservationService.getPassengersCountByReservationId(reservationId); }
    public void reserveFlight(int flightId, List<HashMap> passengers) {reservationService.reserveFlight(flightId,passengers); }
    public void declineReservation(String reservationId) {
        reservationService.declineReservation(reservationId);
    }
    public void showReservations(String name,String surname){ reservationService.showReservations(name,surname); }
}
