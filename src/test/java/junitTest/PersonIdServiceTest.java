package junitTest;

import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ClearService;

import static org.junit.jupiter.api.Assertions.*;

public class PersonIdServiceTest {
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
    public void personIdServicePass() {

    }

    @Test
    public void personIdServiceFail() {

    }
}
