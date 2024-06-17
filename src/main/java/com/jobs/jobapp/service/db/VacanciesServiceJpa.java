package com.jobs.jobapp.service.db;

import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.repository.VacanciesRepository;
import com.jobs.jobapp.service.IVacanciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of IVacanciesService that uses JPA for database operations on vacancies.
 */
@Service
@Primary
@Tag(name = "Vacancies Service", description = "Service for managing vacancies using JPA")
public class VacanciesServiceJpa implements IVacanciesService {

    @Autowired
    private VacanciesRepository vacanciesRepository;

    @Override
    @Operation(summary = "Get all vacancies")
    public List<Vacancy> findAll() {
        return vacanciesRepository.findAll();
    }

    @Override
    @Operation(summary = "Find a vacancy by ID")
    public Vacancy findById(Integer idVacancy) {
        Optional<Vacancy> opt = vacanciesRepository.findById(idVacancy);
        return opt.orElse(null);
    }

    @Override
    @Operation(summary = "Save a vacancy")
    public void save(Vacancy vacancy) {
        vacanciesRepository.save(vacancy);
    }

    @Override
    @Operation(summary = "Find all highlighted vacancies")
    public List<Vacancy> findHighlighted() {
        return vacanciesRepository.findByHighlightedAndStatusOrderByIdDesc(1, "Approved");
    }

    @Override
    @Operation(summary = "Delete a vacancy by ID")
    public void delete(Integer idVacancy) {
        vacanciesRepository.deleteById(idVacancy);
    }

    @Override
    @Operation(summary = "Find vacancies matching example criteria")
    public List<Vacancy> findByExample(Example<Vacancy> example) {
        return vacanciesRepository.findAll(example);
    }

    @Override
    @Operation(summary = "Get all vacancies with pagination")
    public Page<Vacancy> findAll(Pageable page) {
        return vacanciesRepository.findAll(page);
    }
}
