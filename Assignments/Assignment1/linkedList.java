package linkedlist;

import employee.*;
import java.io.*;

// A Singly Linked List
public class linkedList {

    Node head; // head of list

    // Linked list node
    static class Node {

        Employee employee = new Employee();
        Node next;

        Node(Employee employee) {
            this.employee = employee;
            next = null;
        }
    }

    // Method to insert a new node
    public static linkedList insert(linkedList list, Employee employee) {
        // Create a new node with given data
        Node new_node = new Node(employee);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the list by head
        return list;
    }

    // Method to print the LinkedList.
    public static void printList(linkedList list) {
        Node currNode = list.head;

        // Traverse through the LinkedList
        while (currNode != null) {
            System.out.print("LinkedList: ");

            // Print the data at current node
            System.out.print("Name: " + currNode.employee.getName() + " Base Pay: " + currNode.employee.getBasePay()
                    + " Hours Worked: " + currNode.employee.getHoursWorked() + " Total Pay: "
                    + currNode.employee.getTotalPay() + "\n");

            // Go to next node
            currNode = currNode.next;
        }
    }

    public static void sortList(linkedList list) {
        // Node current will point to head
        Node current = list.head, index = null;

        double temp;

        if (list.head == null) {
            return;
        } else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.next;

                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (current.employee.getTotalPay() > index.getTotalPay()) {
                        temp = current.employee;
                        current.employee = index.employee;
                        index.employee = temp;
                    }

                    index = index.next;
                }
                current = current.next;
            }
        }
    }
}
