package wagecalc;

import foocorp.*;
import linkedlist.*;

import java.util.*;
import java.io.*;

public class WageCalc {
    public static void main(String[] args) throws IOException {
        String file = "employees.txt"; 
        Scanner sc = null;

        FooCorporation fooCorp = new FooCorporation(); // Both custom linked list and ArrayList resides in this class
        
        // Opening the file
        sc = new Scanner(new File(file));

        // Reading in from the file
        while (sc.hasNext()) {
            String s = sc.next();

            // Ignoring the comments in the file
            if (s.contains("#")) { 
                sc.nextLine();
                continue;
            }

            String name = s;
            s = sc.next();
            double basePay = Double.parseDouble(s);
            s = sc.next();
            int hoursWorked = Integer.parseInt(s);

            // Calculating the total pay and adding employee to linked list and array list
            try {
                fooCorp.totalPay(basePay, hoursWorked, name);
            } catch (InvalidBasePayException e1) {
                System.out.println(e1);
            } catch (InvalidHoursWorkedException e2) {
                System.out.println(e2);
            }
        }
        sc.close();

        // Sorting the linked list
        System.out.println("\nSorted the linked list");
        fooCorp.sortEmployeesLinkedList();
        
        // Sorting the array list
        System.out.println("Sorted the Array List");
        fooCorp.sortEmployeesArray();

    }
}
