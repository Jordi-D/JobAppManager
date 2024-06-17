package com.jobs.jobapp.service.db;

import com.jobs.jobapp.model.User;
import com.jobs.jobapp.repository.UsersRepository;
import com.jobs.jobapp.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Implementation of IUsersService that uses JPA for database operations on users.
 */
@Service
@Tag(name = "Users Service", description = "Service for managing users using JPA")
public class UsersServiceJpa implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Operation(summary = "Save a user")
    public void save(User user) {
        usersRepository.save(user);
    }

    @Override
    @Operation(summary = "Delete a user by ID")
    public void delete(Integer idUser) {
        usersRepository.deleteById(idUser);
    }

    @Override
    @Operation(summary = "Get all users")
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    @Operation(summary = "Find a user by username")
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

}
