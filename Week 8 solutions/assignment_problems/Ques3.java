import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ques3 {

    interface Seat {
        String getSeatNumber();
        double getPrice();
    }

    static class RegularSeat implements Seat {
        private String seatNumber;

        RegularSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 150;
        }
    }

    static class PremiumSeat implements Seat {
        private String seatNumber;

        PremiumSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 250;
        }
    }

    static class ReclinerSeat implements Seat {
        private String seatNumber;

        ReclinerSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return 400;
        }
    }

    static class Customer {
        private String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Show {
        private String time;
        private Set<String> bookedSeats;

        Show(String time) {
            this.time = time;
            bookedSeats = new HashSet<>();
        }

        boolean isAvailable(String seatNumber) {
            return !bookedSeats.contains(seatNumber);
        }

        boolean bookSeat(String seatNumber) {
            return bookedSeats.add(seatNumber);
        }

        void releaseSeat(String seatNumber) {
            bookedSeats.remove(seatNumber);
        }
    }

    static class Booking {
        private Customer customer;
        private Show show;
        private List<Seat> seats;
        private boolean cancelled;

        Booking(
            Customer customer,
            Show show,
            List<Seat> seats
        ) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
        }

        double getTotal() {
            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        void cancel() {
            if (cancelled) {
                return;
            }

            for (Seat seat : seats) {
                show.releaseSeat(
                    seat.getSeatNumber()
                );
            }

            cancelled = true;
        }

        Customer getCustomer() {
            return customer;
        }

        List<Seat> getSeats() {
            return seats;
        }

        boolean isCancelled() {
            return cancelled;
        }
    }

    static Booking createBooking(
        Customer customer,
        Show show,
        Seat[] selectedSeats
    ) {
        if (selectedSeats.length == 0 ||
            selectedSeats.length > 6) {
            System.out.println(
                "Booking must contain between 1 and 6 seats."
            );
            return null;
        }

        for (Seat seat : selectedSeats) {
            if (!show.isAvailable(
                seat.getSeatNumber()
            )) {
                System.out.println(
                    "Seat " +
                    seat.getSeatNumber() +
                    " is already booked for this show."
                );
                return null;
            }
        }

        List<Seat> seats = new ArrayList<>();

        for (Seat seat : selectedSeats) {
            show.bookSeat(
                seat.getSeatNumber()
            );
            seats.add(seat);
        }

        Booking booking =
            new Booking(customer, show, seats);

        System.out.print(
            "Booking confirmed for " +
            customer.getName() +
            ": "
        );

        for (int i = 0; i < seats.size(); i++) {
            System.out.print(
                seats.get(i).getSeatNumber()
            );

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(
            ". Total: ₹%.2f%n",
            booking.getTotal()
        );

        return booking;
    }

    static void cancelBooking(
        Booking booking,
        boolean showStarted
    ) {
        if (showStarted) {
            System.out.println(
                "Cannot cancel: show has already started."
            );
            return;
        }

        booking.cancel();

        System.out.println(
            booking.getCustomer().getName() +
            "'s booking cancelled."
        );

        System.out.print("Seats ");

        for (int i = 0;
             i < booking.getSeats().size();
             i++) {

            System.out.print(
                booking.getSeats()
                       .get(i)
                       .getSeatNumber()
            );

            if (i < booking.getSeats().size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" released.");
    }

    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking = createBooking(
            asha,
            show,
            new Seat[]{
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
            }
        );

        createBooking(
            ravi,
            show,
            new Seat[]{
                new RegularSeat("A2")
            }
        );

        createBooking(
            ravi,
            show,
            new Seat[]{
                new ReclinerSeat("R1")
            }
        );

        cancelBooking(
            ashaBooking,
            false
        );

        createBooking(
            neha,
            show,
            new Seat[]{
                new RegularSeat("A2")
            }
        );
    }
}