package services;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDao;
import request.FillRequest;
import result.FillResult;

import java.sql.Connection;

/**
 * request service class for fill request, runs the functionality to actually perform the request
 */
public class FillService {
    private Database db;
    /**
     * fill method to actually run the request and fill the map with data
     * @param fillRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public FillResult fill(FillRequest fillRequest) throws DataAccessException {
        db = new Database();
        try {
            Connection conn = db.getConnection();

            int generations = fillRequest.getGenerations();
            String username = fillRequest.getUsername();

            // generate the person and event info for the user
            // then generate the person and event info for each successive parent for the number of generations
            // user must already be present in database
            // if there is any data already present with this user, delete the data
            // TODO: change this to generate random values for each based on xml sheet,
            //  calender function in java, random UMI strings in java





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
