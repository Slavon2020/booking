import model.Flight;
import utils.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> generatedFlightsList = Utils.getRandomFlightsList();
        System.out.println(generatedFlightsList);
    }
}
