package services;

import dao.DataAccessException;
import dao.Database;
import dao.EventDao;
import model.Event;
import request.EventIdRequest;
import result.EventIdResult;

import java.sql.Connection;

/**
 * request service class for eventID request, runs the functionality to actually perform the request
 */
public class EventIdService {

    private Database db;
    private EventDao eDao;
    private Event event;

    /**
     * eventID method to actually run the request sent int from the user to get an event
     * @param eventIdRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public EventIdResult eventIdService(EventIdRequest eventIdRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();
        eDao = new EventDao(conn);
        event = eDao.find(eventIdRequest.getEventID());

        EventIdResult eventResult = new EventIdResult();

        if (event != null) {
            eventResult.setEventID(event.getEventID());
            eventResult.setAssociatedUsername(event.getAssociatedUsername());
            eventResult.setPersonID(event.getPersonID());
            eventResult.setLatitude(event.getLatitude());
            eventResult.setLongitude(event.getLongitude());
            eventResult.setCountry(event.getCountry());
            eventResult.setCity(event.getCity());
            eventResult.setEventType(event.getEventType());
            eventResult.setYear(event.getYear());
            eventResult.setSuccess(true);
        }
        else {
            eventResult.setSuccess(false);
            eventResult.setMessage("Error: TODO: figure out what error to put here");
        }

        db.closeConnection(true); // we don't have anything to write back to the database

        return eventResult;
    }
}
