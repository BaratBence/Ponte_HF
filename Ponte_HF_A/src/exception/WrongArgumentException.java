package exception;

public class WrongArgumentException extends Exception {
    public WrongArgumentException() {

        super("Driver already added to the finishers");
    }
}
