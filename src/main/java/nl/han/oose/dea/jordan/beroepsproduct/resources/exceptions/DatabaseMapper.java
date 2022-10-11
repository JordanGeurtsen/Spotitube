package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.DatabaseException;

@Provider
public class DatabaseMapper implements ExceptionMapper<DatabaseException> {
    @Override
    public Response toResponse(DatabaseException e) {
        return Response
                .status(500)
                .entity(e.getMessage())
                .build();
    }
}
