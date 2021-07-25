import console.Console;
import dao.ReservationDAOImpl;
import service.ReservationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Console console = new Console();
//        console.run();
        ReservationService reservationService = new ReservationService();
        //Peter Griffin
        reservationService.showReservations("Peter","Griffin");
        //Ivan Bydko
        reservationService.showReservations("Ivan","Bydko");
        // Brian Griffin
        reservationService.showReservations("Brian","Griffin");

    }
}
