package dao;

import model.Event;

import java.sql.*;
import java.util.List;

/**
 * Data access object class for Event
 */
public class EventDao {

    /**
     * Connection object created to generate a connection to the database
     */
    private final Connection conn;

    /**
     * Constructor that stores the connection object in this dao class
     * @param conn the connection member variable
     */
    public EventDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * method to add an event to the event table
     * @param event the event being added
     * @throws DataAccessException error in accessing the table
     */
    public void insert(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * method to get an event by its ID
     * @param eventID the ID for the event being accessed
     * @throws DataAccessException error in accessing the table
     * @return an event method object for the even found
     */
    public Event getEventByID(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }

    /**
     * method to clear the events table
     * @throws DataAccessException error in accessing the table
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Events";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    /**
     * method to return a list of the events
     * @param username string username of the user
     * @return a List object of Event method objects from the table
     * @throws DataAccessException error in accessing the table
     */
    public List<Event> getEventsForUser(String username) throws DataAccessException {
        return null;
    }

    /**
     * method to get a list of events by their type
     * @param eventType the type of events to return
     * @return a list object of Event method objects from the table
     * @throws DataAccessException error in accessing the table
     */
    public List<Event> getEventsByType(String eventType) throws DataAccessException {
        return null;
    }

    /**
     * method to return a list of events based on their year
     * @param year the for the events to return
     * @return a list object of Event method objects from the table
     * @throws DataAccessException error in accessing the table
     */
    public List<Event> getEventsByYear(Integer year) throws DataAccessException {
        return null;
    }

    /**
     * method to get a list of events based on their country
     * @param country the country for the events to return
     * @return a list object of Event method objects from the table
     * @throws DataAccessException error in accessing the table
     */
    public List<Event> getEventsByCountry(String country) throws DataAccessException {
        return null;
    }

    /**
     * method to delete a row from the table
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
