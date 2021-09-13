package foocorp;

import java.util.*;
import linkedlist.*;

public class FooCorporation {
    private linkedList employeesLinkedList = new linkedList(); // Linked list to hold the employees
    private ArrayList<Employee> employeesArray = new ArrayList<Employee>(); // Array to hold the employees

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

        System.out.println("Total Pay: " + totalPay);

        // Adding the employee's pay and hours worked to the linked List
        Employee employee = new Employee(basePay, hoursWorked, name, totalPay);
        linkedList.insert(employeesLinkedList, employee);

        // Adding the employee's pay and hours worked to the ArrayList
        employeesArray.add(employee);
    }

    // Sorting the linked list by total pay
    public void sortEmployeesLinkedList() {
        linkedList.sortList(employeesLinkedList);
        linkedList.printList(employeesLinkedList);
    }

    // Sorting the array by total pay
    public void sortEmployeesArray() {
        for(Employee e: employeesArray)
            System.out.println("Array Total Pay: " + e.getTotalPay());
    }
}
