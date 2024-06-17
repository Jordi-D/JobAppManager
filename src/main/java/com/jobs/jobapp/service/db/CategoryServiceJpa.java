package com.jobs.jobapp.service.db;

import com.jobs.jobapp.model.Category;
import com.jobs.jobapp.repository.CategoriesRepository;
import com.jobs.jobapp.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ICategoriesService that uses JPA for database operations on categories.
 */
@Service
@Primary
@Tag(name = "Category Service", description = "Service for managing categories using JPA")
public class CategoryServiceJpa implements ICategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    @Operation(summary = "Save a category")
    public void save(Category category) {
        categoriesRepository.save(category);
    }

    @Override
    @Operation(summary = "Get all categories")
    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    @Operation(summary = "Get a category by ID")
    public Category findById(Integer idCategory) {
        Optional<Category> opt = categoriesRepository.findById(idCategory);
        return opt.orElse(null); // Return the category if present, otherwise null.
    }

    @Override
    @Operation(summary = "Delete a category by ID")
    public void delete(Integer idCategory) {
        categoriesRepository.deleteById(idCategory);
    }

    @Override
    @Operation(summary = "Get all categories with pagination")
    public Page<Category> findAll(Pageable page) {
        return categoriesRepository.findAll(page);
    }
}
