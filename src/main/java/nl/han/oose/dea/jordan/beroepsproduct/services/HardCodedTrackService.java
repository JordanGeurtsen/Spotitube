package nl.han.oose.dea.jordan.beroepsproduct.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TracklistDTO;

import java.util.ArrayList;
import java.util.Objects;

@Default
@ApplicationScoped
public class HardCodedTrackService implements TrackService {

    private TrackDTO trackOne;
    private TrackDTO trackTwo;
    private TrackDTO trackThree;
    private TrackDTO trackFour;
    private TrackDTO trackFive;
    private TrackDTO trackSix;
    private PlaylistService playlistService;
    private LoginService loginService;
    private TracklistDTO tracks;


    public HardCodedTrackService() {
        hardCodedTrackSetup();
    }

    public TrackDTO getTrackOne() {return trackOne;}
    public TrackDTO getTrackTwo() {return trackTwo;}
    public TrackDTO getTrackThree() {return trackThree;}
    public TrackDTO getTrackFour() {return trackFour;}
    public TrackDTO getTrackFive() {return trackFive;}
    public TrackDTO getTrackSix() {return trackSix;}

    @Override
    public TracklistDTO getTracksSuitableForPlaylist(int id, String token){
        loginService.authorize(token);

        TracklistDTO tracksInPlayList = playlistService.getTracksFromPlaylist(id);

        TracklistDTO tracksForPlaylist = new TracklistDTO();
        tracksForPlaylist.setTracks(new ArrayList<>());

        for (TrackDTO track : tracks.getTracks()) {
            for (TrackDTO trackInPlaylist : tracksInPlayList.getTracks()) {
                if (track.getId() != trackInPlaylist.getId()) {
                    tracksForPlaylist.getTracks().add(track);
                }
            }
        }
        return tracksForPlaylist;
    }

    private void hardCodedTrackSetup() {
        trackOne = new TrackDTO();
        trackOne.setId(5);
        trackOne.setTitle("The Trooper");
        trackOne.setPerformer("Iron Maiden");
        trackOne.setDuration(300);
        trackOne.setAlbum("The Number of the Beast");
        trackOne.setPlaycount(100);
        trackOne.setPublicationDate("22-02-1982");
        trackOne.setDescription("The Trooper is a song by the British heavy metal band Iron Maiden. It was released as the second single from their third studio album, The Number of the Beast, in 1982. The song was written by bassist Steve Harris and is about the Battle of Balaclava during the Crimean War. It is one of the band's most popular songs and has been played live on every tour since its release.");
        trackOne.setOfflineAvailable(true);

        trackTwo = new TrackDTO();
        trackTwo.setId(6);
        trackTwo.setTitle("The Number of the Beast");
        trackTwo.setPerformer("Iron Maiden");
        trackTwo.setDuration(300);
        trackTwo.setAlbum("The Number of the Beast");
        trackTwo.setPlaycount(100);
        trackTwo.setPublicationDate("02-14-1982");
        trackTwo.setDescription("The Number of the Beast is the album number");
        trackTwo.setOfflineAvailable(true);

        trackThree = new TrackDTO();
        trackThree.setId(7);
        trackThree.setTitle("Hallowed Be Thy Name");
        trackThree.setPerformer("Iron Maiden");
        trackThree.setDuration(300);
        trackThree.setAlbum("The Number of the Beast");
        trackThree.setPlaycount(100);
        trackThree.setPublicationDate("02-14-1982");
        trackThree.setDescription("Hallowed Be Thy Name is a song by the British heavy metal band Iron Maiden. It was released as the third single from their third studio album, The Number of the Beast, in 1982. The song was written by bassist Steve Harris and is about the Battle of Balaclava during the Crimean War. It is one of the band's most popular songs and has been played live on every tour since its release.");
        trackThree.setOfflineAvailable(true);

        trackFour = new TrackDTO();
        trackFour.setId(8);
        trackFour.setTitle("Talk Dirty");
        trackFour.setPerformer("Jason Derulo");
        trackFour.setDuration(300);
        trackFour.setAlbum("Talk Dirty");
        trackFour.setPlaycount(100);
        trackFour.setPublicationDate("02-14-1982");
        trackFour.setDescription("Talk Dirty is a song by American singer Jason Derulo. It was released on July 22, 2014, as the second single from his fourth studio album, Talk Dirty (2014). The song was written by Derulo, Lukasz Gottwald, Max Martin, and Cirkut, with production handled by the latter two. It features guest vocals from American rapper 2 Chainz. The song was released to contemporary hit radio in the United States on August 12, 2014, as the album's third single.");
        trackFour.setOfflineAvailable(true);

        trackFive = new TrackDTO();
        trackFive.setId(9);
        trackFive.setTitle("Wiggle");
        trackFive.setPerformer("Jason Derulo");
        trackFive.setDuration(300);
        trackFive.setAlbum("Talk Dirty");
        trackFive.setPlaycount(100);
        trackFive.setPublicationDate("02-14-1982");
        trackFive.setDescription("Wiggle is a song by American singer Jason Derulo. It was released on July 22, 2014, as the second single from his fourth studio album, Talk Dirty (2014). The song was written by Derulo, Lukasz Gottwald, Max Martin, and Cirkut, with production handled by the latter two. It features guest vocals from American rapper 2 Chainz. The song was released to contemporary hit radio in the United States on August 12, 2014, as the album's third single.");
        trackFive.setOfflineAvailable(true);

        trackSix = new TrackDTO();
        trackSix.setId(10);
        trackSix.setTitle("Trumpets");
        trackSix.setPerformer("Jason Derulo");
        trackSix.setDuration(300);
        trackSix.setAlbum("Talk Dirty");
        trackSix.setPlaycount(100);
        trackSix.setPublicationDate("02-14-1982");
        trackSix.setDescription("Trumpets is a song by American singer Jason Derulo. It was released on July 22, 2014, as the second single from his fourth studio album, Talk Dirty (2014). The song was written by Derulo, Lukasz Gottwald, Max Martin, and Cirkut, with production handled by the latter two. It features guest vocals from American rapper 2 Chainz. The song was released to contemporary hit radio in the United States on August 12, 2014, as the album's third single.");
        trackSix.setOfflineAvailable(true);

        tracks = new TracklistDTO();
        tracks.setTracks(new ArrayList<>());
        tracks.addTrack(trackOne);
        tracks.addTrack(trackTwo);
        tracks.addTrack(trackThree);
        tracks.addTrack(trackFour);
        tracks.addTrack(trackFive);
        tracks.addTrack(trackSix);
    }

    @Inject
    public void setPlaylistService (PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Inject
    public void setLoginService (LoginService loginService){
        this.loginService = loginService;
    }
}
