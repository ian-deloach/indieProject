package biteSize.persistence;

import biteSize.entity.User;
import junit.framework.TestCase;

import java.util.List;

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

    public void testUserDelete() {
        User userToDelete = (User)dao.getById(1);
        dao.delete(userToDelete);
        assertNull(dao.getById(1));
    }

    public void testGetAll() {
        List<User> users = dao.getAll();
        assertEquals(2, users.size());
    }


}
