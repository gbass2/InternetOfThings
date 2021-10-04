# ECGR 4090 Internet of Things Assignment 2

The assignment was programed in Eclipse using JavaSE 16

## Included in the submission
- Jupyter(Python) script to create all the bike combinations.
- MySQL database file - "bikes.sql"
- Java program to add the csv data to the MySQL database and query the database from command line arguments.


## Notes about the Java Code
There are 2 Java files included in the program. The main function is in "ShowBikes.java" and the MySQL JDBC is contained in "BikesJDBC.java"

BikesJDBC has 5 methods:
- Method to connect to the database.
- Method to disconnect from the database.
- Method to clear the table in the database.
    - To clear the database upon disconnecting, line 75 of "ShowBikes.java" needs to be uncommented.

- Method to write the bike combinations to the database.
    - The database is already populated, so you do not need to do this.
    - To populate the database, lines 46 - 62 of "ShowBikes.java" needs to be uncommented. The database needs to be cleared first so that there are not duplicate entries in the database.

- Method to query the database based on command line arguments.
    -   When an odd number of arguments are given the results from the query will also be sorted.
    -   The results will not be sorted if an even number of command line arguments are given.
