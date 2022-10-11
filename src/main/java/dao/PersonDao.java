package dao;

import model.Person;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object class for Person
 */
public class PersonDao {

    /**
     * Connection object created to generate a connection to the database
     */
    private final Connection conn;

    /**
     * Constructor that stores the connection object in this dao class
     * @param conn the connection member variable
     */
    public PersonDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * method to insert a person into the person table
     * @param person method object
     * @throws DataAccessException error in accessing the table
     */
    public void insert(Person person) throws DataAccessException {
    }

    /**
     * method to get a person by their ID
     * @param personID the string id of the person
     * @return the person method object for the respective person
     * @throws DataAccessException error in accessing the table
     */
    public Person getPersonByID(String personID) throws DataAccessException {
        return null;
    }

    /**
     * method to return a list of all persons for the user
     * @param username the string username of the user
     * @return a list object of Person method objects
     * @throws DataAccessException error in accessing the table
     */
    public List<Person> getPersonsForUser(String username) throws DataAccessException {
        return null;
    }

    /**
     * method to return a list of person method objects based on gender
     * @param gender string of the gender wanting to return
     * @return a list object of person method objects
     * @throws DataAccessException error in accessing the table
     */
    public List<Person> getPersonsByGender(String gender) throws DataAccessException {
        return null;
    }

    /**
     * method to return a list of persons based on their last name
     * @param lastName a string of the last name wanting to return
     * @return a list object of person method objects
     * @throws DataAccessException error in accessing the table
     */
    public List<Person> getPersonsByLastName(String lastName) throws DataAccessException {
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
