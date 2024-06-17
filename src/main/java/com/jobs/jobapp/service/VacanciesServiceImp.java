package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.repository.VacanciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Service
@Tag(name = "Vacancies Service", description = "Service for managing vacancies")
public class VacanciesServiceImp implements IVacanciesService {

    private List<Vacancy> list = null; // This list appears unused, consider removing if not needed.

    private List<Vacancy> lista = null;

    public VacanciesServiceImp() {
        // Initialize lista if needed, or you can remove it if not used.
    }

    @Autowired
    private VacanciesRepository vacanciesRepository;

    @Override
    @Operation(summary = "Get all vacancies")
    public List<Vacancy> findAll() {
        return list; // Ensure list is properly initialized and populated, if used.
    }

    @Override
    @Operation(summary = "Get a vacancy by ID")
    public Vacancy findById(Integer idVacante) {
        for (Vacancy v : list) {
            if (v.getId() == idVacante) {
                return v;
            }
        }
        return null; // Return null if vacancy with given ID is not found.
    }

    @Override
    @Operation(summary = "Save a new vacancy")
    public void save(Vacancy vacante) {
        list.add(vacante); // Add vacancy to the list, assuming list is initialized and managed properly.
    }

    @Override
    @Operation(summary = "Get all highlighted vacancies")
    public List<Vacancy> findHighlighted() {
        return vacanciesRepository.findByHighlightedAndStatusOrderByIdDesc(1, "Approved");
    }

    @Override
    @Operation(summary = "Delete a vacancy by ID")
    public void delete(Integer idVacante) {
        // Implementation not provided, add logic to delete vacancy by ID if required.
    }

    @Override
    @Operation(summary = "Find vacancies by example")
    public List<Vacancy> findByExample(Example<Vacancy> example) {
        // Implementation not provided, add logic to find vacancies by example if required.
        return null;
    }

    @Override
    @Operation(summary = "Get all vacancies with pagination")
    public Page<Vacancy> findAll(Pageable page) {
        // Implementation not provided, add logic for pagination if required.
        return null;
    }
}
