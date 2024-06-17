package com.jobs.jobapp.model;

import jakarta.persistence.*;

/**
 * Represents a user profile or role.
 * This class maps to the "Profiles" table in the database.
 */
@Entity
@Table(name = "Profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile")
    private String profile;

    /**
     * Retrieves the ID of the profile.
     *
     * @return The ID of the profile.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the profile.
     *
     * @param id The ID of the profile.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the profile name.
     *
     * @return The profile name.
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the profile name.
     *
     * @param profile The profile name.
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", profile='" + profile + '\'' +
                '}';
    }
}
