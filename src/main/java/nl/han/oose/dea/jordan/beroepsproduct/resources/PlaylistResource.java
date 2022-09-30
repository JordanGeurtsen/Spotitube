package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.services.HardCodedPlaylistService;
import nl.han.oose.dea.jordan.beroepsproduct.services.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.PlaylistService;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TracklistDTO;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;
    private LoginService loginService;
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getPlaylists(@QueryParam("token") String token) {
        loginService.authorize(token);
        return Response.ok(playlistService.getAllPlaylists()).build();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        loginService.authorize(token);
        return Response.ok(playlistService.addPlaylist(playlist)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlaylist(@PathParam("id") int id, PlaylistDTO body, @QueryParam("token") String token) {
        loginService.authorize(token);
        return Response.ok(playlistService.updatePlaylist(id, body)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        loginService.authorize(token);
        var playlists = playlistService.deletePlaylist(id);
        return Response.ok(playlists).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces("application/json")
    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        loginService.authorize(token);
        var tracks = playlistService.getTracksFromPlaylist(id);
        return Response.ok(tracks).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTrackToPlaylist(@PathParam("id") int id, TrackDTO body, @QueryParam("token") String token) {
        loginService.authorize(token);
        var playlist = playlistService.addTrackToPlaylist(id, body);
        return Response.ok(playlist).build();
    }

    @DELETE
    @Path("/{id}/tracks/{trackId}")
    @Produces("application/json")
    public Response removeTrackFromPlaylist(@PathParam("id") int id, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        loginService.authorize(token);
        var tracklist = playlistService.removeTrackFromPlaylist(id, trackId);
        return Response.ok(tracklist).build();
    }

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
