package nl.han.oose.dea.jordan.beroepsproduct.datasource.dao;

import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackListDAO extends DAOBase<TracklistDTO> {
    public void insertWithPlaylistTrackAndOfflineAvailable(int playlistID, int trackID, boolean offlineAvailable) {
        try {
            Connection connection = getConnection();
            executeUpdateStatement(getInsertWithPlaylistTrackAndOfflineAvailableStatement(connection, playlistID, trackID, offlineAvailable));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWithPlayListAndTrack(int playlist, int track) {
        try {
            Connection connection = getConnection();
            executeUpdateStatement(getDeleteWithPlaylistAndTrackStatement(connection, playlist, track));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getInsertWithPlaylistTrackAndOfflineAvailableStatement(Connection connection, int playlistID, int trackID, boolean offlineAvailable) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO spotitube.tracklist (playlist, track, offline_available) VALUES (?, ?, ?)");
        statement.setInt(1, playlistID);
        statement.setInt(2, trackID);
        statement.setBoolean(3, offlineAvailable);
        return statement;
    }

    public PreparedStatement getDeleteWithPlaylistAndTrackStatement(Connection connection, int playlist, int track) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM spotitube.tracklist WHERE playlist = ? AND track = ?");
        statement.setInt(1, playlist);
        statement.setInt(2, track);
        return statement;
    }

    @Override
    public PreparedStatement getGetAllStatement(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement getGetStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM spotitube.tracklist tl INNER JOIN spotitube.tracks t on t.id = tl.track WHERE playlist = ?");
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public PreparedStatement getInsertStatement(Connection connection, TracklistDTO tracklistDTO) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement getUpdateStatement(Connection connection, TracklistDTO tracklistDTO) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement getDeleteStatement(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public List<TracklistDTO> resultSetMapper(ResultSet resultSet) throws SQLException {
        List<TracklistDTO> result = new ArrayList<>();
        TracklistDTO tracklistDTO = new TracklistDTO();
        List<TrackDTO> trackDTOList = new ArrayList<>();
        while(resultSet.next()) {
            TrackDTO trackDTO = new TrackDTO();
            trackDTO.setId(resultSet.getInt("t.id"));
            trackDTO.setTitle(resultSet.getString("t.title"));
            trackDTO.setPerformer(resultSet.getString("t.performer"));
            trackDTO.setDuration(resultSet.getInt("t.duration"));
            trackDTO.setAlbum(resultSet.getString("t.album"));
            trackDTO.setPlaycount(resultSet.getInt("t.playcount"));
            trackDTO.setPublicationDate(resultSet.getString("t.publication_date"));
            trackDTO.setDescription(resultSet.getString("t.description"));
            trackDTO.setOfflineAvailable(resultSet.getBoolean("tl.offline_available"));
            trackDTOList.add(trackDTO);
        }
        tracklistDTO.setTracks(trackDTOList);
        result.add(tracklistDTO);
        resultSet.close();
        return result;
    }
}
