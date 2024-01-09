package com.jobs.jobapp.service.db;

import com.jobs.jobapp.model.Category;
import com.jobs.jobapp.repository.CategoriesRepository;
import com.jobs.jobapp.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoryServiceJpa implements ICategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public void save(Category categoria) {
        categoriesRepository.save(categoria);
    }

    @Override
    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Category findById(Integer idCategory) {
        Optional<Category> opt = categoriesRepository.findById(idCategory);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public void delete(Integer idCategory) {
        categoriesRepository.deleteById(idCategory);
    }

    @Override
    public Page<Category> findAll(Pageable page) {
        return categoriesRepository.findAll(page);
    }
}
