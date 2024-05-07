package biteSize.persistence;

import biteSize.entity.Task;
import biteSize.entity.User;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TaskTest extends TestCase {

    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao dao;

    // Cleans the database before each test
    public void setUp() throws Exception {
        dao = new GenericDao(Task.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    // Testing getting all tasks
    public void testGetAllTasks() {
        List<Task> tasks = dao.getAll();
        for(Task task : tasks) {
            logger.info(task.getName());
        }
        assertEquals(3, tasks.size());
    }
}
