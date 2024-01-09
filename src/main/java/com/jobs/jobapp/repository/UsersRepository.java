package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}

