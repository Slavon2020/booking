package model;

import destination.Destination;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
    final int id;
    int freeTickets;
    final LocalDateTime dateTime;
    final Destination destination;

    public int getId() {
        return id;
    }
    public int getFreeTickets() {
        return freeTickets;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public Destination getDestination() {
        return destination;
    }

    public Flight(int id, int freeTickets, LocalDateTime dateTime, Destination destination) {
        this.id = id;
        this.freeTickets = freeTickets;
        this.dateTime = dateTime;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return  "\n"+"Flight{" +
                "id=" + id +
                ", freeTickets=" + freeTickets +
                ", dateTime=" + dateTime +
                ", destination=" + destination +
                '}';
    }
}
