package model.exceptions;

public class NullArgumentException extends IllegalArgumentException {

    public NullArgumentException() {
        super();
    }

    public NullArgumentException(String s) {
        super(s);
    }
}
