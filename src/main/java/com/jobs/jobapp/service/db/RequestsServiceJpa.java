package com.jobs.jobapp.service.db;

import com.jobs.jobapp.model.Request;
import com.jobs.jobapp.repository.RequestsRepository;
import com.jobs.jobapp.service.IRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RequestsServiceJpa implements IRequestsService {

    @Autowired
    private RequestsRepository requestsRepository;
    @Override
    public void save(Request request) {
        requestsRepository.save(request);

    }

    @Override
    public void delete(Integer idRequest) {
        requestsRepository.deleteById(idRequest);

    }

    @Override
    public List<Request> findAll() {
        return requestsRepository.findAll();
    }

    @Override
    public Request findById(Integer idRequest) {
        Optional<Request> optional = requestsRepository.findById(idRequest);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    @Override
    public Page<Request> findAll(Pageable page) {
        return requestsRepository.findAll(page);
    }


}
