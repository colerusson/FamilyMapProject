package services;

import dao.DataAccessException;
import dao.Database;
import request.LoginRequest;
import result.LoginResult;

import java.sql.Connection;

/**
 * request service class for login request, runs the functionality to actually perform the request
 */
public class LoginService {

    private Database db;


    /**
     * login method to actually run the request sent int from the user to login
     * @param loginRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public LoginResult login(LoginRequest loginRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();







        db.closeConnection(true);

        return null;
    }
}
