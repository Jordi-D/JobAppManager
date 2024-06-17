package com.jobs.jobapp.service.db;

import com.jobs.jobapp.model.Request;
import com.jobs.jobapp.repository.RequestsRepository;
import com.jobs.jobapp.service.IRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of IRequestsService that uses JPA for database operations on requests.
 */
@Service
@Tag(name = "Requests Service", description = "Service for managing requests using JPA")
public class RequestsServiceJpa implements IRequestsService {

    @Autowired
    private RequestsRepository requestsRepository;

    @Override
    @Operation(summary = "Save a request")
    public void save(Request request) {
        requestsRepository.save(request);
    }

    @Override
    @Operation(summary = "Delete a request by ID")
    public void delete(Integer idRequest) {
        requestsRepository.deleteById(idRequest);
    }

    @Override
    @Operation(summary = "Get all requests")
    public List<Request> findAll() {
        return requestsRepository.findAll();
    }

    @Override
    @Operation(summary = "Get a request by ID")
    public Request findById(Integer idRequest) {
        Optional<Request> optional = requestsRepository.findById(idRequest);
        return optional.orElse(null); // Return the request if present, otherwise null.
    }

    @Override
    @Operation(summary = "Get all requests with pagination")
    public Page<Request> findAll(Pageable page) {
        return requestsRepository.findAll(page);
    }
}
