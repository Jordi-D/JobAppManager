package com.jobs.jobapp.service.db;

import com.jobs.jobapp.model.User;
import com.jobs.jobapp.repository.UsersRepository;
import com.jobs.jobapp.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceJpa implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void save(User user) {
        usersRepository.save(user);
    }

    public void delete(Integer idUser) {
        usersRepository.deleteById(idUser);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

}
