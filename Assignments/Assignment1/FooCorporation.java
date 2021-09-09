package foocorp;
import linkedlist.*;

// Creating an exception for if the base pay is below 8
class InvalidBasePayException extends Exception {
    public InvalidBasePayException(String msg) {
        // Calling the constructor of parent Exception
        super(msg);
    }
}

// Creating an exception for if the hours worked are less than 0 or greater than 60
class InvalidHoursWorkedException extends Exception {
    public InvalidHoursWorkedException(String msg) {
        // Calling the constructor of parent Exception
        super(msg);
    }
}

public class FooCorporation {
    private linkedList employeesLinkedList = new linkedList(); // Creating the linked list to hold the base pay and hours worked

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
        double totalPay = hoursWorked > 40 ? basePay * 40 + basePay * 1.5 * overTime
                : basePay * hoursWorked;

        System.out.println(totalPay);

        // Adding the employee's pay and hours worked to the linked List
        Employee employee = new Employee(basePay, hoursWorked, name, totalPay);
        linkedList.insert(employeesLinkedList, employee);
        // Adding the employee's pay and hours worked to the ArrayList
    }

    public linkedList getEmployeesLinkedList() {
        return employeesLinkedList;
    }
}
