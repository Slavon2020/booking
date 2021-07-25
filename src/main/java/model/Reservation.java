package model;

import java.util.List;
import java.util.Map;

public class Reservation {
    static private int idCounter = 1;
    private final int reservationId;
    private final int flightId;
    private final List<Map> passengers;

    public Reservation(int reservationId, int flightId, List<Map> passengers) {
        this.reservationId = reservationId;
        this.flightId = flightId;
        this.passengers = passengers;
    }
}
