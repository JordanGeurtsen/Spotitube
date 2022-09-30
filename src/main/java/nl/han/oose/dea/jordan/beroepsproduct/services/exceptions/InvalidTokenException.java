package nl.han.oose.dea.jordan.beroepsproduct.services.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Invalid token");
    }
}
