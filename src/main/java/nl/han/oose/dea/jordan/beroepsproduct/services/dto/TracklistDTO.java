package nl.han.oose.dea.jordan.beroepsproduct.services.dto;

import java.util.List;

public class TracklistDTO {

    private List<TrackDTO> tracks;

    public TracklistDTO() {
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
    public void addTrack(TrackDTO track) {
        tracks.add(track);
    }

    public void removeTrack(int trackID) {
        for (TrackDTO track : tracks) {
            if (track.getId() == trackID) {
                tracks.remove(track);
                break;
            }
        }
    }
}
