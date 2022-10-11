package dao;

import model.User;

import java.sql.Connection;

/**
 * Data access object class for User
 */
public class UserDao {

    /**
     * Connection object created to generate a connection to the database
     */
    private final Connection conn;

    /**
     * Constructor that stores the connection object in this dao class
     * @param conn the connection member variable
     */
    public UserDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * method to insert a user into the user data table
     * @param user user method object
     * @throws DataAccessException error in accessing the table
     */
    public void insertUser(User user) throws DataAccessException {
    }

    /**
     * method to validate a user based on their username and password at login
     * @param username the username of the user logging in or making request
     * @param password the password of the user logging in or making request
     * @return boolean confirming the success status of user credentials
     * @throws DataAccessException error in accessing the table
     */
    public boolean validate(String username, String password) throws DataAccessException {
        return false;
    }

    /**
     * method to return the user based on userId
     * @param userId the string ID of the desired user
     * @return a user method object
     * @throws DataAccessException error in accessing the table
     */
    public User getUserById(String userId) throws DataAccessException {
        return null;
    }

    /**
     * method to return a user based on first and last name of user
     * @param firstName the string of first name of the desired user
     * @param lastName the string of last name of the desired user
     * @return a user method object
     * @throws DataAccessException error in accessing the table
     */
    public User getUserByName(String firstName, String lastName) throws DataAccessException {
        return null;
    }

    /**
     * method to return a user based on the personID of the user
     * @param personID the personID string of the user
     * @return a user method object
     * @throws DataAccessException error in accessing the table
     */
    public User getUserByPersonID(String personID) throws DataAccessException {
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
