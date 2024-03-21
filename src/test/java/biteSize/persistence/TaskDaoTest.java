package biteSize.persistence;

import biteSize.entity.Task;
import biteSize.entity.User;
import junit.framework.TestCase;

import java.util.List;

public class TaskDaoTest extends TestCase {

    GenericDao dao;

    public void setUp() throws Exception {
        dao = new GenericDao(Task.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    public void testGetById() {

    }

    public void testInsert() {

    }

    public void testUpdate() {

    }

    public void testUserDelete() {

    }

    public void testGetAll() {

    }

    public void testGetByEqual() {

    }

    public void testGetByLike() {

    }
}
