package dev.asgui.address.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1l;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
