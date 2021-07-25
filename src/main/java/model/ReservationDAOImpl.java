package model;

import dao.ReservationDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    List<Reservation> allReservations = new ArrayList<>();

    public ReservationDAOImpl() throws FileNotFoundException {
    }

    @Override
    public int reserveFlight(int flightId, List<String> passengers) {
        return 0;
    }

    @Override
    public int declineReservation(int reservationId) {
        return 0;
    }

    @Override
    public void showReservations(String name, String surname) {

    }

}
