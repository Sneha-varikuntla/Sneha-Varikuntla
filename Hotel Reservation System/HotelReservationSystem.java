import java.util.*;

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize rooms
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    viewReservations();
                    break;
                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.type);
            }
        }
    }

    static void bookRoom() {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && !room.isBooked) {
                room.isBooked = true;
                reservations.add(new Reservation(name, roomNo));
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void cancelReservation() {
        System.out.print("Enter room number to cancel: ");
        int roomNo = sc.nextInt();

        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) {
            Reservation res = it.next();
            if (res.roomNumber == roomNo) {
                it.remove();
                for (Room room : rooms) {
                    if (room.roomNumber == roomNo) {
                        room.isBooked = false;
                        break;
                    }
                }
                System.out.println("Reservation cancelled.");
                return;
            }
        }
        System.out.println("Reservation not found!");
    }

    static void viewReservations() {
        System.out.println("\nCurrent Reservations:");
        for (Reservation r : reservations) {
            System.out.println("Customer: " + r.customerName + " | Room: " + r.roomNumber);
        }
    }
}
