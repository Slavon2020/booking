import console.Console;
import controller.ReservationController;
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
        ReservationController reservationController = new ReservationController();
        //Peter Griffin
        reservationController.showReservations("Peter","Griffin");
        //Ivan Bydko
        reservationController.showReservations("Ivan","Bydko");
        // Brian Griffin
        reservationController.showReservations("Brian","Griffin");
    }
}
