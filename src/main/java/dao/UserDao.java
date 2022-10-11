package dao;

import model.User;

import java.sql.Connection;

public class UserDao {
    private final Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    void createUser(User user) {
    }

    boolean validate(String username, String password) {
        return false;
    }

    User getUserById(String userId) {
        return null;
    }

}
