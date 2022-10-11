package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
