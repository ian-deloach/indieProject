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

    public void testInsert() {
        int insertedID;
        User newUser = new User("Kobey", "kobey@gmail.com");
        dao.insert(newUser);

        User insertedUser = (User)dao.getById(3);
        assertEquals("Kobey", insertedUser.getName());
        assertEquals(3, insertedUser.getId());
    }

    public void testUpdate() {
        User userToUpdate = (User)dao.getById(1);
        userToUpdate.setName("Michael");
        dao.update(userToUpdate);

        User retrievedUser = (User)dao.getById(1);
        assertEquals("Michael", retrievedUser.getName());
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

    public void testGetByEqual() {
        List<User> users = dao.getPropertyEqual("name", "Ian");
        assertEquals(1, users.size());
        assertEquals("Ian", users.get(0).getName());
    }

    public void testGetByLike() {
        List<User> users = dao.getPropertyLike("name", "ian");
        assertEquals(2, users.size());
        assertEquals("Ian", users.get(0).getName());
        assertEquals("Arianna", users.get(1).getName());
    }


}
