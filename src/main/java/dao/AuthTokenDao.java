package dao;

import model.AuthToken;

import java.sql.Connection;

/**
 * Data access object class for AuthToken
 */
public class AuthTokenDao {

    /**
     * Connection object created to generate a connection to the database
     */
    private final Connection conn;

    /**
     * Constructor that stores the connection object in this dao class
     * @param conn the connection member variable
     */
    public AuthTokenDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * method to insert an authtoken into the table
     * @param authToken an authtoken method object
     * @throws DataAccessException error in accessing the table
     */
    public void insertToken(AuthToken authToken) throws DataAccessException {
    }

    /**
     * method to validate the user making the request
     * @param authToken a string that is the authToken of the user
     * @return a boolean confirming the validation status
     * @throws DataAccessException error in accessing the table
     */
    public boolean validate(String authToken) throws DataAccessException {
        return false;
    }

    /**
     * method to get the authtoken with the given user
     * @param username the string username of the user
     * @return the authToken data object
     * @throws DataAccessException error in accessing the table
     */
    public AuthToken getTokenByUsername(String username) throws DataAccessException {
        return null;
    }

    /**
     * method to delete a row in the table
     * @throws DataAccessException error in accessing the table
     */
    public void deleteRow() throws DataAccessException {
    }

    /**
     * method to delete the table
     * @throws DataAccessException error in accessing the table
     */
    public void deleteTable() throws DataAccessException {
    }
}
