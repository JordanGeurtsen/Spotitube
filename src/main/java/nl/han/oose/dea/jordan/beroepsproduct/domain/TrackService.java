package nl.han.oose.dea.jordan.beroepsproduct.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.datasource.dao.TrackDAO;
import nl.han.oose.dea.jordan.beroepsproduct.datasource.dao.TrackListDAO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;

@Default
@ApplicationScoped
public class TrackService {
    private TrackDAO trackDAO;
    private TrackListDAO tracklistDAO;

    public TracklistDTO getTracksFromPlaylist(int playlistId) {
        if(tracklistDAO.get(playlistId).isPresent()) {
            return tracklistDAO.get(playlistId).get();
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
    public void setTrackListDAO(TrackListDAO tracklistDAO) {
        this.tracklistDAO = tracklistDAO;
    }
}
