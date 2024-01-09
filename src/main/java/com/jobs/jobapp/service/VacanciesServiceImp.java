package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.repository.VacanciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Service
public class VacanciesServiceImp implements IVacanciesService {
    private List<Vacancy> list=null;

    private List<Vacancy> lista=null;
    public VacanciesServiceImp(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<Vacancy>();

        Vacancy vacante1 = new Vacancy();
        vacante1.setId(1);
        vacante1.setName("Ingeniero de sistemas");
        vacante1.setDescription("Ingeniero de sistemas para desarrollo de software");
        vacante1.setSalary(900009);
        vacante1.setHighlighted(1);
        vacante1.setDate(new Date());
        vacante1.setImage("empresa1.png");

        lista.add(vacante1);
    }
    @Autowired
    private VacanciesRepository vacanciesRepository;
    @Override
    public List<Vacancy> findAll() {
        return list;
    }

    @Override
    public Vacancy findById(Integer idVacante) {
        for (Vacancy v : list){
            if (v.getId() == idVacante){
                return v;
            }
        }
        return null;
    }
    @Override
    public void save(Vacancy vacante) {
        list.add(vacante);
    }

    public List<Vacancy> findHighlighted(){
        return vacanciesRepository.findByHighlightedAndStatusOrderByIdDesc(1,"Approved");
    }

    @Override
    public void delete(Integer idVacante) {

    }

    @Override
    public List<Vacancy> findByExample(Example<Vacancy> example) {
        return null;
    }

    @Override
    public Page<Vacancy> findAll(Pageable page) {
        return null;
    }
}
