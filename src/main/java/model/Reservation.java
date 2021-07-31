package model;

import java.io.*;
import java.util.*;

public class Reservation implements Serializable {
    private final String reservationId;
    private final int flightId;
    private final List<HashMap> passengers;

    public Reservation(int flightId, List<HashMap> passengers) {
        this.reservationId = UUID.randomUUID().toString();
        this.flightId = flightId;
        this.passengers = passengers;

    }
    public List<HashMap> getPassengers(){return this.passengers;}
    public String getId(){return this.reservationId;}
    public int getFlightId (){return this.flightId;}

    public boolean isReservationContainPassenger(String name, String surname){
        try {
            Optional<HashMap> passengerReservations = passengers.stream()
                    .filter(e -> e.get("name").equals(name) &&
                            e.get("surname").equals(surname)).findAny();
            return passengerReservations.isPresent();
        }catch (NullPointerException e){
            return false;
        }
    }



    @Override
    public String toString(){
        String[] passengersString = {"["};
        passengers.forEach(e -> {
            passengersString[0] = passengersString[0] +
                    String.format("{ name-%s,surName-%s }"
                            ,e.get("name"),e.get("surName"));
            if (e != passengers.get(passengers.size()-1)){
                passengersString[0] = passengersString[0] + ",";
            }

        });
        passengersString[0] = passengersString[0] + "]";
        return String.format("reservation id = %s, flight id = %d , passengers = %s",this.getId(),this.flightId, passengersString[0]);
    }

}
