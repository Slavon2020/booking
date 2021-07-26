package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
    int id;
    int freeTickets;
    LocalDateTime dateTime;

    public Flight(int id, int freeTickets, LocalDateTime dateTime) {
        this.id = id;
        this.freeTickets = freeTickets;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", freeTickets=" + freeTickets +
                ", dateTime=" + dateTime +
                '}';
    }
}
