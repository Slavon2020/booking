package console;

import exceptions.IllegalMenuOptionException;
import model.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static utils.Utils.getRandomDateTime;

public class Console {

    Scanner scanner;
    Map<Integer, String> mainMenu;
    Random random;

    public Console() {
        scanner = new Scanner(System.in);
        mainMenu = new HashMap<>();
        fillMainMenuOptions();
        random  = new Random();

    }

    public void run() {
        while(true) {
            showMainMenu();
            try {
                handleChoosedMainMenuOption();
            } catch (IllegalMenuOptionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    private void handleChoosedMainMenuOption() throws IllegalMenuOptionException, InterruptedException {
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
        System.out.println("Before exit....");
        System.exit(1);
    }

    private void showMyFlights() {
        System.out.println("showMyFlights...........");
    }

    private void cancelBooking() {
        System.out.println("cancelBooking...........");
    }

    private void searchAndBookFlight() {
//        System.out.println("Введите место назначения:");
//        String destStr = scanner.nextLine();
//        StringBuilder dateTime = new StringBuilder();
//        System.out.println("Введите дату (дд.мм.гггг):");
//        String date = scanner.nextLine();
//        System.out.println("Введите время (чч:мм):");
//        String time = scanner.nextLine();
//        System.out.println("Введите необходимое количество билетов:");
//        int ticketsNum = scanner.nextInt();
//        dateTime.append(date);
//        dateTime.append(" ");
//        dateTime.append(time);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
//        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
//
//        flightController.findFlights()
    }

    private void showFlightInfo() throws InterruptedException {

        System.out.println("Введите ID рейса:");
        String id = scanner.nextLine();
        System.out.println("Информация о рейсе " + id);
        Thread.sleep(3000);
//        flightController.showFlightInfo(id);

    }

    private void showSchedule() {
        System.out.println("SCHEDULE......");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
