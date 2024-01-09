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

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VacanciesServiceJpa implements IVacanciesService {
    @Autowired
    private VacanciesRepository vacanciesRepository;

    @Override
    public List<Vacancy> findAll() {
        return vacanciesRepository.findAll();
    }

    @Override
    public Vacancy findById(Integer idVacancy) {
        Optional<Vacancy> opt = vacanciesRepository.findById(idVacancy);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public void save(Vacancy vacancy) {
        vacanciesRepository.save(vacancy);

    }

    @Override
    public List<Vacancy> findHighlighted() {
        return vacanciesRepository.findByHighlightedAndStatusOrderByIdDesc(1, "Approved");
    }

    @Override
    public void delete(Integer idVacancy) {
        vacanciesRepository.deleteById(idVacancy);
    }

    @Override
    public List<Vacancy> findByExample(Example<Vacancy> example) {
        return vacanciesRepository.findAll(example);

    }

    @Override
    public Page<Vacancy> findAll(Pageable page) {
        return vacanciesRepository.findAll(page);
    }
}
