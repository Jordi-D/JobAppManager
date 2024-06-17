package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 * Extends JpaRepository which provides basic CRUD operations.
 */
public interface UsersRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object associated with the given username.
     */
    User findByUsername(String username);
}
