package nl.han.oose.dea.jordan.beroepsproduct.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistResponseDTO {

    private List<PlaylistDTO> playlists = new ArrayList<>();
    private int length;

    public PlaylistResponseDTO() {
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
