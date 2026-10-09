abstract class Vehicle {
    protected String vehicleId;
    protected String name;
    protected boolean available;

    public Vehicle(String vehicleId, String name) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.available = true;
    }

    public abstract double calculateCharge(int days);

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }
}

class Sedan extends Vehicle {
    public Sedan(String vehicleId, String name) {
        super(vehicleId, name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    public SUV(String vehicleId, String name) {
        super(vehicleId, name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    public Truck(String vehicleId, String name) {
        super(vehicleId, name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public double getCharge() {
        return vehicle.calculateCharge(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }
}

public class M1 {

    static Rental rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName() + " is currently unavailable.");
            return null;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(vehicle, customer, days);

        System.out.println(
            vehicle.getName() +
            " rented successfully by " +
            customer.getName() + "."
        );

        System.out.println("Rental charge: $" + rental.getCharge());

        return rental;
    }

    static void returnVehicle(Rental rental) {
        if (rental != null) {
            rental.getVehicle().setAvailable(true);

            System.out.println(
                rental.getVehicle().getName() +
                " returned by " +
                rental.getCustomer().getName() + "."
            );
        }
    }

    public static void main(String[] args) {
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("S1", "Sedan A");
        Vehicle suvB = new SUV("S1", "SUV B");

        Rental rental1 = rentVehicle(sedanA, customer1, 3);

        rentVehicle(sedanA, customer2, 2);

        returnVehicle(rental1);

        rentVehicle(suvB, customer3, 5);
    }
}