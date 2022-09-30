package nl.han.oose.dea.jordan.beroepsproduct.services;

import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistResponseDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TracklistDTO;

public interface PlaylistService {
    PlaylistResponseDTO getAllPlaylists();
    TracklistDTO getTracksFromPlaylist(int id);
    PlaylistResponseDTO addPlaylist(PlaylistDTO playlist);
    PlaylistResponseDTO updatePlaylist(int id, PlaylistDTO playlistDTO);
    PlaylistResponseDTO deletePlaylist(int id);
    PlaylistResponseDTO addTrackToPlaylist(int playlistId, TrackDTO track);
    TracklistDTO removeTrackFromPlaylist(int playlistId, int trackID);
}
