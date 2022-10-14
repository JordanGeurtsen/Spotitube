package nl.han.oose.dea.jordan.beroepsproduct.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.data.dao.TrackDAO;
import nl.han.oose.dea.jordan.beroepsproduct.data.dao.TracklistDAO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;

@Default
@ApplicationScoped
public class TrackService {
    private TrackDAO trackDAO;
    private TracklistDAO tracklistDAO;

    public TracklistDTO getTracksFromPlaylist(int playlistId) {
        if(tracklistDAO.get(playlistId).isPresent()) {
            TracklistDTO tracklistDTO = tracklistDAO.get(playlistId).get();
            int length = tracklistDTO.getTracks().stream().mapToInt(TrackDTO::getDuration).sum();
            tracklistDTO.setLength(length);
            return tracklistDTO;
        }
        return new TracklistDTO();
    }

    public TracklistDTO getTracksSuitableForPlaylist(int id) {
        TracklistDTO tracklistDTO = new TracklistDTO();
        tracklistDTO.setTracks(trackDAO.getAllTracksNotInPlaylist(id));
        return tracklistDTO;
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setTrackListDAO(TracklistDAO tracklistDAO) {
        this.tracklistDAO = tracklistDAO;
    }
}
