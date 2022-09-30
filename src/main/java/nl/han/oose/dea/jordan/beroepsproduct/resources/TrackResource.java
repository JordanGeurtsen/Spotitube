package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.services.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.TrackService;

@Path("/tracks")
public class TrackResource {

    private TrackService trackService;
    private LoginService loginService;

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getTracksForPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int id){
        loginService.authorize(token);
        return Response.ok(trackService.getTracksSuitableForPlaylist(id, token)).build();
    }

    @Inject
    public void setTrackResource(TrackService trackService){
        this.trackService = trackService;
    }

    @Inject
    public void setLoginService(LoginService loginService){
        this.loginService = loginService;
    }
}
