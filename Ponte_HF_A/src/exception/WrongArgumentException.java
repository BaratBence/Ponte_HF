package exception;

public class WrongArgumentException extends Exception {
    public WrongArgumentException() {

        super("Type and Field does not match");
    }
}
