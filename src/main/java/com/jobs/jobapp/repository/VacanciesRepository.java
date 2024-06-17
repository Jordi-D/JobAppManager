package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Vacancy entities.
 * Extends JpaRepository which provides basic CRUD operations.
 */
@Repository
public interface VacanciesRepository extends JpaRepository<Vacancy, Integer> {

    /**
     * Retrieves a list of vacancies by their status.
     *
     * @param status The status of the vacancies to retrieve.
     * @return List of vacancies with the specified status.
     */
    List<Vacancy> findByStatus(String status);

    /**
     * Retrieves a list of vacancies that are highlighted and have a specific status,
     * ordered by ID in descending order.
     *
     * @param highlighted Flag indicating if the vacancy is highlighted (1 for true, 0 for false).
     * @param status      The status of the vacancies to retrieve.
     * @return List of highlighted vacancies with the specified status, ordered by ID descending.
     */
    List<Vacancy> findByHighlightedAndStatusOrderByIdDesc(int highlighted, String status);

    /**
     * Retrieves a list of vacancies with salaries within a specified range.
     *
     * @param s1 Minimum salary value.
     * @param s2 Maximum salary value.
     * @return List of vacancies with salaries between s1 and s2.
     */
    List<Vacancy> findBySalaryBetween(double s1, double s2);

    /**
     * Retrieves a list of vacancies with statuses that match any in the provided array.
     *
     * @param status Array of statuses to match.
     * @return List of vacancies with statuses in the provided array.
     */
    List<Vacancy> findByStatusIn(String[] status);

}
