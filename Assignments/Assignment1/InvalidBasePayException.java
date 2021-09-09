package foocorp;

// Creating an exception for if the base pay is below 8
public class InvalidBasePayException extends Exception {
    public InvalidBasePayException(String msg) {
        // Calling the constructor of parent Exception
        super(msg);
    }
}
