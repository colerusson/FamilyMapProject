package services;

import dao.AuthTokenDao;
import dao.DataAccessException;
import dao.Database;
import dao.UserDao;
import model.AuthToken;
import model.User;
import request.FillRequest;
import request.RegisterRequest;
import result.FillResult;
import result.RegisterResult;

import java.sql.Connection;
import java.util.UUID;

/**
 * request service class for register request, runs the functionality to actually perform the request
 */
public class RegisterService {
    private Database db;
    private UserDao uDao;
    private User user;
    private AuthTokenDao aDao;
    private AuthToken authToken;
    /**
     * register method to actually run the request sent int from the user to regsiter as a user
     * @param registerRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public RegisterResult register(RegisterRequest registerRequest) throws DataAccessException {
        db = new Database();
        try {
            Connection conn = db.getConnection();
            uDao = new UserDao(conn);

            String username = registerRequest.getUsername();
            String password = registerRequest.getPassword();
            String email = registerRequest.getEmail();
            String firstName = registerRequest.getFirstName();
            String lastName = registerRequest.getLastName();
            String gender = registerRequest.getGender();
            int generations = 4;

            // 1. create a user account with the data
            UUID uuid1 = UUID.randomUUID();
            String personIDForUser = uuid1.toString().substring(0,8);
            user = new User(username, password, email, firstName, lastName, gender, personIDForUser);
            uDao.insert(user);

            db.closeConnection(true);

            FillRequest fillRequest = new FillRequest();
            fillRequest.setGenerations(4);
            fillRequest.setUsername(username);

            FillService fillService = new FillService();
            FillResult fillResult = fillService.fill(fillRequest);

            RegisterResult registerResult = new RegisterResult();

            if (!fillResult.isSuccess()) {
                registerResult.setSuccess(false);
                registerResult.setMessage("Error: missing values");
                return registerResult;
            }

            db.getConnection();
            // 3. use similar login logic to login and return register result
            String personID = null;
            String authTokenString = null;
            if (uDao.validate(username, password)) {
                user = uDao.find(username);
                personID = user.getPersonID();
                UUID uuid2 = UUID.randomUUID();
                authTokenString = uuid2.toString().substring(0, 8);
                authToken = new AuthToken(authTokenString, username);
                if (aDao.validate(authTokenString)) {
                    aDao.deleteRow(authTokenString);
                }
                aDao.insert(authToken);
            }

            db.closeConnection(true);

            if (username == null || password == null || email == null || firstName == null || lastName == null || gender == null) {
                registerResult.setSuccess(false);
                registerResult.setMessage("Error: missing values");
                return registerResult;
            }
            else {
                registerResult.setAuthtoken(authTokenString);
                registerResult.setUsername(username);
                registerResult.setPersonID(personID);
                registerResult.setSuccess(true);
            }

            return registerResult;

        } catch (Exception ex) {
            ex.printStackTrace();
            db.closeConnection(false);

            RegisterResult registerResult = new RegisterResult();
            registerResult.setSuccess(false);
            registerResult.setMessage("Error: error message");
            return registerResult;
        }
    }
}
