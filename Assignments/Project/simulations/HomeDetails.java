import java.io.*;
import java.util.*;
import java.sql.*;

class updateHomeDetails implements Runnable {
    private Connection connection = null;
    private Statement stmt = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    int frontdoor,backdoor,garagedoor;
    String securityStatus = "";
    String houseID= "";
    String name = "";

    Thread t; // thread for the database

    updateHomeDetails(String name, String houseID, String securityStatus, int front, int back, int garage){
      this.securityStatus = securityStatus;
      this.houseID = houseID;
      this.frontdoor = front;
      this.backdoor = back;
      this.garagedoor = garage;
      t = new Thread(this,name);
      t.start();
    }

    public void connectDataBase() throws SQLException {
    	System.out.println("\n\n-------- MySQL JDBC Connection Testing ------------");
        connection = DriverManager.getConnection("jdbc:mysql://192.168.2.10:3306/userdatabase", "android", "fzAbgzqqHruD4V5H");

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

    public void updateDoors() throws SQLException {

    }

    public void updateSecurityStatus() throws SQLException {
      if (connection == null){
            System.out.println("Failed to make connection!");
            return;
          }

      stmt = connection.createStatement();

      String sql = "UPDATE homestatus SET securityStatus = " + securityStatus + " WHERE homeID = " + houseID + ")";

      stmt.executeUpdate(sql);

    }

    public void run() {
        // TODO Auto-generated method stub
        try{
            updateSecurityStatus();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}

public class HomeDetails {
  	public static void main(String[] args) {
        updateHomeDetails update = new updateHomeDetails("thread",args[0],args[1],0,0,0);
    }
}
