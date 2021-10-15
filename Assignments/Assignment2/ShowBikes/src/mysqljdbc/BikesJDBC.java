package mysqljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

// Connection to the bikes mysql database
public class BikesJDBC {
    private Connection connection = null;
    private Statement stmt = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    
    /**
    * Method to connect to the bikes mysql database.
    */
    public void connectDataBase() throws SQLException {
    	System.out.println("\n\n-------- MySQL JDBC Connection Testing ------------");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bikes", "java", "JavaCon!!??11");
        
        if (connection != null) {
            System.out.println("Connected to database!\n");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
    
    /**
    * Method to disconnect from the bikes mysql database.
    */
    public void disconnectDataBase() throws SQLException {
    	connection.close();
    	System.out.println("\nDisconnected from the database!");
    }

    /**
    * Method to query the database with parameters passed to this function.
    * @param config Command line configurations for querying the bikes database.
    */
    public void readDataBase(ArrayList<String> config) throws SQLException {
    	
        if (connection == null) {
            System.out.println("Failed to make connection!");
            return;
        }
        
        // Creating the query statement
        String query = "SELECT * FROM bikes WHERE";
        
        for(int i = 0; i < config.size() -1; i+=2) {
        	query = query +"("+ config.get(i) + " = ? OR ? IS NULL)";
        	if(i < (config.size() -3))
        		query = query + " AND ";
        }
        preparedStatement = connection.prepareStatement(query);
        
        
        if(config.size() <= 1) {
        	query  = "SELECT * FROM bikes";
        	preparedStatement = connection.prepareStatement(query);
        }
        if(config.size() == 2 || config.size() == 3) {
        	 preparedStatement.setObject(1, config.get(1));
        	 preparedStatement.setObject(2, config.get(1));
        }
        
        if(config.size() == 4 || config.size() == 5) {
        	preparedStatement.setObject(1, config.get(1));
        	preparedStatement.setObject(2, config.get(1));
       	 	preparedStatement.setObject(3, config.get(3));
       	 	preparedStatement.setObject(4, config.get(3));
       }
        
       if(config.size() == 6 || config.size() == 7) {
       		preparedStatement.setObject(1, config.get(1));
       		preparedStatement.setObject(2, config.get(1));
      	 	preparedStatement.setObject(3, config.get(3));
      	 	preparedStatement.setObject(4, config.get(3));
      	 	preparedStatement.setObject(5, config.get(5));
      	 	preparedStatement.setObject(6, config.get(5));
       }
       
       if(config.size() == 8 || config.size() == 9) {
       	preparedStatement.setObject(1, config.get(1));
	   		preparedStatement.setObject(1, config.get(1));
	   		preparedStatement.setObject(2, config.get(1));
	  	 	preparedStatement.setObject(3, config.get(3));
	  	 	preparedStatement.setObject(4, config.get(3));
	  	 	preparedStatement.setObject(5, config.get(5));
	  	 	preparedStatement.setObject(6, config.get(5));
	  	 	preparedStatement.setObject(7, config.get(7));
	  	 	preparedStatement.setObject(8, config.get(7));
	  	 	
      }
       if(config.size() == 10 || config.size() == 11) {
	   		preparedStatement.setObject(1, config.get(1));
	   		preparedStatement.setObject(2, config.get(1));
	  	 	preparedStatement.setObject(3, config.get(3));
	  	 	preparedStatement.setObject(4, config.get(3));
	  	 	preparedStatement.setObject(5, config.get(5));
	  	 	preparedStatement.setObject(6, config.get(5));
	  	 	preparedStatement.setObject(7, config.get(7));
	  	 	preparedStatement.setObject(8, config.get(7)); 	
	  	 	preparedStatement.setObject(9, config.get(9));
	  	 	preparedStatement.setObject(10, config.get(9)); 
     }
       if(config.size() == 12 || config.size() == 13) {
	   		preparedStatement.setObject(1, config.get(1));
	   		preparedStatement.setObject(2, config.get(1));
	  	 	preparedStatement.setObject(3, config.get(3));
	  	 	preparedStatement.setObject(4, config.get(3));
	  	 	preparedStatement.setObject(5, config.get(5));
	  	 	preparedStatement.setObject(6, config.get(5));
	  	 	preparedStatement.setObject(7, config.get(7));
	  	 	preparedStatement.setObject(8, config.get(7)); 	
	  	 	preparedStatement.setObject(9, config.get(9));
	  	 	preparedStatement.setObject(10, config.get(9)); 
	  	 	preparedStatement.setObject(11, config.get(11)); 
	  	 	preparedStatement.setObject(12, config.get(11)); 
     }
           
 	   // Receiving the bikes that match the parameters
       System.out.println("Querying the database");
	   rs = preparedStatement.executeQuery();
	  
	   // Storing the results in a 2d array to be sorted
	   ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>(); 
       while (rs.next()) {
    	   ArrayList<String> result = new ArrayList<String>();
    	   result.add(rs.getObject(1).toString());
    	   result.add(rs.getObject(2).toString());
    	   result.add(rs.getObject(3).toString());
    	   result.add(rs.getObject(4).toString());
    	   result.add(rs.getObject(5).toString());
	       result.add(rs.getObject(6).toString());
	       results.add(result);
        }
       
       // Sorting and printing the query results
       HashMap<String,Integer> sortParams = new HashMap<String, Integer>(); 
       sortParams.put("type", 0);
       sortParams.put("gear", 1);
       sortParams.put("wheelbase", 2);
       sortParams.put("height", 3);
       sortParams.put("color", 4);
       sortParams.put("construction_material", 5);
       
       if(config.size() % 2 !=0) {
	       final int column = sortParams.get(config.get(config.size() -1));
	       Comparator<ArrayList<String>> myComparator = new Comparator<ArrayList<String>>() {
	           @Override
	           public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	               return o1.get(column).compareTo(o2.get(column));
	           }
	       };
	       Collections.sort(results, myComparator);
	       System.out.println("\nSorting the results");
       }
	   
       System.out.println("\nPrinting the results");
       for(int i = 0; i < results.size(); i++) {
           System.out.println(results.get(i));
       }
    }
    
    /**
    * Method to write the bicycle combinations to the bikes mysql database.
    * @param bikeList Holds all the possible combinations for the bicycles.
    */
	public void writeDataBase(ArrayList<Object> bikeList) throws SQLException {
	        if (connection == null)
	            System.out.println("Failed to make connection!");
	        
	        stmt = connection.createStatement();
	        String sql = "INSERT INTO bikes (type,gear,wheelbase,height,color,construction_material) " +
	        		"VALUES ('" + 
	        		bikeList.get(0) + "', " + 
	        		bikeList.get(1) + ", " + 
	        		bikeList.get(2) + ", " + 
	        		bikeList.get(3) + ", '" +
	        		bikeList.get(4) + "', '" +
	        		bikeList.get(5) + "')";
	        
	        System.out.println("Adding entries to the database");
	        stmt.executeUpdate(sql);	        
	 }
	
    /**
    * Method to clear the table in the bikes mysql database.
    */
	public void clearDataBase() throws SQLException {
        if (connection == null) {
            System.out.println("Failed to make connection!");
            return;
        }
        
        stmt = connection.createStatement();
        String sql = "TRUNCATE TABLE bikes";
        
        System.out.println("Clearing the database");
        stmt.executeUpdate(sql);
	}
}