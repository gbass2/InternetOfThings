package wagecalc;

import foocorp.*;
import linkedlist.*;

import java.util.*;
import java.io.*;

public class WageCalc {
    public static void main(String[] args) throws IOException {
        String file = args[0]; // Getting the file path for the employees file from CL args
        Scanner sc = null;

        FooCorporation fooCorp = new FooCorporation();

        // Opening the file
        sc = new Scanner(new File(file));

        // Reading in from the file
        while (sc.hasNext()) {
            String s = sc.next();

            if (s.contains("#")) { // Ignoring the comments in the file
                sc.nextLine();
                continue;
            }

            String name = s;
            s = sc.next();
            double basePay = Double.parseDouble(s);
            s = sc.next();
            int hoursWorked = Integer.parseInt(s);

            try {
                fooCorp.totalPay(basePay, hoursWorked, name); // Both custom linked list and ArrayList resides in this class
            } catch (InvalidBasePayException e1) {
                System.out.println(e1);
            } catch (InvalidHoursWorkedException e2) {
                System.out.println(e2);
            }
        }
        sc.close();

        // Sorting the lists
        fooCorp.sortEmployeesLinkedList();
        fooCorp.sortEmployeesArray();

    }
}
