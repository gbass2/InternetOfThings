package foocorp;

// Creating an exception for if the hours worked are less than 0 or greater than 60
public class InvalidHoursWorkedException extends Exception {
    public InvalidHoursWorkedException(String msg) {
        // Calling the constructor of parent Exception
        super(msg);
    }
}
