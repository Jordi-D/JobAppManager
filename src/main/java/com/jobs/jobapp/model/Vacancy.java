package com.jobs.jobapp.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents a job vacancy in the job application system.
 * This class maps to the "Vacancies" table in the database.
 */
@Entity
@Table(name = "Vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Column(name = "salary")
    private double salary;

    @Column(name = "status")
    private String status;

    @Column(name = "highlighted")
    private Integer highlighted;

    @Column(name = "image")
    private String image = "no-image.png";

    @Column(name = "details")
    private String details;

    @OneToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    /**
     * Retrieves the ID of the vacancy.
     *
     * @return The ID of the vacancy.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the vacancy.
     *
     * @param id The ID of the vacancy.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the vacancy.
     *
     * @return The name of the vacancy.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the vacancy.
     *
     * @param name The name of the vacancy.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the description of the vacancy.
     *
     * @return The description of the vacancy.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the vacancy.
     *
     * @param description The description of the vacancy.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the date when the vacancy was posted.
     *
     * @return The date when the vacancy was posted.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date when the vacancy was posted.
     *
     * @param date The date when the vacancy was posted.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retrieves the salary offered for the vacancy.
     *
     * @return The salary offered for the vacancy.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary offered for the vacancy.
     *
     * @param salary The salary offered for the vacancy.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Retrieves the status of the vacancy.
     *
     * @return The status of the vacancy.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the vacancy.
     *
     * @param status The status of the vacancy.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves whether the vacancy is highlighted.
     *
     * @return Whether the vacancy is highlighted (1 for highlighted, 0 otherwise).
     */
    public Integer getHighlighted() {
        return highlighted;
    }

    /**
     * Sets whether the vacancy is highlighted.
     *
     * @param highlighted Whether the vacancy is highlighted (1 for highlighted, 0 otherwise).
     */
    public void setHighlighted(Integer highlighted) {
        this.highlighted = highlighted;
    }

    /**
     * Retrieves the image associated with the vacancy.
     *
     * @return The image associated with the vacancy.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image associated with the vacancy.
     *
     * @param image The image associated with the vacancy.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Retrieves additional details about the vacancy.
     *
     * @return Additional details about the vacancy.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets additional details about the vacancy.
     *
     * @param details Additional details about the vacancy.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Retrieves the category of the vacancy.
     *
     * @return The category of the vacancy.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the vacancy.
     *
     * @param category The category of the vacancy.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Resets the image associated with the vacancy to default.
     */
    public void reset() {
        this.image = "no-image.png";
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                ", highlighted=" + highlighted +
                ", image='" + image + '\'' +
                ", details='" + details + '\'' +
                ", category=" + category +
                '}';
    }
}
