import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Event {
    private final int id;
    private final String name;
    private final String date;
    private final String location;

    public Event(int id, String name, String date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}

class Reservation {
    private final int reservationId;
    private final Event event;
    private final String clientName;

    public Reservation(int reservationId, Event event, String clientName) {
        this.reservationId = reservationId;
        this.event = event;
        this.clientName = clientName;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Event getEvent() {
        return event;
    }

    public String getClientName() {
        return clientName;
    }
}

class EventBookingSystem {
    private final List<Event> events;
    private final List<Reservation> reservations;
    private int reservationCounter;

    public EventBookingSystem() {
        this.events = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.reservationCounter = 1;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getAvailableEvents() {
        return events;
    }

    public void bookReservation(Event event, String clientName) {
        Reservation reservation = new Reservation(reservationCounter++, event, clientName);
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

public class Main {
    public static void main(String[] args) {
        EventBookingSystem bookingSystem = new EventBookingSystem();

        Event event1 = new Event(1, "Conference", "2024-01-30", "Convention Center");
        Event event2 = new Event(2, "Concert", "2024-02-15", "Stadium");

        bookingSystem.addEvent(event1);
        bookingSystem.addEvent(event2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View available events");
            System.out.println("2. Book event reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Event> availableEvents = bookingSystem.getAvailableEvents();
                    System.out.println("Available Events:");
                    for (Event event : availableEvents) {
                        System.out.println(event.getId() + ". " + event.getName() + " - " + event.getDate() + " - " + event.getLocation());
                    }
                    break;
                case 2:
                    System.out.println("Enter the event ID you want to book:");
                    int eventId = scanner.nextInt();
                    Event selectedEvent = bookingSystem.getAvailableEvents().stream()
                            .filter(e -> e.getId() == eventId)
                            .findFirst()
                            .orElse(null);
                    if (selectedEvent != null) {
                        System.out.println("Enter your name for the reservation:");
                        scanner.nextLine(); // Consume the newline character
                        String clientName = scanner.nextLine();
                        bookingSystem.bookReservation(selectedEvent, clientName);
                        System.out.println("Reservation successful!");
                    } else {
                        System.out.println("Invalid event ID");
                    }
                    break;
                case 3:
                    List<Reservation> reservations = bookingSystem.getReservations();
                    System.out.println("Reservations:");
                    for (Reservation reservation : reservations) {
                        System.out.println(reservation.getReservationId() + ". " +
                                reservation.getEvent().getName() + " - " +
                                reservation.getEvent().getDate() + " - " +
                                reservation.getEvent().getLocation() + " - " +
                                "Reserved by: " + reservation.getClientName());
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

