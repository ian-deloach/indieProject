package biteSize.persistence;

import biteSize.controller.TaskController;
import biteSize.entity.Schedule;
import biteSize.entity.Task;
import biteSize.entity.Theme;
import biteSize.entity.User;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import junit.framework.TestCase;

public class ScheduleTest extends TestCase {

    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao taskDao;
    GenericDao themeDao;
    GenericDao userDao;
    GenericDao scheduleDao;

    // Cleans the database before each test and generates DAOs
    public void setUp() throws Exception {
        taskDao = new GenericDao(Task.class);
        themeDao = new GenericDao(Theme.class);
        userDao = new GenericDao(User.class);
        scheduleDao = new GenericDao(Schedule.class);

        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    public void testInsertNewSchedule() {
        TaskController taskControl = new TaskController();

        taskControl.generateSchedule("Test", 1);
        assertNotNull(scheduleDao.getById(3));
    }

    public void testDeleteSchedule() {
        Schedule scheduleToDelete = (Schedule)scheduleDao.getById(3);
        scheduleDao.delete(scheduleToDelete);

        assertNull(scheduleDao.getById(3));
    }

}
