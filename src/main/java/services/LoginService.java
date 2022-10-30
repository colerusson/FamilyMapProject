package services;

import dao.*;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import result.LoginResult;

import java.sql.Connection;

/**
 * request service class for login request, runs the functionality to actually perform the request
 */
public class LoginService {

    private Database db;
    private AuthTokenDao aDao;
    private UserDao uDao;
    private User user;
    private AuthToken authToken;


    /**
     * login method to actually run the request sent int from the user to login
     * @param loginRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public LoginResult login(LoginRequest loginRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();

        aDao = new AuthTokenDao(conn);
        uDao = new UserDao(conn);

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        LoginResult loginResult = new LoginResult();

        if (uDao.validate(username, password)) {
           user = uDao.find(username);
           String personID = user.getPersonID();
           String authTokenString = "generate-random-token";    // TODO: change this to generate random authtokens
           authToken = new AuthToken(authTokenString, username);
           if (aDao.validate(authTokenString)) {
               aDao.deleteRow(authTokenString);
           }
           aDao.insert(authToken);

           loginResult.setAuthtoken(authTokenString);
           loginResult.setUsername(username);
           loginResult.setPersonID(personID);
           loginResult.setSuccess(true);
        }
        else {
            loginResult.setSuccess(false);
            loginResult.setMessage("Error, not valid login credentials");
        }

        db.closeConnection(true);

        return loginResult;
    }
}
