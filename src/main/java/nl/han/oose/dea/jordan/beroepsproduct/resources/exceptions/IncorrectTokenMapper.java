package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.InvalidTokenException;

@Provider
public class IncorrectTokenMapper implements ExceptionMapper<InvalidTokenException> {

    @Override
    public Response toResponse(InvalidTokenException e) {
        return Response.status(401)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
    }
}
