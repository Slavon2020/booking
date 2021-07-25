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


    {
       loadData();
    }

    public ReservationDAOImpl() throws IOException {
    }

    @Override
    public void reserveFlight(int flightId, List<Map> passengers) throws IOException {
        Reservation reservation = new Reservation(flightId,passengers);
        allReservations.add(reservation);
        saveData();
    }

    @Override
    public void declineReservation(int reservationId) throws IOException {
        try {
            Optional<Reservation> neededReservation = allReservations.stream()
                    .filter(e -> e.getId() == reservationId).findAny();
            int index = allReservations.indexOf(neededReservation.get());
            allReservations.remove(index);
            saveData();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Ups, sorry but this reservation id is not valid");
        }

    }

    @Override
    public ArrayList<Reservation> getReservations(String name, String surname) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            reservations = (ArrayList<Reservation>) allReservations.stream()
                    .filter(e -> e.isReservationContainPassenger(name, surname)).collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println("Sorry you don’t have reservations");
        }
        return reservations;
    }

    public void showReservations(String name, String surname) {
        allReservations.stream().forEach(e -> {
            if (e.isReservationContainPassenger(name,surname)){
                System.out.println(e.toString());
            }
        });
    }

    private void saveData() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/resources/reservationsDb.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allReservations);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/reservationsDb.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            File file = new File("src/main/resources/reservationsDb.txt");
            if (file.length() != 0){
                try {
                    ArrayList<Reservation> reservation = (ArrayList<Reservation>) ois.readObject();
                    allReservations = reservation;
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
