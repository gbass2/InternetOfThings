# ECGR 4090 Internet of Things Assignment 3

The assignment was programed in Eclipse using JavaSE 16.
Javadocs were used to describe each function in the code with their parameters and return statements.

## Included in the submission
- MySQL database file "FEDEX.sql"
- Java program to accept new packages from a csv and simulate them through a FedED route.

## Notes about the Java Code
There are 3 Java files included in the program.
- "FedexMain.java" is the main function to spawn the threads and deals with user input
    - A new thread is spawned when ever the user adds a package or queries for package information.
    - Another thread is spawned when the Simulation class is called to traverse the graph path.
- "Fedex.java" which handles the creation of the package and querying the database.
    - spawns a new thread when created.
- "Simulation.java" which handles the simulation of the shipment of the package through the connected cities from source to destination.
    - spawns a new thread when created.
    - Breadth First Search shortest path algorithm was used for traversing the graph which contains the connected cities.
    - The simulation is randomly started between 0 and 10 seconds.
    - There is a 10 second delay after the package has arrived to each city. 

## Input file
The csv is used to add packages to the database. It includes the following parameters:
- Weight
- Signature service
- Packing
- Service
- Dimensions
- Total pieces
- Special handling section
- Source
- Destination

## SQL database
The SQL database holds 2 tables
- shipment_facts: holds the information from the csv file with a randomly created tracking number.
- travel_history: holds the history of the route the package has taken.

## Output of command line
1: Adds the package from the csv to the database and starts the Simulation
2: Queries the database for shipping information and the travel history
3: Exits the program and clears the database
