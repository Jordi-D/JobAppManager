package com.jobs.jobapp.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a user of the job application system.
 * This class maps to the "Users" table in the database.
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private int status;

    @Column(name = "registrationDate")
    private Date registrationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserProfile",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idProfile"))
    private List<Profile> profiles;

    /**
     * Adds a profile to the user's list of profiles.
     *
     * @param tempProfile The profile to be added.
     */
    public void add(Profile tempProfile) {
        if (profiles == null) {
            profiles = new LinkedList<>();
        }
        profiles.add(tempProfile);
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return The ID of the user.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID of the user.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the status of the user.
     *
     * @return The status of the user.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     *
     * @param status The status of the user.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Retrieves the registration date of the user.
     *
     * @return The registration date of the user.
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the registration date of the user.
     *
     * @param registrationDate The registration date of the user.
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Retrieves the list of profiles associated with the user.
     *
     * @return The list of profiles associated with the user.
     */
    public List<Profile> getProfiles() {
        return profiles;
    }

    /**
     * Sets the list of profiles associated with the user.
     *
     * @param profiles The list of profiles associated with the user.
     */
    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", registrationDate=" + registrationDate +
                ", profiles=" + profiles +
                '}';
    }
}
