package nl.han.oose.dea.jordan.beroepsproduct.services;

import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistResponseDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TracklistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HardCodedPlaylistServiceTest {

    private HardCodedPlaylistService sut;
    private TracklistDTO tracklist;

    @BeforeEach
    public void setup() {
        sut = new HardCodedPlaylistService();
        tracklist = new TracklistDTO();
    }

    @Test
    public void testGetAllPlaylists() {
        // Arrange
        String token = "1234-1234-1234-1234";

        PlaylistResponseDTO expected = new PlaylistResponseDTO();
        PlaylistDTO playlistOne = new PlaylistDTO();
        tracklist.setTracks(new ArrayList<>());
        playlistOne.setTracks(tracklist);
        playlistOne.setId(1);
        playlistOne.setName("Heavy Metal");
        playlistOne.setOwner(true);
        expected.addPlaylist(playlistOne);

        PlaylistDTO playlistTwo = new PlaylistDTO();
        tracklist.setTracks(new ArrayList<>());
        playlistTwo.setTracks(tracklist);
        playlistTwo.setId(2);
        playlistTwo.setName("Rock");
        playlistTwo.setOwner(false);
        expected.addPlaylist(playlistTwo);

        PlaylistDTO playlistThree = new PlaylistDTO();
        tracklist.setTracks(new ArrayList<>());
        playlistThree.setTracks(tracklist);
        playlistThree.setId(3);
        playlistThree.setName("JSON Derulo");
        playlistThree.setOwner(true);
        expected.addPlaylist(playlistThree);

        // Act
        var actual = sut.getAllPlaylists();

        // Assert
        assertEquals(expected.getPlaylists().get(0).getId(), actual.getPlaylists().get(0).getId());
        assertEquals(expected.getPlaylists().get(0).getName(), actual.getPlaylists().get(0).getName());
        assertEquals(expected.getPlaylists().get(0).isOwner(), actual.getPlaylists().get(0).isOwner());
        assertEquals(expected.getPlaylists().get(1).getId(), actual.getPlaylists().get(1).getId());
        assertEquals(expected.getPlaylists().get(1).getName(), actual.getPlaylists().get(1).getName());
        assertEquals(expected.getPlaylists().get(1).isOwner(), actual.getPlaylists().get(1).isOwner());
        assertEquals(expected.getPlaylists().get(2).getId(), actual.getPlaylists().get(2).getId());
        assertEquals(expected.getPlaylists().get(2).getName(), actual.getPlaylists().get(2).getName());
        assertEquals(expected.getPlaylists().get(2).isOwner(), actual.getPlaylists().get(2).isOwner());
        assertEquals(expected.getPlaylists().size(), actual.getPlaylists().size());
    }
}