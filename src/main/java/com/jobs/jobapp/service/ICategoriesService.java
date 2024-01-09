package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriesService {
    void save(Category category);

    List<Category> findAll();

    Category findById(Integer idCategory);

    void delete(Integer idCategory);

    Page<Category> findAll(Pageable page);


}