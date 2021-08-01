package controller;

import model.Reservation;
import service.ReservationService;

import java.util.HashMap;
import java.util.List;

public class ReservationController {
    ReservationService reservationService = new ReservationService();

    public int getPassengersCount(Reservation reservation){ return reservationService.getPassengersCount(reservation); }
    public void reserveFlight(int flightId, List<HashMap> passengers) {reservationService.reserveFlight(flightId,passengers); }
    public void declineReservation(String reservationId) {
        reservationService.declineReservation(reservationId);
    }
    public void showReservations(String name,String surname){ reservationService.showReservations(name,surname); }
    public Reservation getReservationById(String id) { return reservationService.getReservationById(id); }
}
