package com.jobs.jobapp.service;

import com.jobs.jobapp.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing requests.
 */
public interface IRequestsService {

    /**
     * Saves a new request.
     *
     * @param request The request to save.
     */
    void save(Request request);

    /**
     * Deletes a request by its ID.
     *
     * @param idRequest The ID of the request to delete.
     */
    void delete(Integer idRequest);

    /**
     * Retrieves all requests.
     *
     * @return List of all requests.
     */
    List<Request> findAll();

    /**
     * Finds a request by its ID.
     *
     * @param idRequest The ID of the request to find.
     * @return The found request, or null if not found.
     */
    Request findById(Integer idRequest);

    /**
     * Retrieves a page of requests.
     *
     * @param page The pagination information.
     * @return Page of requests.
     */
    Page<Request> findAll(Pageable page);
}
