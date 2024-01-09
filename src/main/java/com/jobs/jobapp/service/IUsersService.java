package com.jobs.jobapp.service;

import com.jobs.jobapp.model.User;

import java.util.List;

public interface IUsersService {
    void save(User user);

    void delete(Integer idUser);

    List<User> findAll();
    User findByUsername(String username);
}
