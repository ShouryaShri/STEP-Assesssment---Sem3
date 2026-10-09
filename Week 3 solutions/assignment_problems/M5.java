class Employee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class M5 {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Divya", 65000);
        Employee employee2 = new Employee("Arjun", 30000);
        Employee employee3 = new Employee("Ravi", 55000);

        System.out.println("3 Employee objects created");
        Employee.printCompanyInfo();
    }
}