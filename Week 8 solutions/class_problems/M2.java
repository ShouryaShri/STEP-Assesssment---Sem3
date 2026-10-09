abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract boolean canTakeLeave(int days);

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;
    private LeaveStatus status;

    public LeaveRequest(
        Employee employee,
        String startDate,
        String endDate,
        int days
    ) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = LeaveStatus.PENDING;
    }

    public boolean approve() {
        if (status != LeaveStatus.PENDING) {
            return false;
        }

        if (!employee.canTakeLeave(days)) {
            return false;
        }

        status = LeaveStatus.APPROVED;
        return true;
    }

    public boolean reject() {
        if (status != LeaveStatus.PENDING) {
            return false;
        }

        status = LeaveStatus.REJECTED;
        return true;
    }

    public boolean setStatus(LeaveStatus newStatus) {
        if (status != LeaveStatus.PENDING) {
            return false;
        }

        status = newStatus;
        return true;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

public class M2 {

    static LeaveRequest submitLeave(
        Employee employee,
        String startDate,
        String endDate,
        int days
    ) {
        LeaveRequest request =
            new LeaveRequest(employee, startDate, endDate, days);

        System.out.println(
            "Leave request submitted for " +
            employee.getName() +
            " (" + startDate + "-" + endDate + "). Status: Pending."
        );

        return request;
    }

    static void reviewRequest(LeaveRequest request, boolean approve) {
        if (approve) {
            if (request.approve()) {
                System.out.println(
                    request.getEmployee().getName() +
                    "'s leave request (" +
                    request.getStartDate() +
                    "-" +
                    request.getEndDate() +
                    ") approved. Status: Approved."
                );
            } else {
                System.out.println("Leave request cannot be approved.");
            }
        } else {
            if (request.reject()) {
                System.out.println(
                    request.getEmployee().getName() +
                    "'s leave request (" +
                    request.getStartDate() +
                    "-" +
                    request.getEndDate() +
                    ") rejected. Status: Rejected."
                );
            }
        }
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest johnRequest =
            submitLeave(john, "Jan 1", "Jan 5", 5);

        reviewRequest(johnRequest, true);

        LeaveRequest janeRequest =
            submitLeave(jane, "Feb 10", "Feb 11", 2);

        reviewRequest(janeRequest, false);

        if (!johnRequest.setStatus(LeaveStatus.PENDING)) {
            System.out.println(
                "Cannot change leave request status from Approved to Pending."
            );
        }
    }
}