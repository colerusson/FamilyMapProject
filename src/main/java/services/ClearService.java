package services;

import dao.*;
import model.User;
import result.ClearResult;

import java.sql.Connection;

/**
 * request service class for clearing request, runs the functionality to actually perform the request
 */
public class ClearService {

    private Database db;
    private EventDao eDao;
    private PersonDao pDao;
    private AuthTokenDao aDao;
    private UserDao uDao;
    /**
     * clear method to actually run the request sent int from the user
     * @return a register result object obtained from the result package classes
     */
    public ClearResult clear() throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();
        eDao = new EventDao(conn);
        pDao = new PersonDao(conn);
        aDao = new AuthTokenDao(conn);
        uDao = new UserDao(conn);

        eDao.clear();
        pDao.clear();
        aDao.clear();
        uDao.clear();

        ClearResult clearResult = new ClearResult();
        clearResult.setMessage("Clear succeeded.");
        clearResult.setSuccess(true);

        // figure out when to set to false

        db.closeConnection(true);

        return clearResult;
    }
}
