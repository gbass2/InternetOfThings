package foocorp;
import linkedlist.*;

// Creating an exception for if the base pay is below 8
class InvalidBasePayException extends Exception {
    public InvalidBasePayException(String msg) {
        // Calling the constructor of parent Exception
        super(msg);
    }
}

// Creating an exception for if the hours worked are less than 0 or greater than
// 60
class InvalidHoursWorkedException extends Exception {
    public InvalidHoursWorkedException(String msg) {
        // Calling the constructor of parent Exception
        super(msg);
    }
}

// Class to hold the attributes of an employee
class Employee {
    private int hoursWorked;
    private double basePay;
    private double totalPay;
    private String name;

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setBasePay(double basePay) {
        this.basePay = basePay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public double getBasePay() {
        return basePay;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public String getName() {
        return name;
    }
}


class FooCorporation {
    private linkedList employeesLinkedList = new linkedList(); // Creating the linked list to hold the base pay and //
                                                               // hours worked

    public void totalPay(double basePay, int hoursWorked, String name)
            throws InvalidHoursWorkedException, InvalidBasePayException {

        // Checking for errors
        if (basePay < 8) {
            throw new InvalidBasePayException("Base pay is " + basePay + " and cannot be less than 8");
        }
        if (hoursWorked > 60 || hoursWorked < 0) {
            throw new InvalidHoursWorkedException(
                    "Hours worked is " + hoursWorked + " and cannot be less than 0 or greater than 60");
        }

        // Calculating over time
        int overTime = hoursWorked > 40 ? hoursWorked - 40 : 0;

        // Calculating total pay
        totalPay = employee.hoursWorked > 40 ? employee.basePay * 40 + employee.basePay * 1.5 * overTime
                : basePay * hoursWorked;

        System.out.println(employee.getTotalPay);

        // Adding the employee's pay and hours worked to the linked List
        Employee employee = new Employee();
        employee.setName(name);
        employee.setBasePay(basePay);
        employee.setTotalPay(totalPay);
        employee.setsetHoursWorked(hoursWorked);
        linkedList.insert(employeesLinkedList, employee);
        // Adding the employee's pay and hours worked to the ArrayList
    }

    public linkedList getEmployeesLinkedList() {
        return employeesLinkedList;
    }
}
