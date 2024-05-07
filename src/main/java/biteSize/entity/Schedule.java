package biteSize.entity;

import java.util.*;

import biteSize.entity.Task;

import biteSize.persistence.GenericDao;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

// This will be a many-to-many relationship with tasks. Make a separate table.
@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name="expiration_date")
    private Date expirationDate;

    @Column(name="date_created")
    private Date dateCreated;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(
            name = "schedule_task",
            joinColumns = {@JoinColumn(name="schedule_id")},
            inverseJoinColumns = {@JoinColumn(name="task_id")}
    )
    private List<Task> tasks;

    @Column(name="name")
    private String name;


    /**
     * Zero param constructor
     */
    public Schedule() {

    }

    public List<Task> generateSchedule(int id) {

        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(id);

        List<Task> taskList = user.getTasks();
        List<Task> urgentTasks = new ArrayList<Task>();
        List<Task> regularTasks = new ArrayList<Task>();
        List<Task> newSchedule = new ArrayList<Task>();

        for (Task task : taskList) {
            if (Objects.equals(task.getUrgency(), "Urgent")) {
                urgentTasks.add(task);
            } else {
                regularTasks.add(task);
            }
        }

        return urgentTasks;
    }

    public Task getRandomTaskFromList(List<Task> tasks) {

        return null;
    }

    /**
     * Getter for expiration date
     * @return expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Setter for expiration date
     * @param expirationDate the new date
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Getter for date created
     * @return the date the schedule was created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Setter for date created
     * @param dateCreated the new creation date
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Getter for list of tasks in schedule
     * @return
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Setter for the list of tasks in schedule
     * @param tasks the new list of tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for schedule name
     * @return the schedule name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for schedule name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for schedule ID
     * @return The id
     */
    public int getId() {
        return id;
    }
}
