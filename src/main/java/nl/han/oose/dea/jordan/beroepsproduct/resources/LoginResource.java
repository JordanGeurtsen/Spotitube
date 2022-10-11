package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.services.HardCodedLoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.UserDTO;

@Path("/login")
public class LoginResource {

    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO login) {
        var user = loginService.login(login.getUser(), login.getPassword());
        return Response.ok(user).build();
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
