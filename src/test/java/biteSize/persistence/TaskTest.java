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

public class TaskTest extends TestCase {

    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao taskDao;
    GenericDao themeDao;
    GenericDao userDao;

    // Cleans the database before each test
    public void setUp() throws Exception {
        taskDao = new GenericDao(Task.class);
        themeDao = new GenericDao(Theme.class);
        userDao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    // Testing getting all tasks
    public void testGetAllTasks() {
        List<Task> tasks = taskDao.getAll();
        for(Task task : tasks) {
            logger.info(task.getName());
        }
        assertEquals(10, tasks.size());
    }

    // Tests adding a single task to a user
    public void testInsertTask() {
        Theme theme = (Theme)themeDao.getById(1);
        User user = (User)userDao.getById(1);

        Task newTask = new Task("Test task", null, theme, user);
        taskDao.insert(newTask);

        Task insertedTask = (Task)taskDao.getById(13);
        assertEquals("Test task", insertedTask.getName());
        assertNull(insertedTask.getUrgency());
        assertEquals("Java", insertedTask.getTheme().getName());
        assertEquals("Ian", insertedTask.getUser().getName());
    }

    // Ensures correct tasks are deleted.
    public void testDeleteTask() {
        // Gets the eat breakfast task
        Task taskToDelete = (Task)taskDao.getById(4);
        taskDao.delete(taskToDelete);
        assertNull(taskDao.getById(4));

        List<Task> remainingTasks = taskDao.getAll();
        assertEquals(9, remainingTasks.size());

    }

    // Checks to see if themes/tasks are deleted during user deletion
    public void testTaskAndThemeOrphanRemoval() {
        User userToDelete = (User)userDao.getById(1);
        userDao.delete(userToDelete);

        assertNull(taskDao.getById(1));
        assertNull(themeDao.getById(1));

    }

    /*
        Tests to see if schedule generation works as intended.
        Uses users that have more than 4 tasks to search through, as well
        as a user that only has 3 tasks.
    */
    public void testGenerateTaskList() {
        TaskController taskController = new TaskController();
        List<Task> testFullList = taskController.generateSchedule(1);
        List<Task> testParialList = taskController.generateSchedule(2);

        for(Task i : testFullList) {
            logger.info("Full list: " + i.getName() + " Urgency: " + i.getUrgency());
        }

        for(Task i : testParialList) {
            logger.info("Partial List: " + i.getName() + " Urgency: " + i.getUrgency());
        }

        assertEquals(4, testFullList.size());
        assertEquals(3, testParialList.size());
    }

    public void testUpdateTask() {
        Task taskToUpdate = (Task)taskDao.getById(1);
        taskToUpdate.setName("Present BiteSize");
        taskDao.update(taskToUpdate);

        Task retrievedTask = (Task)taskDao.getById(1);
        assertEquals("Present BiteSize", retrievedTask.getName());
    }
}
