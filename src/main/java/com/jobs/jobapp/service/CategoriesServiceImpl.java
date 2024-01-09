package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriesServiceImpl implements ICategoriesService {

    private List<Category> list = null;

    public void save(Category category) {
        list.add(category);
    }

    public List<Category> findAll() {
        return list;
    }

    public Category findById(Integer idCategory) {
        for (Category cat : list) {
            if (cat.getId() == idCategory) {
                return cat;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer idCategoria) {

    }

    @Override
    public Page<Category> findAll(Pageable page) {
        return null;
    }

}
