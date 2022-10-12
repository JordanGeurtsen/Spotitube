package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class DatabaseException extends SpotitubeException {
    public DatabaseException(String message) {
        super(message, 500);
    }
}
