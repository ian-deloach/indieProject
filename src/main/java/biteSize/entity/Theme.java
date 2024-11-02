package biteSize.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

/**
 * A class to represent themes
 * @author Ian DeLoach
 */

@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

    @Column(name = "is_primary")
    private boolean isPrimary;

    /**
     * Zero param constructor for Themes
     */
    public Theme() {

    }

    /**
     * Constructor for Themes with users and names
     */
    public Theme(User user, String name) {
        this.user = user;
        this.name = name;
    }

    /**
     * Getter for id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the theme's user
     * @return the User associated with the theme
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for the theme's user
     * @param user the new User object
     */
    public void setUser(User user) {
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
     * Getter for isPrimary
     * @return if the theme is a primary theme or not
     */
    public boolean getIsPrimary() {
        return isPrimary;
    }

    /**
     * Setter for isPrimary
     */
    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     * Getter for tasks
     * @return the list of tasks associated with the theme
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Setter for tasks
     * @param tasks the new list of tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
