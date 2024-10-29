package biteSize.controller;

import biteSize.entity.Schedule;
import biteSize.entity.Task;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A controller to handle methods that pertain to tasks.
 * This includes schedules as those are just lists of tasks.
 */
public class TaskController {

    /**
     * Generates a list of tasks for a new schedule. Each schedule has 3 regular
     * tasks and 1 urgent task.
     * @param name The name of the schedule
     * @param id The id of the user
     */
    // Currently generates List<Task>
    public void generateSchedule(String name, int id) {

        UserController userControl = new UserController();
        User user = userControl.getUserFromId(id);

        List<Task> taskList = user.getTasks();
        List<Task> urgentTasks = new ArrayList<Task>();
        List<Task> regularTasks = new ArrayList<Task>();

        for (Task task : taskList) {
            if (Objects.equals(task.getUrgency(), "Urgent")) {
                urgentTasks.add(task);
            } else {
                regularTasks.add(task);
            }
        }

        List<Task> scheduleTasks = generateRandomTasks(regularTasks);

        if (!urgentTasks.isEmpty()) {
            scheduleTasks.add(generateUrgentTask(urgentTasks));
        }

        Schedule newSchedule = new Schedule(name, user, scheduleTasks);

        insertSchedule(newSchedule);

//        return newSchedule;
    }

    /**
     * Generates a random task from the list of regular tasks
     * @param tasks The list of regular tasks
     * @return A list of 4 random tasks
     */
    public List<Task> generateRandomTasks(List<Task> tasks) {

        final Logger logger = LogManager.getLogger(this.getClass());

        Random rng = new Random();
        int randomNumber;

        List<Task> randomTasks = new ArrayList<Task>();

        while (randomTasks.size() < 3 && !tasks.isEmpty()) {
            randomNumber = rng.nextInt(tasks.size());
            randomTasks.add(tasks.get(randomNumber));
            tasks.remove(randomNumber);
        }

        return randomTasks;
    }

    /**
     * Randomly selects 1 random high priority task
     * @param urgentTasks The list of urgent tasks
     * @return The random urgent task
     */
    public Task generateUrgentTask(List<Task> urgentTasks) {

        final Logger logger = LogManager.getLogger(this.getClass());

        Random rng = new Random();
        int randomNumber = rng.nextInt((urgentTasks.size()));
        Task urgentTask = urgentTasks.get(randomNumber);

        return urgentTask;
    }

    /**
     * Insert a schedule into the database
     * @param scheduleToInsert The schedule to be inserted
     */
    public void insertSchedule(Schedule scheduleToInsert) {

        GenericDao<Schedule> scheduleDao = new GenericDao<>(Schedule.class);

        scheduleDao.insert(scheduleToInsert);

    }
}
