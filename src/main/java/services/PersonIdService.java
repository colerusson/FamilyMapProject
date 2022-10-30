package services;

import dao.DataAccessException;
import dao.Database;
import dao.EventDao;
import dao.PersonDao;
import model.Event;
import model.Person;
import request.PersonIdRequest;
import request.RegisterRequest;
import result.PersonIdResult;
import result.RegisterResult;

import java.sql.Connection;

/**
 * request service class for personID request, runs the functionality to actually perform the request
 */
public class PersonIdService {

    private Database db;
    private PersonDao pDao;
    private Person person;

    /**
     * personID method to actually run the request sent int from the user to get a certain person
     * @param personIdRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public PersonIdResult personIdService (PersonIdRequest personIdRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);
        person = pDao.find(personIdRequest.getPersonID());

        PersonIdResult personResult = new PersonIdResult();

        if (person != null) {
            personResult.setAssociatedUsername(person.getAssociatedUsername());
            personResult.setPersonID(person.getPersonID());
            personResult.setGender(person.getGender());
            personResult.setFirstName(person.getFirstName());
            personResult.setLastName(person.getLastName());
            personResult.setFatherID(person.getFatherID());
            personResult.setMessage(person.getMotherID());
            personResult.setSpouseID(person.getSpouseID());
            personResult.setSuccess(true);
        }
        else {
            personResult.setSuccess(false);
            personResult.setMessage("Error: TODO: figure out what error message to put");
        }

        db.closeConnection(true);

        return personResult;
    }
}
