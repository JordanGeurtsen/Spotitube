package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class UnauthorizedException extends SpotitubeException {
    public UnauthorizedException() {
        super("Unauthorized", 403);
    }
}
