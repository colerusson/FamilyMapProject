package dao;

import model.AuthToken;

import java.sql.Connection;

public class AuthTokenDao {
    private final Connection conn;

    public AuthTokenDao(Connection conn) {
        this.conn = conn;
    }

    public void createToken(AuthToken authToken) {
    }

    public boolean validate(String authtoken) {
        return false;
    }

    public AuthToken getTokenByUsername(String username) {
        return null;
    }
}
