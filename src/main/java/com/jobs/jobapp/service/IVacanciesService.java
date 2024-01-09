package com.jobs.jobapp.service;


import com.jobs.jobapp.model.Vacancy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVacanciesService {
    List<Vacancy> findAll();

    Vacancy findById(Integer idVacancy);

    void save(Vacancy vacancy);

    List<Vacancy> findHighlighted();

    void delete(Integer idVacancy);

    List<Vacancy> findByExample(Example<Vacancy> example);

    Page<Vacancy> findAll(Pageable page);
}
