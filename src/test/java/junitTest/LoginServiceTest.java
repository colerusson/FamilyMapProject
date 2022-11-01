package junitTest;

import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ClearService;

public class LoginServiceTest {
    ClearService clearService;

    @BeforeEach
    public void setUp() {
        clearService = new ClearService();


    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        clearService.clear();
    }

    @Test
    public void loginPass() {

    }

    @Test
    public void loginFail() {

    }
}
