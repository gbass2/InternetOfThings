package fedex;

import java.util.*; 
import java.sql.SQLException; 

/**
* Method to simulate the shipping of a package
*/

public class Simulation implements Runnable {
	private HashMap<String,Integer> citiesInt = new HashMap<String,Integer>(); // Converts the city,state to an integer
	private HashMap<Integer,String> citiesStr = new HashMap<Integer,String>(); // Converts the integer to city, state

	static final int num_Vertices = 25;  //max number of vertices in graph
	Fedex query; // Instance to query and write to database
	String trackingNumber;
	Thread t; // thread for simulating the package through each city

	
	public Simulation(String name, String trackingNumber) throws SQLException, InterruptedException {
		this.trackingNumber = trackingNumber;
		addCities();
    	t = new Thread(this,name);
    	t.start();
    } 
	
	/**
	    * Adds the cities to the hashmap that converts the cities to integers for the graph.
	*/
	private void addCities() {
		citiesInt.put("Northborough, MA", 0); citiesInt.put("Edison, NJ", 1); citiesInt.put("Pittsburgh, PA", 2); citiesInt.put("Allentown, PA", 3);
		citiesInt.put("Martinsburg, WV", 4); citiesInt.put("Charlotte, NC", 5); citiesInt.put("Atlanta, GA", 6); citiesInt.put("Orlando, FL", 7);
		citiesInt.put("Memphis, TN", 8); citiesInt.put("Grove City, OH", 9); citiesInt.put("Indianapolis, IN", 10); citiesInt.put("Detroit, MI", 11);
		citiesInt.put("New Berlin, WI", 12); citiesInt.put("Minneapolis, MN", 13); citiesInt.put("St. Louis, MO", 14); citiesInt.put("Kansas, KS", 15); 
		citiesInt.put("Dallas, TX", 16); citiesInt.put("Houston, TX", 17); citiesInt.put("Denver, CO", 18); citiesInt.put("Salt Lake City, UT", 19);
		citiesInt.put("Phoenix, AZ", 20); citiesInt.put("Los Angeles, CA", 21); citiesInt.put("Chino, CA", 22);citiesInt.put("Sacramento, CA", 23);
		citiesInt.put("Seattle, WA", 24);
		
		citiesStr.put(0,"Northborough, MA"); citiesStr.put(1, "Edison, NJ"); citiesStr.put(2,"Pittsburgh, PA"); citiesStr.put(3, "Allentown, PA");
		citiesStr.put(4,"Martinsburg, WV"); citiesStr.put(5,"Charlotte, NC"); citiesStr.put(6,"Atlanta, GA"); citiesStr.put(7, "Orlando, FL");
		citiesStr.put(8, "Memphis, TN"); citiesStr.put(9, "Grove City, OH"); citiesStr.put(10, "Indianapolis, IN"); citiesStr.put(11, "Detroit, MI");
		citiesStr.put(12, "New Berlin, WI"); citiesStr.put(13, "Minneapolis, MN"); citiesStr.put(14, "St. Louis, MO"); citiesStr.put(15, "Kansas, KS"); 
		citiesStr.put(16, "Dallas, TX"); citiesStr.put(17, "Houston, TX"); citiesStr.put(18, "Denver, CO"); citiesStr.put(19,"Salt Lake City, UT");
		citiesStr.put(20,"Phoenix, AZ"); citiesStr.put(21, "Los Angeles, CA"); citiesStr.put(22, "Chino, CA");citiesStr.put(23,"Sacramento, CA");
		citiesStr.put(24, "Seattle, WA");
		
	
	}
	
	/**
	* Method to form an edge between two vertices.
	* @param adj List that holds the graph.
	* @param i source vertex.
	* @param j destination vertex.
	*/
    //
    private void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
 
	/**
	* Method to find the shortest path between source vertex and destination vertex.
	* @param adj List that holds the graph.
	* @param s source vertex.
	* @param dest destination vertex.
	*/

    private void shortestPath(ArrayList<ArrayList<Integer>> adj, int s, int dest) throws InterruptedException, SQLException{
        // Predecessor[i] array stores predecessor of i
    	// Distance array stores distance of i from s
        int pred[] = new int[num_Vertices];
        int dist[] = new int[num_Vertices];
 
        if (BFS(adj, s, dest, num_Vertices, pred, dist) == false) {
            System.out.println("Given source and destination are not connected");
            return;
        }
 
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
 
        // Print distance
//        System.out.println("Shortest path length is: " + dist[dest]);
 
        // Print path
        for (int i = path.size() - 1; i >= 0; i--) {
//            System.out.print(citiesStr.get(path.get(i)) + " ");
            // Creating an array that holds the first entry to the travel history database
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
			ArrayList<Object> activity = new ArrayList<Object>(Arrays.asList(sqlDate, "Arrived in " + citiesStr.get(path.get(i))));
			query.writeTravelHistory(trackingNumber, activity);
			Thread.sleep(10000);
        }
    }
 
	/**
	* Helper method for shortestPath. Calculates the breadth first search shortest path.
	* @param adj List that holds the graph.
	* @param src Source vertex
	* @param dest Destination vertex.
	* @param v Number of vertices
	* @param pred Stores predecessor of i
	* @param dist Stores distance of i from src
	* @return Returns a boolean value on whether the src and dest are connected
	*/
    private boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[]) {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];
 
        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);
 
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
 
                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
    
	/**
	    * Simulates the shipment through each city in the path from source to destination addresses.
	*/
	public void simShipment() throws SQLException, InterruptedException {
		// Querying the database for the source and destination address of the current package
		ArrayList<String> packet = query.readShipmentFacts();
		
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(num_Vertices); // Graph to hold the nodes
        
        for (int i = 0; i < num_Vertices; i++) {
        	adj.add(new ArrayList<Integer>());
        }
        
        addEdge(adj, 0, 1); addEdge(adj, 0, 2); addEdge(adj, 0, 3); addEdge(adj, 0, 4);
        addEdge(adj, 1, 0); addEdge(adj, 1, 2); addEdge(adj, 1, 3); addEdge(adj, 1, 4);
        addEdge(adj, 2, 1); addEdge(adj, 2, 3); addEdge(adj, 2, 4); addEdge(adj, 2, 9);
        addEdge(adj, 3, 0); addEdge(adj, 3, 1); addEdge(adj, 3, 2); addEdge(adj, 3, 4);
        addEdge(adj, 4, 1); addEdge(adj, 4, 2); addEdge(adj, 4, 3); addEdge(adj, 4, 9);
        addEdge(adj, 5, 4); addEdge(adj, 5, 6); addEdge(adj, 5, 8); addEdge(adj, 5, 9);
        addEdge(adj, 6, 5); addEdge(adj, 6, 7); addEdge(adj, 6, 8); addEdge(adj, 6, 10);
        addEdge(adj, 7, 4); addEdge(adj, 7, 5); addEdge(adj, 7, 6); addEdge(adj, 7, 8);
        addEdge(adj, 8, 6); addEdge(adj, 8, 14); addEdge(adj, 8, 15); addEdge(adj, 8, 16);
        addEdge(adj, 9, 2); addEdge(adj, 9, 10); addEdge(adj, 9, 11); addEdge(adj, 9, 14);
        addEdge(adj, 10, 9); addEdge(adj, 10, 11); addEdge(adj, 10, 12); addEdge(adj, 10, 14);
        addEdge(adj, 11, 2); addEdge(adj, 11, 9); addEdge(adj, 11, 10); addEdge(adj, 11, 12);
        addEdge(adj, 12, 10); addEdge(adj, 12, 11); addEdge(adj, 12, 13); addEdge(adj, 12, 14);
        addEdge(adj, 13, 10); addEdge(adj, 13, 12); addEdge(adj, 13, 14); addEdge(adj, 13, 15);
        addEdge(adj, 14, 8); addEdge(adj, 14, 10); addEdge(adj, 14, 12); addEdge(adj, 14, 15);
        addEdge(adj, 15, 10); addEdge(adj, 15, 14); addEdge(adj, 15, 16); addEdge(adj, 15, 19);
        addEdge(adj, 16, 8); addEdge(adj, 16, 15); addEdge(adj, 16, 17); addEdge(adj, 16, 20);
        addEdge(adj, 17, 8); addEdge(adj, 17, 14); addEdge(adj, 17, 15); addEdge(adj, 17, 16);
        addEdge(adj, 18, 16); addEdge(adj, 18, 15); addEdge(adj, 18, 19); addEdge(adj, 18, 20);
        addEdge(adj, 19, 18); addEdge(adj, 19, 20); addEdge(adj, 19, 22); addEdge(adj, 19, 23);
        addEdge(adj, 20, 19); addEdge(adj, 20, 21); addEdge(adj, 20, 22); addEdge(adj, 20, 23);
        addEdge(adj, 21, 19); addEdge(adj, 21, 20); addEdge(adj, 21, 22); addEdge(adj, 21, 23);
        addEdge(adj, 22, 19); addEdge(adj, 22, 20); addEdge(adj, 22, 21); addEdge(adj, 22, 23);
        addEdge(adj, 23, 19); addEdge(adj, 23, 21); addEdge(adj, 23, 22); addEdge(adj, 23, 24);
        addEdge(adj, 24, 19); addEdge(adj, 24, 21); addEdge(adj, 24, 22); addEdge(adj, 24, 23);

        // Calculating shortest path and adding them to the database
        shortestPath(adj, citiesInt.get(packet.get(8)), citiesInt.get(packet.get(9)));
	}

	@Override
	public void run() {		
		try {
			// Randomizing the start of simulation
			Calendar currTime = Calendar.getInstance(); // Current time
			Calendar executeTime = Calendar.getInstance(); // Time to execute shipment
			int rand = (int) (Math.random() * 10); // Random seconds between 0 - 10
			executeTime.add(Calendar.SECOND, rand); // Adding the random time to the current time
						
			while(currTime.compareTo(executeTime) == -1) {
				currTime.add(Calendar.SECOND, 1);
				Thread.sleep(1000);
			}
			
			// Connecting to the database
			query = new Fedex("Read ShipmentFacts", trackingNumber);
			query.connectDataBase();
			// Simulating the package through the cities
			simShipment();
			
		} catch(SQLException e1) {
			System.out.println(e1);
		} catch(InterruptedException e2) {
			System.out.println(e2);
		}
	}
}
