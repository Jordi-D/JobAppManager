package com.jobs.jobapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Represents a job application request made by a user for a specific vacancy.
 * This class maps to the "Requests" table in the database.
 */
@Entity
@Table(name = "Requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate date; // Date when the user applied for this job offer

    @Column(name = "comments")
    private String comments;

    @Column(name = "file")
    private String file; // The name of the PDF or DOCX file of the CV.

    @OneToOne
    @JoinColumn(name = "idVacancy") // Foreign key in the requests table
    private Vacancy vacancy;

    @OneToOne
    @JoinColumn(name = "idUser") // Foreign key in the users table
    private User user;

    /**
     * Default constructor initializes the application date to the current date.
     */
    public Request() {
        this.date = LocalDate.now();
    }

    /**
     * Retrieves the ID of the request.
     *
     * @return The ID of the request.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the request.
     *
     * @param id The ID of the request.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the date when the application was made.
     *
     * @return The application date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the application was made.
     *
     * @param date The application date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Retrieves the file name of the CV attached with the application.
     *
     * @return The file name of the CV.
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the file name of the CV attached with the application.
     *
     * @param file The file name of the CV.
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Retrieves the vacancy to which the application is made.
     *
     * @return The vacancy object.
     */
    public Vacancy getVacancy() {
        return vacancy;
    }

    /**
     * Sets the vacancy to which the application is made.
     *
     * @param vacancy The vacancy object.
     */
    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    /**
     * Retrieves the user who made the application.
     *
     * @return The user object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who made the application.
     *
     * @param user The user object.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Retrieves the comments or cover letter attached with the application.
     *
     * @return The comments or cover letter.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments or cover letter attached with the application.
     *
     * @param comments The comments or cover letter.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", comments='" + comments + '\'' +
                ", file='" + file + '\'' +
                ", vacancy=" + vacancy +
                ", user=" + user +
                '}';
    }
}
