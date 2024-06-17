package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Profile entities.
 * Extends JpaRepository which provides basic CRUD operations.
 */
public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
}
