package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.ClassNotFoundException;

public class ClassNotFoundMapper implements ExceptionMapper<ClassNotFoundException> {
    @Override
    public Response toResponse(ClassNotFoundException e) {
        return Response
                .status(500)
                .entity(e.getMessage())
                .build();
    }
}

