package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {
}
