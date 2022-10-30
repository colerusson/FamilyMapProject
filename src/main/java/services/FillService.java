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
    private PersonDao pDao;

    /**
     * fill method to actually run the request and fill the map with data
     * @param fillRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public FillResult fill(FillRequest fillRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();
        pDao = new PersonDao(conn);

        int generations = fillRequest.getGenerations();
        String username = fillRequest.getUsername();

        // generate the person and event info for the user
        // then generate the person and event info for each successive parent for the number of generations



        db.closeConnection(true);

        return null;
    }
}
