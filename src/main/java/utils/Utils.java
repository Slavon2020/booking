package utils;

import destination.Destination;
import model.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    static Random random = new Random();

    // returns dateTime that is guaranteed after the moment of method call
    private static LocalDateTime getRandomDateTime() {
        int randomDays = random.nextInt(31);
        int randomMonth = random.nextInt(13);
        int randomYears = random.nextInt(6);
        int randomHours = random.nextInt(23);
        int randomMinutes = random.nextInt(60);
        return LocalDateTime.now().plusDays(randomDays).plusMonths(randomMonth).plusYears(randomYears).plusHours(randomHours).plusMinutes(randomMinutes).withNano(0);
    }

    public static List<Flight> getRandomFlightsList() {
        List<Flight> flightsListToReturn = new ArrayList<>();
        int count = 0;
        while (count < 10) {
            int destinationIndex = random.nextInt(4);
            int randomFreeTickets = random.nextInt(10);
            Flight flight = new Flight(count, randomFreeTickets, getRandomDateTime(), Destination.values()[destinationIndex]);
            flightsListToReturn.add(flight);
            count++;
        }
        return flightsListToReturn;
    }
}

