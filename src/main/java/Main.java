import console.Console;
import dao.ReservationDAOImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
//        Console console = new Console();
//        console.run();
//        HashMap<String,String> passenger1 = new HashMap<>();
//        passenger1.put("name","Ivan");
//        passenger1.put("surName","Bydko");
//        HashMap<String,String> passenger2 = new HashMap<>();
//        passenger2.put("name","Valyha");
//        passenger2.put("surName","Bydko");
//        List<Map> passengers = new ArrayList<>();
//        passengers.add(passenger1);
//        passengers.add(passenger2);
        ReservationDAOImpl rdi = new ReservationDAOImpl();
//        rdi.reserveFlight(123,passengers);
//        rdi.reserveFlight(153,passengers);
//        rdi.reserveFlight(162,passengers);
        rdi.showAllReservations();
        System.out.println("Reservations by name and surname");
        rdi.showReservations("Ivan","Bydko");
    }
}
