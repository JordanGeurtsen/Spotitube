package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Unauthorized");
    }
}
