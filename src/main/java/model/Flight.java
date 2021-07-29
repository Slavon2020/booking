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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (getId() != flight.getId()) return false;
        if (!getDateTime().equals(flight.getDateTime())) return false;
        return getDestination() == flight.getDestination();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDateTime().hashCode();
        result = 31 * result + getDestination().hashCode();
        return result;
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
