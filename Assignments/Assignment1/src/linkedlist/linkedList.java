package linkedlist;

import foocorp.Employee;

// A Singly Linked List
public class linkedList {

    Node head; // head of list

    // Linked list node
    static class Node {

        Employee employee = new Employee(0.0,0, "", 0.0);
        Node next;

        Node(Employee employee) {
            this.employee = employee;
            next = null;
        }
    }

    // Method to insert a new node
    public static linkedList insert(linkedList list, Employee employee) {

    	Node new_node = new Node(employee);
        new_node.next = null;

        if (list.head == null) {
            list.head = new_node;
        } else {

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

        while (currNode != null) {
        	
            System.out.print(currNode.employee.getName() + "'s Total Pay: " + currNode.employee.getTotalPay() + "\n");

            currNode = currNode.next;
        }
        System.out.println();
    }

    public static void sortList(linkedList list) {
        
    	// Node current will point to head
        Node current = list.head, index = null;

        Employee temp = new Employee(0.0,0, "", 0.0);

        if (list.head == null) {
            return;
        } else {
            while (current != null) {
                // Node index will point to node next to current
                index = current.next;

                while (index != null) {
                    // If current node's data is greater than index's node data, swap the data between them
                    if (current.employee.getTotalPay() > index.employee.getTotalPay()) {
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
