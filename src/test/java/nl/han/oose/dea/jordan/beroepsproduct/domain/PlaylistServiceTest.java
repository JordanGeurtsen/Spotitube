package nl.han.oose.dea.jordan.beroepsproduct.domain;

import nl.han.oose.dea.jordan.beroepsproduct.data.dao.PlaylistDAO;
import nl.han.oose.dea.jordan.beroepsproduct.data.dao.TracklistDAO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlaylistServiceTest {

    private PlaylistService sut;
    private PlaylistResponseDTO playlistResponseDTO;
    private PlaylistDAO mockedPlaylistDAO;
    private TracklistDAO mockedTracklistDAO;
    private LoginService mockedLoginService;
    private TrackService mockedTrackService;
    private List<PlaylistDTO> playlistDTOList;
    private TracklistDTO tracklistDTO;

    @BeforeEach
    void setUp() {
        sut = new PlaylistService();
        playlistResponseDTO = new PlaylistResponseDTO();
        mockedPlaylistDAO = mock(PlaylistDAO.class);
        mockedTracklistDAO = mock(TracklistDAO.class);
        mockedLoginService = mock(LoginService.class);
        mockedTrackService = mock(TrackService.class);
        sut.setPlaylistDAO(mockedPlaylistDAO);
        sut.setTracklistDAO(mockedTracklistDAO);
        sut.setLoginService(mockedLoginService);
        sut.setTrackService(mockedTrackService);

        playlistDTOList = new ArrayList<>();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);
        playlistDTO.setName("playlist");
        playlistDTO.setOwner(true);
        playlistDTO.setOwnerID(1);

        tracklistDTO = new TracklistDTO();
        List<TrackDTO> tracks = new ArrayList<>();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);
        trackDTO.setDuration(1);
        tracks.add(trackDTO);
        tracklistDTO.setTracks(tracks);
        playlistDTO.setTracks(tracklistDTO);
        playlistDTOList.add(playlistDTO);
        playlistResponseDTO.setPlaylists(playlistDTOList);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        when(mockedPlaylistDAO.getAll()).thenReturn(playlistDTOList);
        when(mockedTrackService.getTracksFromPlaylist(1)).thenReturn(tracklistDTO);
        when(mockedLoginService.getCurrentUser()).thenReturn(userDTO);
        when(mockedTrackService.getTracksFromPlaylist(playlistDTO.getId())).thenReturn(tracklistDTO);
    }

    @Test
    void getPlaylistsReturnsPlaylistResponseDTO() {
        // Arrange

        // Act
        sut.getAllPlaylists();

        // Assert
        assertEquals(playlistDTOList.get(0).getId(), playlistResponseDTO.getPlaylists().get(0).getId());
        assertEquals(playlistDTOList.get(0).getName(), playlistResponseDTO.getPlaylists().get(0).getName());
        assertEquals(playlistDTOList.get(0).getOwner(), playlistResponseDTO.getPlaylists().get(0).getOwner());
        assertEquals(playlistDTOList.get(0).getTracks().getTracks().get(0).getId(), playlistResponseDTO.getPlaylists().get(0).getTracks().getTracks().get(0).getId());
        assertEquals(playlistDTOList.get(0).getTracks().getTracks().get(0).getDuration(), playlistResponseDTO.getPlaylists().get(0).getTracks().getTracks().get(0).getDuration());
    }
}