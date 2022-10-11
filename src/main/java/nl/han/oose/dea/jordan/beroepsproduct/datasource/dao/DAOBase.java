package nl.han.oose.dea.jordan.beroepsproduct.datasource.dao;

import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.datasource.utils.DatabaseProperties;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.DatabaseException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class DAOBase<T> {
    private DatabaseProperties databaseProperties;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseProperties.getDBConnectionString());
    }

    public List<T> getAll() {
        try {
            Connection connection = getConnection();
            List<T> result = resultSetMapper(executeResultStatement(getGetAllStatement(connection)));
            connection.close();
            return result;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Optional<T> get(int id) {
        try {
        Connection connection = getConnection();
        Optional<T> result = Optional.of(resultSetMapper(executeResultStatement(getGetStatement(connection, id))).get(0));
        connection.close();
        return result;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void insert(T t) {
        try {
        Connection connection = getConnection();
        executeUpdateStatement(getInsertStatement(connection, t));
        connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void update(T t) {
        try {
        Connection connection = getConnection();
        executeUpdateStatement(getUpdateStatement(connection, t));
        connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
        Connection connection = getConnection();
        executeUpdateStatement(getDeleteStatement(connection, id));
        connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public abstract PreparedStatement getGetAllStatement(Connection connection) throws SQLException;

    public abstract PreparedStatement getGetStatement(Connection connection, int id) throws SQLException;

    public abstract PreparedStatement getInsertStatement(Connection connection, T t) throws SQLException;

    public abstract PreparedStatement getUpdateStatement(Connection connection, T t) throws SQLException;

    public abstract PreparedStatement getDeleteStatement(Connection connection, int id) throws SQLException;

    public ResultSet executeResultStatement(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public void executeUpdateStatement(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }

    public abstract List<T> resultSetMapper(ResultSet resultSet) throws SQLException;

    @Inject
    private void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
