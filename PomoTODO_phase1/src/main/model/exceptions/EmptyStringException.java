package model.exceptions;

public class EmptyStringException extends IllegalArgumentException {

    public EmptyStringException() {
        super();
    }

    public EmptyStringException(String s) {
        super(s);
    }
}
