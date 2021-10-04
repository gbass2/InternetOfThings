package showbikes;

import java.util.*;
import java.io.*;
import java.sql.SQLException;
import mysqljdbc.*;

/**
 * Queries the bikes database based on command line parameters.
 * 
 * @author      Grayson Bass <gbass2@uncc.edu>
 * @version     1.0
 * 
 */
public class ShowBikes {
	public static void main(String[] args) throws IOException {
		ArrayList<String> config = new ArrayList<String>(); // Holds the configuration from the command line argumanets
		
		// Printing the command line arguments
		System.out.print("Command Line args: ");
		for(String s: args)
			System.out.print(s + " ");
		
		// Adding the input arguments to config
		for(String s:args) {
			s = s.replace("-","");
			config.add(s);
		}
						
		// Making a connection to the database
		BikesJDBC bikes = new BikesJDBC();
		try {
			bikes.connectDataBase();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		// To add the bicycle combinations to the database, uncomment the below code
		
		String file = "bike_combos.csv";
		Scanner sc = new Scanner(new File(file));
		sc.useDelimiter(",");   //sets the delimiter pattern 
		
		// To write to the database uncomment the code below
//		// Reading the bike combinations from a csv
//		 while (sc.hasNext()) {
//				ArrayList<Object> input = new ArrayList<Object>();
//				
//	            for(int i = 0; i<6; i++) {
//	            	String s = sc.next();
//	            	s = s.replaceAll("\n","");
//	            	input.add(s);
//	            }
//	   		 
//	            // Writing each bike entry to the database
//	    		try {
//	    			bikes.writeDataBase(input);
//	    		} catch(SQLException e) {
//	    			System.out.println(e);
//	    		}
//	        }
//		 sc.close();
		 
	
	    // Reading from the database based on the configuration in arguments and sorting the results
		try {
			bikes.readDataBase(config);
		} catch(Exception e) {
			System.out.println(e);
		}
		 
		
		//Disconnecting from the database
		try {
//			bikes.clearDataBase(); // To clear the database upon disconnecting, uncomment this code
			bikes.disconnectDataBase();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
