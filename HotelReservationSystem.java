package Project3;
import java.util.Scanner;
import java.util.List;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n---- Hotel Reservation System ----");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (Single, Double, Suite): ");
                    String roomType = scanner.next();
                    List<Room> availableRooms = hotel.searchAvailableRooms(roomType);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms of type " + roomType);
                    } else {
                        System.out.println("Available Rooms:");
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter room number to reserve: ");
                    int roomNumber = scanner.nextInt();
                    Room room = hotel.getRoomDetails(roomNumber);
                    if (room != null && room.isAvailable()) {
                        System.out.println("Room selected: " + room);
                        if (Payment.processPayment(room.getPricePerNight())) {
                            hotel.reserveRoom(roomNumber);
                            System.out.println("Reservation confirmed!");
                        } else {
                            System.out.println("Payment failed!");
                        }
                    } else {
                        System.out.println("Room is not available or doesn't exist.");
                    }
                    break;

                case 3:
                    System.out.print("Enter room number to view details: ");
                    int roomNo = scanner.nextInt();
                    Room bookedRoom = hotel.getRoomDetails(roomNo);
                    if (bookedRoom != null) {
                        System.out.println(bookedRoom);
                    } else {
                        System.out.println("No booking found for room number " + roomNo);
                    }
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}

