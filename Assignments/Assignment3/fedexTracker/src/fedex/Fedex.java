package fedex;

import java.io.*;
import java.util.*;
import java.sql.*;

// Connection to the bikes mysql database
public class Fedex implements Runnable {
    private Connection connection = null;
    private Statement stmt = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    
    Thread t; // thread for the database
    private String trackingNumber;
    
    public Fedex(String name, String trackingNumber) throws InterruptedException {
    	this.trackingNumber = trackingNumber;
    	t = new Thread(this,name);
    	t.start();
    }
    
    /**
    * Method to connect to the fedex mysql database.
    */
    public void connectDataBase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/FEDEX", "java", "JavaCon!!??11");
        
        if (connection == null)
            System.out.println("Failed to make connection!");
        
    }
    
    /**
    * Method to disconnect from the fedex mysql database.
    */
    public void disconnectDataBase() throws SQLException {
    	connection.close();
    }

    /**
    * Method to query the travel history of a package from the database
    */
    public void readTravelHistory() throws SQLException {
    	
        if (connection == null) {
            System.out.println("Failed to make connection!");
            return;
        }

        // Querying for the travel history
        String query = "SELECT * FROM travel_history WHERE (tracking_number = ? OR ? IS NULL)";
       
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, trackingNumber);
        preparedStatement.setObject(2, trackingNumber);
          
        rs = preparedStatement.executeQuery();
        
	    System.out.println();
        ArrayList<ArrayList<String>> travelHistory = new ArrayList<ArrayList<String>>(); 
        while (rs.next()) {
     	   	ArrayList<String> result = new ArrayList<String>();
     	    result.add(rs.getObject(1).toString());
     	    result.add(rs.getObject(2).toString());
     	    result.add(rs.getObject(3).toString());
        	travelHistory.add(result);
        }

        if(t.getName() == "Read DataBase") {
	        System.out.println("\nTravel History:");
	        for(int i = 0; i < travelHistory.size(); i++)
	        	System.out.println(travelHistory.get(i));
        }
    }
    
    /**
    * Method to query the shipment facts of a package from the database
    */
     public ArrayList<String> readShipmentFacts() throws SQLException {
     	
         if (connection == null) {
             System.out.println("Failed to make connection!");
             return null;
         }
         
         // Querying for the packaging info
         String query = "SELECT * FROM shipment_facts WHERE (tracking_number = ? OR ? IS NULL)";
         
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setObject(1, trackingNumber);
         preparedStatement.setObject(2, trackingNumber);
            
         rs = preparedStatement.executeQuery();
 	  
         ArrayList<String> shipmentInfo = new ArrayList<String>(); 
 	   
         while (rs.next()) {
         	shipmentInfo.add(rs.getObject(1).toString());
         	shipmentInfo.add(rs.getObject(2).toString());
         	shipmentInfo.add(rs.getObject(3).toString());
         	shipmentInfo.add(rs.getObject(4).toString());
         	shipmentInfo.add(rs.getObject(5).toString());
         	shipmentInfo.add(rs.getObject(6).toString());
         	shipmentInfo.add(rs.getObject(7).toString());
         	shipmentInfo.add(rs.getObject(8).toString());
         	shipmentInfo.add(rs.getObject(9).toString());
         	shipmentInfo.add(rs.getObject(10).toString());
         }
         
         
         if(t.getName() == "Read DataBase") {
             System.out.print("\nShipment Facts: ");
             System.out.println(shipmentInfo);
         }
        	 
         return shipmentInfo;
     }
    
    /**
    * Method to create a new packet from a csv file, enter it into the fedex database, and simulate it through the graph.
    */
    public void createPacket() throws IOException, SQLException, InterruptedException {
    	String file = "packages.csv";
		Scanner sc = new Scanner(new File(file));
		sc.useDelimiter(";");   //sets the delimiter pattern 

		// Reading the packages from a csv
		 while (sc.hasNext()) {
				ArrayList<Object> input = new ArrayList<Object>();
				
				// Creating a random tracking number
				Random rnd = new Random();
				long n = 8000000000000L + rnd.nextInt(1000000000);
				String trackingNumber = "" + n + "";
								
	            for(int i = 0; i<9; i++) {
	            	String s = sc.next();
	            	s = s.replaceAll("\n","");
	            	input.add(s);
	            }
    			
	            // Adding the packages to the database
	    		writeShipmentFacts(trackingNumber, input);
	    		System.out.println("The tracking number for the package is: " + trackingNumber);
	    		
	    		// Simulating the package through the cities
	    		Simulation sim = new Simulation("Sim",trackingNumber);
	        }
		 sc.close();
    }
    
    /**
    * Method to write the shipment facts to the FEDEX mysql database.
    * @param list Holds all the information about the shipment
    */
	public void writeShipmentFacts(String trackingNumber, ArrayList<Object> shipmentList) throws SQLException {
	        if (connection == null)
	            System.out.println("Failed to make connection!");
	        
	        stmt = connection.createStatement();
	        String sql = "INSERT INTO shipment_facts (tracking_number,weight,signature_service,packaging,service,dimensions,total_pieces,special_handling,source,destination) " +
	        		"VALUES (" + 
	        		trackingNumber + ", '" + 
	        		shipmentList.get(0) + "', '" + 
	        		shipmentList.get(1) + "', '" + 
	        		shipmentList.get(2) + "', '" +
	        		shipmentList.get(3) + "', '" +
	        		shipmentList.get(4) + "', " +
	        		shipmentList.get(5) + ", '" +
	        		shipmentList.get(6) + "', '" +
	        		shipmentList.get(7) + "', '" +
	        		shipmentList.get(8) + "')";
	        
	        stmt.executeUpdate(sql);	  
	 }
	
    /**
    * Method to write the travel history of a package to the FEDEX mysql database.
    * @param packageList Holds the activity of a package
    */
	public void writeTravelHistory(String trackingNumber, ArrayList<Object> travelHistory) throws SQLException {
	        if (connection == null)
	            System.out.println("Failed to make connection!");
	        
	        stmt = connection.createStatement();
	        String sql = "INSERT INTO travel_history (tracking_number,date_time,activity) " +
	        		"VALUES (" + 
	        		trackingNumber + ", '" + 
	        		travelHistory.get(0) + "', '" + 
	        		travelHistory.get(1) + "')";
	        
	        stmt.executeUpdate(sql);	        
	 }
	
    /**
    * Method to clear the tables in the FEDEX mysql database.
    */
	public void clearDataBase() throws SQLException {
        if (connection == null) {
            System.out.println("Failed to make connection!");
            return;
        }
        
        stmt = connection.createStatement();
        String sql = "TRUNCATE TABLE shipment_facts;";
        stmt.executeUpdate(sql);
        sql = "TRUNCATE TABLE travel_history;";
        stmt.executeUpdate(sql);
	}
	
	@Override
	public void run() {
		try {
			connectDataBase();
				
			if(t.getName() == "Write DataBase") 
				createPacket();
			if(t.getName() == "Clear DataBase")
				clearDataBase();
			if(t.getName() == "Read DataBase") {
				readTravelHistory();
				readShipmentFacts();
			}
					
		} catch (SQLException e1) {
			System.out.println(e1);
		} catch (IOException e2) {
			System.out.println(e2);
		} catch(InterruptedException e3) {
			System.out.print(e3);
		}
	}
}