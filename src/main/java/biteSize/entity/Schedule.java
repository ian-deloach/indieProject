package biteSize.entity;

import java.util.Date;
import java.util.List;
import biteSize.entity.Task;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

// TODO Create a schedule class that will hold a list of tasks for the day
// This will be a many to many relationship with tasks. Make a separate table.
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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Schedule_TasK",
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
