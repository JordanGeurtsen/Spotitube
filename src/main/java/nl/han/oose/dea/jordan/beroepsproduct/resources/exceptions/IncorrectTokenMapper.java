package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.UnauthorizedException;

@Provider
public class IncorrectTokenMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(UnauthorizedException e) {
        return Response
                .status(403)
                .entity(e.getMessage())
                .build();
    }
}
