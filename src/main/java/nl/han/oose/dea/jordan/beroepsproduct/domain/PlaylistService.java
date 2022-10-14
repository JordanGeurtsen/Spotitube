package nl.han.oose.dea.jordan.beroepsproduct.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.data.dao.PlaylistDAO;
import nl.han.oose.dea.jordan.beroepsproduct.data.dao.TracklistDAO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistResponseDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.UnauthorizedException;

import java.util.List;

@Default
@ApplicationScoped
public class PlaylistService {
    private PlaylistDAO playlistDAO;
    private TracklistDAO tracklistDAO;
    private LoginService loginService;
    private TrackService trackService;
    private final PlaylistResponseDTO playlistResponseDTO;

    public PlaylistService() {
        playlistResponseDTO = new PlaylistResponseDTO();
    }

    public PlaylistResponseDTO getAllPlaylists() {
        List<PlaylistDTO> playlists = playlistDAO.getAll();
        for (PlaylistDTO playlist : playlists) {
            playlist.setOwner(loginService.getCurrentUser().getId() == playlist.getOwnerID());

            TracklistDTO tracklistDTO = trackService.getTracksFromPlaylist(playlist.getId());
            playlist.setTracks(tracklistDTO);
            playlist.setLength(tracklistDTO.getLength());
        }
        playlistResponseDTO.setLength(playlists.stream().mapToInt(PlaylistDTO::getLength).sum());
        playlistResponseDTO.setPlaylists(playlists);
        return playlistResponseDTO;
    }

    public PlaylistResponseDTO addPlaylist(PlaylistDTO playlist) {
        playlist.setOwnerID(loginService.getCurrentUser().getId());
        playlistDAO.insert(playlist);
        return getAllPlaylists();
    }

    public PlaylistResponseDTO updatePlaylist(int id, PlaylistDTO playlistDTO) {
        playlistDAO.update(playlistDTO);
        return getAllPlaylists();
    }

    public PlaylistResponseDTO deletePlaylist(int id) {
        if(playlistDAO.get(id).isPresent() && loginService.getCurrentUser().getId() != playlistDAO.get(id).get().getOwnerID()) {
            throw new UnauthorizedException();
        }
        playlistDAO.delete(id);
        return getAllPlaylists();
    }

    public TracklistDTO getTracksFromPlaylist(int id) {
        return trackService.getTracksFromPlaylist(id);
    }

    public TracklistDTO addTrackToPlaylist(int playlistId, TrackDTO track) {
        tracklistDAO.insertWithPlaylistTrackAndOfflineAvailable(playlistId, track.getId(), track.getOfflineAvailable());
        return getTracksFromPlaylist(playlistId);
    }

    public TracklistDTO removeTrackFromPlaylist(int playlistId, int trackID) {
        tracklistDAO.deleteWithPlayListAndTrack(playlistId, trackID);
        return getTracksFromPlaylist(playlistId);
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    public void setTracklistDAO(TracklistDAO trackListDAO) {
        this.tracklistDAO = trackListDAO;
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
}
