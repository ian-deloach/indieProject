package biteSize.persistence;

import biteSize.entity.Schedule;
import biteSize.entity.Task;
import biteSize.entity.Theme;
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
        assertEquals(10, tasks.size());
    }

    public void testInsertTask() {
        GenericDao themeDao = new GenericDao(Theme.class);
        Theme theme = (Theme)themeDao.getById(1);
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(1);

        Task newTask = new Task("Test task", null, theme, user);
        dao.insert(newTask);

        Task insertedTask = (Task)dao.getById(13);
        assertEquals("Test task", insertedTask.getName());
        assertNull(insertedTask.getUrgency());
        assertEquals("Java", insertedTask.getTheme().getName());
        assertEquals("Ian", insertedTask.getUser().getName());
    }

//    public void testGenerateTaskList() {
//        Schedule schedule = new Schedule();
//        List<Task> testList = schedule.generateSchedule(1);
//
//        assertEquals(4, testList.size());
//    }

    public void testUpdateTask() {
        Task taskToUpdate = (Task)dao.getById(1);
        taskToUpdate.setName("Present BiteSize");
        dao.update(taskToUpdate);

        Task retrievedTask = (Task)dao.getById(1);
        assertEquals("Present BiteSize", retrievedTask.getName());
    }
}
