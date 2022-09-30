package nl.han.oose.dea.jordan.beroepsproduct.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistResponseDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TracklistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.InvalidTokenException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Default
public class HardCodedPlaylistService implements PlaylistService {

    private PlaylistResponseDTO playlistResponseDTO = new PlaylistResponseDTO();
    private TrackService trackService;

    public HardCodedPlaylistService() {
        setHardCodedPlaylistResponse();
    }

    @Override
    public PlaylistResponseDTO getAllPlaylists() {
        return playlistResponseDTO;
    }

    @Override
    public TracklistDTO getTracksFromPlaylist(int id) {
        return playlistResponseDTO.selectPlaylist(id).getTracks();
    }
    @Override
    public PlaylistResponseDTO addPlaylist(PlaylistDTO playlist) {
        List<PlaylistDTO> playlists = getAllPlaylists().getPlaylists();
        playlistResponseDTO = new PlaylistResponseDTO();
        int id = playlists.get(playlists.size() - 1).getId() + 1;
        playlist.setId(id);
        playlist.setOwner(true);
        TracklistDTO trackList = new TracklistDTO();
        trackList.setTracks(new ArrayList<>());
        playlist.setTracks(trackList);
        playlists.add(playlist);

        playlistResponseDTO.setPlaylists(playlists);
        return playlistResponseDTO;
    }

    @Override
    public PlaylistResponseDTO updatePlaylist(int id, PlaylistDTO playlistDTO) {
        playlistDTO.setTracks(new TracklistDTO());
        PlaylistResponseDTO playlistResponseDTO = getAllPlaylists();
        playlistResponseDTO.replacePlaylist(id, playlistDTO);
        return playlistResponseDTO;
    }

    @Override
    public PlaylistResponseDTO deletePlaylist(int id) {
        playlistResponseDTO.removePlaylist(id);
        return playlistResponseDTO;
    }

    @Override
    public PlaylistResponseDTO addTrackToPlaylist(int playlistId, TrackDTO track) {
        playlistResponseDTO.selectPlaylist(playlistId).getTracks().addTrack(track);
        return playlistResponseDTO;
    }

    @Override
    public TracklistDTO removeTrackFromPlaylist(int playlistId, int trackID) {
        playlistResponseDTO.selectPlaylist(playlistId).getTracks().removeTrack(trackID);
        return playlistResponseDTO.selectPlaylist(playlistId).getTracks();
    }

    private void setHardCodedPlaylistResponse() {
        //Change to db call later
        HardCodedTrackService hcts = new HardCodedTrackService();

        TracklistDTO tracks;
        TrackDTO trackOne = hcts.getTrackOne();
        TrackDTO trackTwo = hcts.getTrackTwo();
        TrackDTO trackThree = hcts.getTrackThree();
        TrackDTO trackFour = hcts.getTrackFour();
        TrackDTO trackFive = hcts.getTrackFive();
        TrackDTO trackSix  = hcts.getTrackSix();

        PlaylistDTO playlistOne = new PlaylistDTO();
        playlistOne.setId(1);
        playlistOne.setName("Heavy Metal");
        playlistOne.setOwner(true);
        tracks = new TracklistDTO();
        tracks.setTracks(new ArrayList<>());
        tracks.addTrack(trackOne);
        tracks.addTrack(trackTwo);
        tracks.addTrack(trackThree);
        playlistOne.setTracks(tracks);
        playlistResponseDTO.addPlaylist(playlistOne);

        PlaylistDTO playlistTwo = new PlaylistDTO();
        playlistTwo.setId(2);
        playlistTwo.setName("Rock");
        playlistTwo.setOwner(false);
        tracks = new TracklistDTO();
        tracks.setTracks(new ArrayList<>());
        playlistTwo.setTracks(tracks);
        playlistResponseDTO.addPlaylist(playlistTwo);

        PlaylistDTO playlistThree = new PlaylistDTO();
        playlistThree.setId(3);
        playlistThree.setName("JSON Derulo");
        playlistThree.setOwner(true);
        tracks = new TracklistDTO();
        tracks.setTracks(new ArrayList<>());
        tracks.addTrack(trackFour);
        tracks.addTrack(trackFive);
        tracks.addTrack(trackSix);
        playlistThree.setTracks(tracks);
        playlistResponseDTO.addPlaylist(playlistThree);
    }

    @Inject
    public void setTrackService(TrackService trackService){
        this.trackService = trackService;
    }
}
