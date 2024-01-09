package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRequestsService {
    void save(Request request);
    void delete(Integer idRequest);
    List<Request> findAll();
    Request findById(Integer idRequest);
    Page<Request> findAll(Pageable page);
}
