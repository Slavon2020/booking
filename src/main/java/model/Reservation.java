package model;

import java.io.Serializable;
import java.util.*;

public class Reservation implements Serializable {
    static private int idCounter = 1;
    private final int reservationId;
    private final int flightId;
    private final List<Map> passengers;

    public Reservation(int flightId, List<Map> passengers) {
        this.reservationId = idCounter++;
        this.flightId = flightId;
        this.passengers = passengers;
    }

    public int getId(){return this.reservationId;}

    public boolean isReservationContainPassenger(String name, String surname){
        try {
            Optional<Map> filteredArray = passengers.stream()
                    .filter(e -> e.get("name").equals(name) &&
                            e.get("surName").equals(surname)).findAny();
            return true;
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public String toString(){
        String[] passengersString = {"["};
        passengers.stream().forEach(e -> {
            passengersString[0] = passengersString[0] +
                    String.format("{ name-%s,surName-%s }"
                            ,e.get("name"),e.get("surName"));
            if (e != passengers.get(passengers.size()-1)){
                passengersString[0] = passengersString[0] + ",";
            }

        });
        passengersString[0] = passengersString[0] + "]";
        return String.format("reservation id = %d, flight id = %d , passengers = %s",this.getId(),this.flightId, passengersString[0]);
    }
}
