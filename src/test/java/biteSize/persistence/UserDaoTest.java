package biteSize.persistence;

import biteSize.entity.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDaoTest extends TestCase {

    GenericDao dao;

    public void setUp() throws Exception {
        dao = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    public void testGetById() {
        User retrievedUser = (User)dao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("Ian", retrievedUser.getName());
    }


}
