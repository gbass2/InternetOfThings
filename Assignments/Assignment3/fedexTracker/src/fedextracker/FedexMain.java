package fedextracker;

import java.util.*;
import java.io.*;
import fedex.*;

/**
 * Simulates FedEX packaging system with the ability to use tracking number to query database for travel history and package information.
 * 
 * @author      Grayson Bass <gbass2@uncc.edu>
 * @version     1.0
 * 
 */
public class FedexMain {

	public static void main(String[] args) throws InterruptedException{
		// User input for tracking packages and adding packages
		boolean b = true;
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println("\n1: Add packages to database from csv ");
			System.out.println("2: Check status of package ");
			System.out.println("3: Exit the program ");
			String in = input.next();
			System.out.println(in);
			
			switch(in) {
				case "1":
					Fedex write = new Fedex("Write DataBase", ""); // Adding package to the database and simulating through the graph nodes
					break;
				case "2":
					System.out.println("\nEnter a tracking number: ");
					String trackingNumber = input.next();
					Fedex query = new Fedex("Read DataBase", trackingNumber);
					break;
				default: 
					b = false;
					// Clearing the packages from the database
					Fedex clear = new Fedex("Clear DataBase", ""); // Comment if you don't want to clear database
					System.out.println("\nExiting and clearing the database");
					Thread.interrupted();
					break;
			}
		} while (b == true);
		input.close();
	}
}
