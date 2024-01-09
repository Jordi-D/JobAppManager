package com.jobs.jobapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date; // Date when the user applied for this job offer
    private String comments;
    private String file; // The name of the PDF or DOCX file of the CV.

    @OneToOne
    @JoinColumn(name = "idVacancy") // Foreign key in the requests table
    private Vacancy vacancy;

    @OneToOne
    @JoinColumn(name = "idUser") // Foreign key in the users table
    private User user;

    public Request() {
        this.date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Request [id=" + id + ", date=" + date + ", comments=" + comments + ", file=" + file
                + ", vacancy=" + vacancy + ", user=" + user + "]";
    }
}
