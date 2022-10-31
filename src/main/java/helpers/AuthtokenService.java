package helpers;

import dao.AuthTokenDao;
import dao.Database;

import java.sql.Connection;

public class AuthtokenService {
    private Database db;
    private AuthTokenDao aDao;
    public boolean verify(String authtoken) {
        db = new Database();
        try {
            Connection conn = db.getConnection();
            aDao = new AuthTokenDao(conn);
            if (aDao.validate(authtoken)) {
                db.closeConnection(true);
                return true;
            }
            else {
                db.closeConnection(true);
                return false;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            db.closeConnection(false);
            return false;
        }
    }
}
