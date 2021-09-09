package wagecalc;

import foocorp.*;
import linkedlist.*;

import java.util.*;
import java.io.*;

public class WageCalc {
    public static void main(String[] args) {
        String file = args[0]; // Getting the file path for the employees file from CL args
        Scanner sc = null;

        FooCorporation fooCorp = new FooCorporation();

        // Opening the file
        try {
            sc = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Reading in from the file
        while (sc.hasNextLine()) {
            String s = sc.next();
            double basePay = 0;
            int hoursWorked = 0;
            String name = "";
            System.out.print(s);

            if (s.contains("#")) { // Ignoring the comments in the file
                sc.nextLine();
                continue;
            }
            String s2 = sc.next();
            // System.out.print(s2);
            // name = s2;
            // s2 = sc.next();
            // basePay = Double.parseDouble(s2);
            // s2 = sc.next();
            // hoursWorked = Integer.parseInt(s2);

            // try {
            //     fooCorp.totalPay(basePay, hoursWorked, name); // Both custom linked list and ArrayList resides in this
            //                                                   // class
            // } catch (InvalidBasePayException e1) {
            //     System.out.println(e1);
            // } catch (InvalidHoursWorkedException e2) {
            //     System.out.println(e2);
            // }
        }
        sc.close();

        // Sorting the linked list by total pay
        linkedList.printList(fooCorp.getEmployeesLinkedList());
        linkedList.sortList(fooCorp.getEmployeesLinkedList());
        System.out.println();
        linkedList.printList(fooCorp.getEmployeesLinkedList());
        System.out.println();

    }
}
