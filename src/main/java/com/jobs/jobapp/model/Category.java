package com.jobs.jobapp.model;

import jakarta.persistence.*;

/**
 * Represents a category for job vacancies.
 * This class maps to the "Categories" table in the database.
 */
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    /**
     * Retrieves the ID of the category.
     *
     * @return The ID of the category.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id The ID of the category.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name of the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the description of the category.
     *
     * @return The description of the category.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the category.
     *
     * @param description The description of the category.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
