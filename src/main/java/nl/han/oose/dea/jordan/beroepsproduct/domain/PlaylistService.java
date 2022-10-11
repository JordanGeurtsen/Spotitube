package nl.han.oose.dea.jordan.beroepsproduct.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.datasource.dao.PlaylistDAO;
import nl.han.oose.dea.jordan.beroepsproduct.datasource.dao.TrackListDAO;
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
    private TrackListDAO tracklistDAO;
    private LoginService loginService;
    private TrackService trackService;
    private final PlaylistResponseDTO playlistResponseDTO;

    public PlaylistService() {
        playlistResponseDTO = new PlaylistResponseDTO();
    }

    public PlaylistResponseDTO getAllPlaylists() {
        List<PlaylistDTO> playlists = playlistDAO.getAll();
        for (PlaylistDTO playlist : playlists) {
            playlist.setOwner(loginService.getCurrentUser().getId() == playlist.getOwnerId());
            if (tracklistDAO.get(playlist.getId()).isPresent()) {
                playlist.setTracks(tracklistDAO.get(playlist.getId()).get());
            }
        }
        playlistResponseDTO.setPlaylists(playlists);
        return playlistResponseDTO;
    }

    public PlaylistResponseDTO addPlaylist(PlaylistDTO playlist) {
        playlist.setOwnerId(loginService.getCurrentUser().getId());
        playlistDAO.insert(playlist);
        return getAllPlaylists();
    }

    public PlaylistResponseDTO updatePlaylist(int id, PlaylistDTO playlistDTO) {
        playlistDAO.update(playlistDTO);
        return getAllPlaylists();
    }

    public PlaylistResponseDTO deletePlaylist(int id) {
        if(playlistDAO.get(id).isPresent() && loginService.getCurrentUser().getId() != playlistDAO.get(id).get().getOwnerId()) {
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
    public void setTracklistDAO(TrackListDAO trackListDAO) {
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
