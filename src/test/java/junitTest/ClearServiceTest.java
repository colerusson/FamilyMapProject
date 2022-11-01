package junitTest;

import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ClearService;


public class ClearServiceTest {
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
    public void clearPass() {

    }

}
