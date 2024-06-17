package com.jobs.jobapp.service;

import com.jobs.jobapp.model.User;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface IUsersService {

    /**
     * Saves a new user.
     *
     * @param user The user to save.
     */
    void save(User user);

    /**
     * Deletes a user by its ID.
     *
     * @param idUser The ID of the user to delete.
     */
    void delete(Integer idUser);

    /**
     * Retrieves all users.
     *
     * @return List of all users.
     */
    List<User> findAll();

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The found user, or null if not found.
     */
    User findByUsername(String username);
}
