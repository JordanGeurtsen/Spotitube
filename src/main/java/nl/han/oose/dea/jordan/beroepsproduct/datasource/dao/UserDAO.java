package nl.han.oose.dea.jordan.beroepsproduct.datasource.dao;

import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.UserDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends DAOBase<UserDTO> {
    public Optional<UserDTO> getUserWithToken(String token) {
        try {
        Connection connection = getConnection();
        List<UserDTO> result = resultSetMapper(executeResultStatement(getGetUserWithTokenStatement(connection, token)));
        connection.close();
        return Optional.of(result.get(0));
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserDTO getUserWithLoginRequest(LoginRequestDTO loginRequestDTO) {
        try {
        Connection connection = getConnection();
        UserDTO result = resultSetMapper(executeResultStatement(getGetUserWithLoginRequestStatement(connection, loginRequestDTO))).get(0);
        connection.close();
        return result;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public PreparedStatement getGetUserWithTokenStatement(Connection connection, String token) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM spotitube.users WHERE token = ?");
        statement.setString(1, token);
        return statement;
    }
    public PreparedStatement getGetUserWithLoginRequestStatement(Connection connection, LoginRequestDTO loginRequestDTO) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM spotitube.users WHERE username = ? AND Password = ?");
        statement.setString(1, loginRequestDTO.getUser());
        statement.setString(2, loginRequestDTO.getPassword());
        return statement;
    }

    @Override
    public PreparedStatement getGetAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM spotitube.users");
    }

    @Override
    public PreparedStatement getGetStatement(Connection connection, int id) throws SQLException {
        return connection.prepareStatement("SELECT * FROM spotitube.users WHERE id = " + id);
    }

    @Override
    public PreparedStatement getInsertStatement(Connection connection, UserDTO userDTO) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement getUpdateStatement(Connection connection, UserDTO userDTO) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE spotitube.users SET name = ?, token = ? WHERE id = ?");
        statement.setString(1, userDTO.getUser());
        statement.setString(2, userDTO.getToken());
        statement.setInt(3, userDTO.getId());
        return statement;
    }

    @Override
    public PreparedStatement getDeleteStatement(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> resultSetMapper(ResultSet resultSet) throws SQLException {
        List<UserDTO> userDTOList = new ArrayList<>();
        while (resultSet.next()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(resultSet.getInt("id"));
            userDTO.setUser(resultSet.getString("name"));
            userDTO.setToken(resultSet.getString("token"));
            userDTOList.add(userDTO);
        }
        resultSet.close();
        return userDTOList;
    }
}