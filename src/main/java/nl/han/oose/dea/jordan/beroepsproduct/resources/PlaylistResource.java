package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.PlaylistService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;
    private LoginService LoginService;
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getPlaylists(@QueryParam("token") String token) {
        LoginService.authorize(token);
        return Response
                .status(200)
                .entity(playlistService.getAllPlaylists())
                .build();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        LoginService.authorize(token);
        return Response
                .status(200)
                .entity(playlistService.addPlaylist(playlist))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlaylist(@PathParam("id") int id, PlaylistDTO body, @QueryParam("token") String token) {
        LoginService.authorize(token);
        return Response
                .status(200)
                .entity(playlistService.updatePlaylist(id, body))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        LoginService.authorize(token);
        var playlists = playlistService.deletePlaylist(id);
        return Response
                .status(200)
                .entity(playlists)
                .build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        LoginService.authorize(token);
        var tracks = playlistService.getTracksFromPlaylist(id);
        return Response
                .status(200)
                .entity(tracks)
                .build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTrackToPlaylist(@PathParam("id") int id, TrackDTO body, @QueryParam("token") String token) {
        LoginService.authorize(token);
        var playlist = playlistService.addTrackToPlaylist(id, body);
        return Response
                .status(200)
                .entity(playlist)
                .build();
    }

    @DELETE
    @Path("/{id}/tracks/{trackId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response removeTrackFromPlaylist(@PathParam("id") int id, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        LoginService.authorize(token);
        var tracklist = playlistService.removeTrackFromPlaylist(id, trackId);
        return Response
                .status(200)
                .entity(tracklist)
                .build();
    }

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setLoginService(LoginService LoginService) {
        this.LoginService = LoginService;
    }
}
