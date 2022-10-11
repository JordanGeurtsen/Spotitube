package nl.han.oose.dea.jordan.beroepsproduct.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class TracklistDTO {

    private List<TrackDTO> tracks = new ArrayList<>();

    public TracklistDTO() {
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
