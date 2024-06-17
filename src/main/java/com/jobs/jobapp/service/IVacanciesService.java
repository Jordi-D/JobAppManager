package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Vacancy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing vacancies.
 */
public interface IVacanciesService {

    /**
     * Retrieves all vacancies.
     *
     * @return List of all vacancies.
     */
    List<Vacancy> findAll();

    /**
     * Finds a vacancy by its ID.
     *
     * @param idVacancy The ID of the vacancy to find.
     * @return The found vacancy, or null if not found.
     */
    Vacancy findById(Integer idVacancy);

    /**
     * Saves or updates a vacancy.
     *
     * @param vacancy The vacancy to save or update.
     */
    void save(Vacancy vacancy);

    /**
     * Retrieves all highlighted vacancies.
     *
     * @return List of highlighted vacancies.
     */
    List<Vacancy> findHighlighted();

    /**
     * Deletes a vacancy by its ID.
     *
     * @param idVacancy The ID of the vacancy to delete.
     */
    void delete(Integer idVacancy);

    /**
     * Finds vacancies matching the specified example.
     *
     * @param example The example to match against vacancies.
     * @return List of vacancies matching the example.
     */
    List<Vacancy> findByExample(Example<Vacancy> example);

    /**
     * Retrieves a page of vacancies.
     *
     * @param page The pagination information.
     * @return Page of vacancies.
     */
    Page<Vacancy> findAll(Pageable page);
}
