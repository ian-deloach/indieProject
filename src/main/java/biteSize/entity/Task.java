package biteSize.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;
import java.util.Date;

/**
 * A class to represent the tasks created
 * @author IanDeLoach
 */

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="deadline")
    private Date deadline;

    @Column(name="urgency")
    private String urgency;

    @ManyToOne
    private Theme theme;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Schedule> schedules = new ArrayList<>();

    @Column(name="description")
    private String description;

    @ManyToOne
    private User user;

    /**
     * Zero param constructor for the task class
     */
    public Task() {
        name = "My task";
        urgency = null;
    }

    /**
     * Constructor for a task class
     */
    public Task(String name, String urgency, Theme theme, User user) {
        this.name = name;
        this.urgency = urgency;
        this.theme = theme;
        this.user = user;
    }


    /**
     * Getter for name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the deadline
     * @return deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Setter for deadline
     * @param deadline the new deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * Getter for the urgency
     * @return the urgency
     */
    public String getUrgency() {
        return urgency;
    }

    /**
     * Setter for urgency
     * @param urgency the new urgency
     */
    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    /**
     * Getter for theme
     * @return the theme
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Setter for theme
     * @param theme
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /**
     * Getter for user
     * @return the user object associated to the task
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for user
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for ID
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the schedule list
     * @return the task's schedule list
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * Setter for the task's schedule list
     * @param schedules the new list of schedules
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
