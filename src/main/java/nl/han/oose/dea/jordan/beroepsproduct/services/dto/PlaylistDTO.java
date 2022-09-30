package nl.han.oose.dea.jordan.beroepsproduct.services.dto;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean owner;
    private TracklistDTO tracks;

    public PlaylistDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public TracklistDTO getTracks() {
        return tracks;
    }

    public void setTracks(TracklistDTO tracks) {
        this.tracks = tracks;
    }

    public void addTrack(TrackDTO track) {
        tracks.addTrack(track);
    }

    public void removeTrack(int trackID) {
        tracks.removeTrack(trackID);
    }

    public int updatePlaylistDuration() {
        int duration = 0;
        if (tracks.getTracks() != null) {
            for (TrackDTO track : tracks.getTracks()) {
                duration += track.getDuration();
            }
        }
        return duration;
    }
}

