package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Request entities.
 * Extends JpaRepository which provides basic CRUD operations.
 */
public interface RequestsRepository extends JpaRepository<Request, Integer> {
}
