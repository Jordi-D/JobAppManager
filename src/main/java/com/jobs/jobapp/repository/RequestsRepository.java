package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsRepository extends JpaRepository<Request, Integer> {
}
