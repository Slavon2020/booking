package dao;

import interfaces.ReservationDAO;
import model.Reservation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationDAOImpl implements ReservationDAO{

    private List<Reservation> allReservations = new ArrayList<>();

    { loadData();}

    public ReservationDAOImpl() {
    }

    @Override
    public void reserveFlight(int flightId, List<Map> passengers){
        Reservation reservation = new Reservation(flightId,passengers);
        allReservations.add(reservation);
        saveData();
    }

    @Override
    public void declineReservation(String reservationId) {
        try {
            Optional<Reservation> neededReservation = allReservations.stream()
                    .filter(e -> e.getId().equals(reservationId)).findAny();
            allReservations.remove(neededReservation.get());
            saveData();
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Reservation> getReservations(String name, String surname) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            reservations = (ArrayList<Reservation>) allReservations.stream()
                    .filter(e -> e.isReservationContainPassenger(name, surname)).collect(Collectors.toList());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    private void saveData()  {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/resources/reservationsDb.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allReservations);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            try {
                FileInputStream fis = new FileInputStream("src/main/resources/reservationsDb.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    allReservations = (ArrayList<Reservation>) ois.readObject();
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            } catch (EOFException e) {
                e.printStackTrace();
            }

        } catch (IOException  e){
            e.printStackTrace();
        }
    }
}
