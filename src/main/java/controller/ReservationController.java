package controller;

import service.ReservationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationController {
    ReservationService reservationService = new ReservationService();

    public void reserveFlight(int flightId, List<HashMap> passengers) {reservationService.reserveFlight(flightId,passengers); }
    public void declineReservation(String reservationId) {
        reservationService.declineReservation(reservationId);
    }
    public void showReservations(String name,String surname){ reservationService.showReservations(name,surname); }
}
