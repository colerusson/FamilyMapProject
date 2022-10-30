package services;

import dao.AuthTokenDao;
import dao.DataAccessException;
import dao.Database;
import dao.EventDao;
import model.AuthToken;
import model.Event;
import request.EventRequest;
import result.EventResult;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * request service class for event request, runs the functionality to actually perform the request
 */
public class EventService {

    private Database db;
    private EventDao eDao;
    private AuthTokenDao aDao;
    private Event[] events;
    /**
     * event method to actually run the request sent int from the user to get all events
     * @param eventRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public EventResult eventService(EventRequest eventRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();
        eDao = new EventDao(conn);
        AuthToken authToken = aDao.find(eventRequest.getAuthToken());
        EventResult eventResult = new EventResult();

        if (authToken != null) {
            String username = authToken.getUsername();
            events = eDao.getEventsForUser(username).toArray(new Event[0]);

            eventResult.setData(events);
            eventResult.setSuccess(true);
        }
        else {
            eventResult.setSuccess(false);
            eventResult.setMessage("Error: TODO: figure out error message");
        }

        db.closeConnection(true);

        return eventResult;
    }
}
