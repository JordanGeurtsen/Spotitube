package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.FileNotFoundException;

public class FileNotFoundMapper implements ExceptionMapper<FileNotFoundException> {
    @Override
    public Response toResponse(FileNotFoundException e) {
        return Response
                .status(404)
                .entity(e.getMessage())
                .build();
    }
}
