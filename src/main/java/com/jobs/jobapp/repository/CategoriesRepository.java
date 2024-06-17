package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Category entities.
 * Extends JpaRepository which provides basic CRUD operations.
 */
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
}
