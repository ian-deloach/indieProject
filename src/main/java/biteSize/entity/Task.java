package biteSize.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * A class to represent the tasks created
 * @author IanDeLoach
 */

@Entity
@Table(name="task")
public class Task {

    @Column(name="name")
    private String name;

    @Column(name="deadline")
    private Date deadline;

    @Column(name="urgency")
    private String urgency;

    @ManyToOne
    private Theme theme;

    @Column(name="description")
    private String description;

    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

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
}
