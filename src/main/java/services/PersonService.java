package services;

import dao.*;
import model.AuthToken;
import model.Event;
import model.Person;
import request.PersonIdRequest;
import request.PersonRequest;
import result.EventResult;
import result.PersonIdResult;
import result.PersonResult;

import java.sql.Connection;

/**
 * request service class for person request, runs the functionality to actually perform the request
 */
public class PersonService {

    private Database db;
    private PersonDao pDao;
    private AuthTokenDao aDao;
    private Person[] persons;
    /**
     * person method to actually run the request sent int from the user to return all persons
     * @param personRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public PersonResult personService (PersonRequest personRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();
        AuthToken authToken = aDao.find(personRequest.getAuthToken());
        PersonResult personResult = new PersonResult();

        if (authToken != null) {
            String username = authToken.getUsername();
            persons = pDao.getPersonsForUser(username).toArray(new Person[0]);

            personResult.setData(persons);
            personResult.setSuccess(true);
        }
        else {
            personResult.setSuccess(false);
            personResult.setMessage("Error: TODO: figure out error message");
        }

        db.closeConnection(true);

        return personResult;
    }
}
