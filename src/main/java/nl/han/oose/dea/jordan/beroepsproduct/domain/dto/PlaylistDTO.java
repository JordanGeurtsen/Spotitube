package nl.han.oose.dea.jordan.beroepsproduct.domain.dto;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean owner = true;
    private int ownerID;
    private TracklistDTO tracks = new TracklistDTO();

    private int length;

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

    public int getOwnerID() {
        return ownerID;
    }
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setTracks(TracklistDTO tracks) {
        this.tracks = tracks;
    }

    public TracklistDTO getTracks() {
        return tracks;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

