package nl.han.oose.dea.jordan.beroepsproduct.services.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistResponseDTO {

    private List<PlaylistDTO> playlists = new ArrayList<>();
    private int length = 0;

    public PlaylistResponseDTO() {
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
        updateLength();
    }

    public void addPlaylist(PlaylistDTO playlist) {
        playlists.add(playlist);
        updateLength();
    }

    public void removePlaylist(int id) {
        for (PlaylistDTO playlist : playlists) {
            if (playlist.getId() == id) {
                playlists.remove(playlist);
                break;
            }
        }
        updateLength();
    }

    public void replacePlaylist(int id, PlaylistDTO playlistDTO) {
        for (PlaylistDTO playlist : playlists) {
            if (playlist.getId() == id) {
                playlist = playlistDTO;
                break;
            }
        }
        updateLength();
    }

    public int getLength() {
        return length;
    }

    public void updateLength() {
        length = 0;
        for (PlaylistDTO playlist : playlists) {
            length += playlist.updatePlaylistDuration();
        }
    }

    public PlaylistDTO selectPlaylist(int id) {
        for (PlaylistDTO playlist : playlists) {
            if (playlist.getId() == id) {
                return playlist;
            }
        }
        return null;
    }
}
