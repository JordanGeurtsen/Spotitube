package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.SpotitubeException;

@Provider
public class SpotitubeMapper implements ExceptionMapper<SpotitubeException> {

    @Override
    public Response toResponse(SpotitubeException e) {
        return Response
                .status(e.getStatus())
                .entity(e.getMessage())
                .build();
    }
}
