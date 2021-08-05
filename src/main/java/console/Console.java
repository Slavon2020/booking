package console;

import controller.FlightController;
import controller.ReservationController;
import destination.Destination;
import exceptions.IllegalMenuOptionException;
import model.Flight;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Console {
    private ReservationController reservationController;
    FlightController flightController;
    private Map<Integer, String> mainMenu;

    public Console() {
        reservationController = new ReservationController();
        try {
            flightController = new FlightController();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        mainMenu = new HashMap<>();
        fillMainMenuOptions();
    }

    public void run() {
        while (true) {
            showMainMenu();
            try {
                handleChoosedMainMenuOption();
            } catch (IllegalMenuOptionException | ClassNotFoundException | IOException e) {
                if(e instanceof IllegalMenuOptionException) {
                    System.out.println("Выберите корректный пункт меню!");
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    private void fillMainMenuOptions() {
        mainMenu.put(1, "Онлайн-табло");
        mainMenu.put(2, "Посмотреть информацию о рейсе");
        mainMenu.put(3, "Поиск и бронировка рейса");
        mainMenu.put(4, "Отменить бронирование");
        mainMenu.put(5, "Мои рейсы");
        mainMenu.put(6, "Выход");
    }

    private void showMainMenu() {
        System.out.println("Выберите пункт меню, который Вас интересует:");
        for (Map.Entry<Integer, String> entry: mainMenu.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private void handleChoosedMainMenuOption() throws IllegalMenuOptionException, IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                showSchedule();
                break;
            case "2":
                showFlightInfo();
                break;
            case "3":
                searchAndBookFlight();
                break;
            case "4":
                cancelBooking();
                break;
            case "5":
                showMyFlights();
                break;
            case "6":
                exit();
                break;
            default:
                throw new IllegalMenuOptionException();
        }
    }

    private void exit() {
        System.exit(1);
    }

    private void showMyFlights() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();
        reservationController.showReservations(name, surname);
    }

    private void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID бронирования:");
        String reservationId = scanner.nextLine();
        int flightId;
        try {
            flightId = reservationController.getFlightIdByReservationId(reservationId);
        } catch (NoSuchElementException e) {
            System.out.println("Вы ввели неверный ID бронирования");
            return;
        }
        int passengersCount = reservationController.getPassengersCountByReservationId(reservationId);

        try {
            flightController.increaseFreeTickets(flightId, passengersCount);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        reservationController.declineReservation(reservationId);
    }

    private void searchAndBookFlight() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите место назначения:");
        String destStr = scanner.nextLine();
        System.out.println("Введите дату (дд.мм.гггг):");
        String date = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Вы ввели неправильный формат даты!");
            return;
        }
        System.out.println("Введите необходимое количество билетов:");
        int ticketsNum;
        try {
            ticketsNum = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели неправильный формат данных!");
            return;
        }

        List<Flight> foundFlights = new ArrayList<>();
        try {
            foundFlights = flightController.findFlights(Destination.valueOf(destStr.toUpperCase()), localDate, ticketsNum);
            System.out.println("Найденные рейсы: ");
            for (int i = 0; i < foundFlights.size(); i++) {
                System.out.println((i + 1) + ". " + foundFlights.get(i));
            }
        } catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
            if (e instanceof IllegalArgumentException) {
                System.out.println("Вы ввели неправильный пункт назначения!");

            } else {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("Выберите порядковый номер рейса или нажмите 0 для возврата в главное меню");
        int choosedOption;
        try {
            choosedOption = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели неправильный формат данных!");
            return;
        }
        if (choosedOption == 0 || choosedOption > foundFlights.size() || foundFlights.size() == 0) {
            if (choosedOption > foundFlights.size()) {
                System.out.println("Неверный порядковый номер рейса!");
            } else if (foundFlights.size() == 0) {
                System.out.println("Нет рейсов по параметрам поиска (");
            }
            return;
        }
        Flight choosedFlight = foundFlights.get(choosedOption - 1);
        System.out.println("Выбранный рейс: " + choosedFlight);
        List<HashMap> passengersList = createPassengersList(ticketsNum);
        reservationController.reserveFlight(choosedFlight.getId(), passengersList);
        flightController.decreaseFreeTickets(choosedFlight.getId(),  ticketsNum);
    }

    private List <HashMap> createPassengersList(int ticketsNum) {
        List<HashMap> passengersList = new ArrayList<>();
        for (int i = 0; i < ticketsNum; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя пвссажира " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.println("Введите фамилию пвссажира " + (i + 1) + ": ");
            String surname = scanner.nextLine();
            HashMap<String, String> passenger = new HashMap<>();
            passenger.put("name", name);
            passenger.put("surname", surname);
            passengersList.add(passenger);
        }
        return passengersList;
    }

    private void showFlightInfo(){
        System.out.println("Введите ID рейса:");
        Scanner scanner = new Scanner(System.in);
        int id;
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели некорректный номер рейса");
            return;
        }
        System.out.println("Информация о рейсе " + id);
        Flight flight = null;
        try {
            flight = flightController.getFlightById(id);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(flight);
    }

    private void showSchedule() {
        List<Flight> allFlights = new ArrayList<>();
        try {
            allFlights = flightController.getAllFlights();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("SCHEDULE - " + allFlights);
    }
}
