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

            int generations = fillRequest.getGenerations();
            String username = fillRequest.getUsername();

            user = uDao.find(username);
            person = pDao.find(user.getPersonID());

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

            FamilyTree familyTree = new FamilyTree();
            familyTree.generateFemaleNames(fileFemale);
            familyTree.generateMaleNames(fileMale);
            familyTree.generateLastNames(fileSurnames);
            familyTree.generateLocations(fileLocations);

            // regenerate person data and event data for the user himself
            newPerson = person;
            pDao.insert(newPerson);
            int birthYear = familyTree.generateYear();
            newEvent = familyTree.generateEvent("Birth", birthYear);
            eDao.insert(newEvent);


            // 2^i to get how many people for that generation



            //int rnd = new Random().nextInt(nameArray.size());



            db.closeConnection(true);

            FillResult fillResult = new FillResult();
            fillResult.setSuccess(true);
            // add other things to message
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
}
