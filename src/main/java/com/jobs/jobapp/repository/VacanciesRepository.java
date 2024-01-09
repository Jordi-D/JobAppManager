package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanciesRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findByStatus(String status);

    List<Vacancy> findByHighlightedAndStatusOrderByIdDesc(int highlighted, String status);

    List<Vacancy> findBySalaryBetween(double s1, double s2);

    List<Vacancy> findByStatusIn(String[] status);

}
