import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public abstract double calculatePrice(long nights);

    public String getRoomNumber() {
        return roomNumber;
    }
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 100;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 180;
    }
}

class Suite extends Room {
    public Suite(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 300;
    }
}

class Reservation {
    private M4.Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean cancelled;

    public Reservation(
        M4.Customer customer,
        Room room,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate cancellationDeadline
    ) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
        this.cancelled = false;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return !cancelled
            && start.isBefore(endDate)
            && end.isAfter(startDate);
    }

    public boolean cancel(LocalDate date) {
        if (cancelled) {
            return false;
        }

        if (!date.isAfter(cancellationDeadline)) {
            cancelled = true;
            return true;
        }

        return false;
    }

    public double getPrice() {
        long nights = ChronoUnit.DAYS.between(startDate, endDate);
        return room.calculatePrice(nights);
    }

    public Room getRoom() {
        return room;
    }

    public M4.Customer getCustomer() {
        return customer;
    }
}

public class M4 {
    static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static List<Reservation> reservations = new ArrayList<>();

    static boolean isAvailable(
        Room room,
        LocalDate start,
        LocalDate end
    ) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom() == room &&
                reservation.overlaps(start, end)) {
                return false;
            }
        }

        return true;
    }

    static Reservation reserveRoom(
        Customer customer,
        Room room,
        LocalDate start,
        LocalDate end,
        LocalDate cancellationDeadline
    ) {
        if (!isAvailable(room, start, end)) {
            System.out.println(
                room.getClass().getSimpleName() +
                " " +
                room.getRoomNumber() +
                " is not available from " +
                start +
                " to " +
                end
            );

            return null;
        }

        Reservation reservation = new Reservation(
            customer,
            room,
            start,
            end,
            cancellationDeadline
        );

        reservations.add(reservation);

        System.out.println(
            "Reservation confirmed for " +
            customer.getName() +
            ", " +
            room.getClass().getSimpleName() +
            " " +
            room.getRoomNumber()
        );

        System.out.println(
            "Price: $" + reservation.getPrice()
        );

        return reservation;
    }

    static void cancelReservation(
        Reservation reservation,
        LocalDate date
    ) {
        if (reservation.cancel(date)) {
            System.out.println(
                "Reservation for " +
                reservation.getCustomer().getName() +
                ", " +
                reservation.getRoom().getClass().getSimpleName() +
                " " +
                reservation.getRoom().getRoomNumber() +
                " cancelled successfully."
            );
        } else {
            System.out.println(
                "Reservation cannot be cancelled."
            );
        }
    }

    public static void main(String[] args) {
        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        Room standard101 = new StandardRoom("101");
        Room deluxe201 = new DeluxeRoom("201");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);

        if (isAvailable(standard101, jan1, jan5)) {
            System.out.println(
                "Standard Room 101 is available from Jan 1 to Jan 5."
            );
        }

        Reservation reservation = reserveRoom(
            customerA,
            standard101,
            jan1,
            jan5,
            LocalDate.of(2025, 12, 28)
        );

        reserveRoom(
            customerB,
            standard101,
            LocalDate.of(2026, 1, 3),
            LocalDate.of(2026, 1, 7),
            LocalDate.of(2025, 12, 30)
        );

        cancelReservation(
            reservation,
            LocalDate.of(2025, 12, 27)
        );

        reserveRoom(
            customerC,
            deluxe201,
            LocalDate.of(2026, 2, 10),
            LocalDate.of(2026, 2, 12),
            LocalDate.of(2026, 2, 5)
        );
    }
}