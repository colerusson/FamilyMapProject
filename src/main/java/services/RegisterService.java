package services;

import dao.DataAccessException;
import dao.Database;
import dao.UserDao;
import model.User;
import request.FillRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;

import java.sql.Connection;

/**
 * request service class for register request, runs the functionality to actually perform the request
 */
public class RegisterService {

    private Database db;
    private UserDao uDao;
    private User user;
    private FillService fillService;
    private LoginService loginService;
    /**
     * register method to actually run the request sent int from the user to regsiter as a user
     * @param registerRequest a request object sent in form the handler
     * @return a register result object obtained from the result package classes
     */
    public RegisterResult register(RegisterRequest registerRequest) throws DataAccessException {
        db = new Database();
        Connection conn = db.getConnection();
        uDao = new UserDao(conn);

        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String email = registerRequest.getEmail();
        String firstName = registerRequest.getFirstName();
        String lastName = registerRequest.getLastName();
        String gender = registerRequest.getGender();

        int generations = 4;

        RegisterResult registerResult = new RegisterResult();
        if (username == null || password == null || email == null || firstName == null || lastName == null || gender == null) {
            registerResult.setSuccess(false);
            registerResult.setMessage("Error: missing values");
            return registerResult;
        }
        // create a user account with the data
        user = new User(username, password, email, firstName, lastName, gender, null);
        uDao.insert(user);

        // call fill with generation value of 4
        fillService = new FillService();
        FillRequest fillRequest = new FillRequest();

        fillRequest.setGenerations(generations);
        fillRequest.setUsername(username);
        fillService.fill(fillRequest);

        // call login for the end
        loginService = new LoginService();
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setPassword(password);
        loginRequest.setUsername(username);
        LoginResult loginResult = loginService.login(loginRequest);

        registerResult.setAuthtoken(loginResult.getAuthtoken());
        registerResult.setUsername(loginResult.getUsername());
        registerResult.setPersonID(loginResult.getPersonID());
        registerResult.setSuccess(loginResult.isSuccess());

        db.closeConnection(true);

        return registerResult;
    }

}
