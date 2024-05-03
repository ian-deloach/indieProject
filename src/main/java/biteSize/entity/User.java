package biteSize.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

/**
 * A class to represent the users
 * @author IanDeLoach
 */

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

    /**
     * No param constructor for user
     */
    public User() {
        name = "User";
        email = "EmailNotSupplied";
    }

    /**
     * Constructor for user
     *
     * @param name the user's name
     * @param email the user's email
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the user's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's tasks
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks
     * @param tasks the new list of tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }
}
