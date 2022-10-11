package nl.han.oose.dea.jordan.beroepsproduct.domain.dto;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean owner = true;
    private int ownerID;
    private TracklistDTO tracks = new TracklistDTO();

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

    public boolean getOwner() {
        return owner;
    }
    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public int getOwnerId() {
        return ownerID;
    }
    public void setOwnerId(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setTracks(TracklistDTO tracks) {
        this.tracks = tracks;
    }

    public TracklistDTO getTracks() {
        return tracks;
    }

    public int updatePlaylistDuration() {
        int duration = 0;
        if (tracks != null) {
            for (TrackDTO track : tracks.getTracks()) {
                duration += track.getDuration();
            }
        }
        return duration;
    }
}

