package dao;

import model.User;

import java.sql.Connection;

public class UserDao {
    private final Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public void createUser(User user) {
    }

    public boolean validate(String username, String password) {
        return false;
    }

    public User getUserById(String userId) {
        return null;
    }

    public User getUserByName(String firstName, String lastName) {
        return null;
    }

    public User getUserByPersonID(String personID) {
        return null;
    }

    public void deleteRow() {
    }

    public void deleteTable() {

    }

}
