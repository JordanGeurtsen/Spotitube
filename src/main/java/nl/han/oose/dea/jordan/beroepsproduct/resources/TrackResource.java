package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.TrackService;

@Path("/tracks")
public class TrackResource {
    private TrackService trackService;
    private LoginService loginService;

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getTracksForPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int id){
        loginService.authorize(token);
        return Response
                .status(200)
                .entity(trackService.getTracksSuitableForPlaylist(id))
                .build();
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
