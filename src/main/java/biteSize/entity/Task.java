package biteSize.entity;

import java.util.Date;

/**
 * A class to represent the tasks created
 * @author IanDeLoach
 */

public class Task {

    private String name;
    private Date deadline;
    private String urgency;
    private String theme;
    private User user;
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
    public String getTheme() {
        return theme;
    }

    /**
     * Setter for theme
     * @param theme
     */
    public void setTheme(String theme) {
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
}
