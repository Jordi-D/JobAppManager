package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the CategoriesService interface.
 * Provides methods to manage categories in memory.
 */
@Service
public class CategoriesServiceImpl implements ICategoriesService {

    private List<Category> list = new LinkedList<>();

    /**
     * Saves a category to the list.
     *
     * @param category The category to be saved.
     */
    public void save(Category category) {
        list.add(category);
    }

    /**
     * Retrieves all categories from the list.
     *
     * @return List of all categories.
     */
    public List<Category> findAll() {
        return list;
    }

    /**
     * Finds a category by its ID.
     *
     * @param idCategory The ID of the category to find.
     * @return The category object if found, null otherwise.
     */
    public Category findById(Integer idCategory) {
        for (Category cat : list) {
            if (cat.getId() == idCategory) {
                return cat;
            }
        }
        return null;
    }

    /**
     * Deletes a category by its ID (not implemented).
     *
     * @param idCategoria The ID of the category to delete.
     */
    @Override
    public void delete(Integer idCategoria) {
        // Implementation pending
    }

    /**
     * Finds all categories using pagination (not implemented).
     *
     * @param page The pagination information.
     * @return Page of categories.
     */
    @Override
    public Page<Category> findAll(Pageable page) {
        // Implementation pending
        return null;
    }
}
