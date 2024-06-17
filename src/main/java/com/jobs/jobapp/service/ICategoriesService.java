package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing categories.
 */
public interface ICategoriesService {

    /**
     * Saves a new category.
     *
     * @param category The category to save.
     */
    void save(Category category);

    /**
     * Retrieves all categories.
     *
     * @return List of all categories.
     */
    List<Category> findAll();

    /**
     * Finds a category by its ID.
     *
     * @param idCategory The ID of the category to find.
     * @return The found category, or null if not found.
     */
    Category findById(Integer idCategory);

    /**
     * Deletes a category by its ID.
     *
     * @param idCategory The ID of the category to delete.
     */
    void delete(Integer idCategory);

    /**
     * Retrieves a page of categories.
     *
     * @param page The pagination information.
     * @return Page of categories.
     */
    Page<Category> findAll(Pageable page);
}
