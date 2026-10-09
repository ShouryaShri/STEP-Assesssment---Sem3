import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
    String getMethodName();
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return true;
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return false;
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        return true;
    }

    @Override
    public String getMethodName() {
        return "Bank Transfer";
    }
}

class Order {
    private M5.Customer customer;
    private List<OrderItem> items;
    private String status;

    public Order(M5.Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "Pending";
    }

    public void addProduct(Product product, int quantity) {
        if (quantity > 0) {
            items.add(new OrderItem(product, quantity));
        }
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public boolean processPayment(PaymentMethod method) {
        if (!hasItems()) {
            System.out.println(
                "Cannot process payment for an empty order."
            );
            return false;
        }

        System.out.println(
            "Payment initiated via " +
            method.getMethodName() +
            " for Order " +
            customer.getName() +
            "."
        );

        boolean success = method.processPayment(getTotal());

        if (success) {
            status = "Paid";

            System.out.println(
                "Payment for Order " +
                customer.getName() +
                " successful."
            );
        } else {
            System.out.println(
                "Payment for Order " +
                customer.getName() +
                " failed."
            );
        }

        System.out.println("Order status: " + status);

        return success;
    }
}

public class M5 {
    static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Customer customerX = new Customer("X");

        Product productA = new Product("Product A", 50);
        Product productB = new Product("Product B", 30);

        Order orderX = new Order(customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println(
            "Order created for Customer X."
        );

        orderX.processPayment(
            new CreditCardPayment()
        );

        Customer customerY = new Customer("Y");

        Order orderY = new Order(customerY);

        orderY.processPayment(
            new CreditCardPayment()
        );

        Customer customerZ = new Customer("Z");

        Product productC = new Product("Product C", 100);

        Order orderZ = new Order(customerZ);

        orderZ.addProduct(productC, 1);

        System.out.println(
            "Order created for Customer Z."
        );

        orderZ.processPayment(
            new PayPalPayment()
        );
    }
}