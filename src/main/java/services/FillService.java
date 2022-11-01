package services;

import dao.*;
import model.Event;
import model.FamilyTree;
import model.Person;
import model.User;
import request.FillRequest;
import result.FillResult;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

/**
 * request service class for fill request, runs the functionality to actually perform the request
 */
public class FillService {
    private Database db;
    private PersonDao pDao;
    private EventDao eDao;
    private UserDao uDao;
    private Person person;
    private Person newPerson;
    private Event newEvent;
    private User user;
    int eventsAdded;
    int peopleAdded;
    private FamilyTree familyTree;
    int DOB;

    /**
     * fill method to actually run the request and fill the map with data
     * @param fillRequest a request object sent in from the handler
     * @return a register result object obtained from the result package classes
     */
    public FillResult fill(FillRequest fillRequest) throws DataAccessException {
        db = new Database();
        try {
            Connection conn = db.getConnection();
            pDao = new PersonDao(conn);
            uDao = new UserDao(conn);
            eDao = new EventDao(conn);

            FillResult fillResult = new FillResult();
            int generations = fillRequest.getGenerations();
            String username = fillRequest.getUsername();

            user = uDao.find(username);

            if (generations < 1 || user == null) {
                db.closeConnection(true);
                fillResult = new FillResult();
                fillResult.setSuccess(false);
                fillResult.setMessage("Error: invalid request");
                return fillResult;
            }

            //person = pDao.find(user.getPersonID());

            pDao.deletePersonsByUsername(username);
            eDao.deleteEventsByUsername(username);

            String filePathFemale = "json/fnames.json";
            File fileFemale = new File(filePathFemale);
            String filePathMale = "json/mnames.json";
            File fileMale = new File(filePathMale);
            String filePathSurnames = "json/snames.json";
            File fileSurnames = new File(filePathSurnames);
            String filePathLocations = "json/locations.json";
            File fileLocations = new File(filePathLocations);

            familyTree = new FamilyTree();
            familyTree.generateFemaleNames(fileFemale);
            familyTree.generateMaleNames(fileMale);
            familyTree.generateLastNames(fileSurnames);
            familyTree.generateLocations(fileLocations);

            // regenerate person data and event data for the user himself
//            newPerson = person;
//            pDao.insert(newPerson);

            DOB = 2000;
//            newEvent = familyTree.generateEvent(DOB, username, newPerson.getPersonID(), "birth");
//            eDao.insert(newEvent);

            generatePerson(user, generations, DOB);

            db.closeConnection(true);

            fillResult = new FillResult();
            fillResult.setSuccess(true);
            if (eventsAdded > 0 && peopleAdded > 0) {
                fillResult.setMessage("Successfully added " + peopleAdded + " persons and " + eventsAdded + " events to the database.");
                fillResult.setSuccess(true);
            }
            else {
                fillResult.setSuccess(false);
                fillResult.setMessage("Error: Invalid data");
            }

            return fillResult;

        } catch (Exception ex) {
            ex.printStackTrace();
            db.closeConnection(false);

            FillResult fillResult = new FillResult();
            fillResult.setSuccess(false);
            fillResult.setMessage("Error: error message");

            return fillResult;
        }
    }

    private void generatePerson(User user, int generations, int DOB) throws  DataAccessException {
        Person mother;
        Person father;

        mother = generatePersonHelper(user.getUsername(), "f", generations, DOB-25);
        father = generatePersonHelper(user.getUsername(), "m", generations, DOB-25);

        mother.setSpouseID(father.getPersonID());
        father.setSpouseID(mother.getPersonID());

        Event marriage = familyTree.generateEvent(DOB-25, user.getUsername(), mother.getPersonID(), "marraige");
        eDao.insert(marriage);
        ++eventsAdded;
        marriage.setPersonID(father.getPersonID());
        marriage.setEventID(UUID.randomUUID().toString());
        eDao.insert(marriage);
        eventsAdded++;

        Person person = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(),user.getLastName(), user.getGender(), father.getPersonID(), mother.getPersonID(), null);
        Event birth = familyTree.generateEvent(DOB, user.getUsername(), person.getPersonID(), "birth");
        eDao.insert(birth);
        eventsAdded++;
        Event death = familyTree.generateEvent(DOB, user.getUsername(), person.getPersonID(), "death");
        eDao.insert(death);
        eventsAdded++;

        pDao.insert(person);
        pDao.insert(mother);
        pDao.insert(father);
        peopleAdded+=3;
    }

    private Person generatePersonHelper(String username, String gender, int generations, int DOB) throws DataAccessException {
        Person mother = new Person();
        Person father = new Person();
        Person person = null;

        if (generations > 1) {
            mother = generatePersonHelper(username, "f", generations - 1, DOB-25);
            father = generatePersonHelper(username, "m", generations - 1, DOB-25);
            mother.setSpouseID(father.getSpouseID());
            father.setSpouseID(mother.getPersonID());

            Event marriage = familyTree.generateEvent(DOB-25, username, father.getPersonID(), "marriage");
            eDao.insert(marriage);
            marriage.setPersonID(mother.getPersonID());
            marriage.setEventID(UUID.randomUUID().toString());
            eDao.insert(marriage);
            pDao.insert(mother);
            pDao.insert(father);
            eventsAdded+=2;
            peopleAdded+=2;
        }

        if (gender.equals("f")) {
            String firstName = familyTree.getName("f");
            String lastName = familyTree.getName("l");

            person = new Person(UUID.randomUUID().toString(), username, firstName, lastName, "f", father.getPersonID(), mother.getPersonID(), null);
            Event birth = familyTree.generateEvent(DOB, username, person.getPersonID(), "birth");
            eDao.insert(birth);
            Event death = familyTree.generateEvent(DOB, username, person.getPersonID(), "death");
            eDao.insert(death);
            eventsAdded+=2;
        }
        else if (gender.equals("m")) {
            String firstName = familyTree.getName("m");
            String lastName = familyTree.getName("l");

            person = new Person(UUID.randomUUID().toString(), username, firstName, lastName, "m", father.getPersonID(), mother.getPersonID(), null);
            Event birth = familyTree.generateEvent(DOB, username, person.getPersonID(), "birth");
            eDao.insert(birth);
            Event death = familyTree.generateEvent(DOB, username, person.getPersonID(), "death");
            eDao.insert(death);
            eventsAdded+=2;
        }

        return person;
    }
}
