import java.util.ArrayList;
import java.util.Scanner;

class Train {
    int trainNo;
    String trainName;
    String from;
    String to;
    int availableSeats;

    Train(int trainNo, String trainName, String from, String to, int seats) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.from = from;
        this.to = to;
        this.availableSeats = seats;
    }

    void displayInfo() {
        System.out.println(trainNo + " | " + trainName + " | From: " + from + " | To: " + to + " | Seats Available: " + availableSeats);
    }
}

class Booking {
    String passengerName;
    int trainNo;
    int seatsBooked;

    Booking(String name, int trainNo, int seats) {
        this.passengerName = name;
        this.trainNo = trainNo;
        this.seatsBooked = seats;
    }

    void displayTicket() {
        System.out.println("Booking Confirmed!");
        System.out.println("Passenger: " + passengerName);
        System.out.println("Train No: " + trainNo);
        System.out.println("Seats Booked: " + seatsBooked);
    }
}

public class RailwayTicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Train> trains = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        // Predefined trains
        trains.add(new Train(101, "Udaipur Express", "Udaipur", "Delhi", 10));
        trains.add(new Train(202, "Mewar Fast", "Udaipur", "Jaipur", 15));
        trains.add(new Train(303, "Desert Queen", "Jodhpur", "Bikaner", 8));

        int choice;
        do {
            System.out.println("\n===== Railway Ticket Booking System =====");
            System.out.println("1. View Available Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- Available Trains ---");
                    for (Train t : trains) {
                        t.displayInfo();
                    }
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Train Number: ");
                    int trainNo = scanner.nextInt();

                    Train selectedTrain = null;
                    for (Train t : trains) {
                        if (t.trainNo == trainNo) {
                            selectedTrain = t;
                            break;
                        }
                    }

                    if (selectedTrain == null) {
                        System.out.println("Invalid Train Number!");
                        break;
                    }

                    System.out.print("Enter number of seats to book: ");
                    int seats = scanner.nextInt();

                    if (seats <= 0 || seats > selectedTrain.availableSeats) {
                        System.out.println("Invalid number of seats! Only " + selectedTrain.availableSeats + " left.");
                        break;
                    }

                    // Book ticket
                    selectedTrain.availableSeats -= seats;
                    Booking booking = new Booking(name, trainNo, seats);
                    bookings.add(booking);
                    System.out.println("\n--- Ticket Details ---");
                    booking.displayTicket();
                    break;

                case 3:
                    System.out.println("\n--- All Bookings ---");
                    if (bookings.isEmpty()) {
                        System.out.println("No tickets booked yet.");
                    } else {
                        for (Booking b : bookings) {
                            b.displayTicket();
                            System.out.println("----------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using Railway Ticket Booking System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}

