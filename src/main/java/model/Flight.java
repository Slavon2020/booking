package model;

import destination.Destination;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
    int id;
    int freeTickets;
    LocalDateTime dateTime;
    Destination destination;

    public Flight(int id, int freeTickets, LocalDateTime dateTime, Destination destination) {
        this.id = id;
        this.freeTickets = freeTickets;
        this.dateTime = dateTime;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", freeTickets=" + freeTickets +
                ", dateTime=" + dateTime +
                ", destination=" + destination +
                '}';
    }
}
