package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.AccountNotFoundException;

@Provider
public class AccountNotFoundMapper implements ExceptionMapper<AccountNotFoundException> {

    @Override
    public Response toResponse(AccountNotFoundException e) {
        return Response.status(401)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
    }
}
