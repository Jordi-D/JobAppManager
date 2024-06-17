package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.User;
import com.jobs.jobapp.service.IUsersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final PasswordEncoder passwordEncoder;
    private final IUsersService usersService;

    @Autowired
    public UsersController(PasswordEncoder passwordEncoder, IUsersService usersService) {
        this.passwordEncoder = passwordEncoder;
        this.usersService = usersService;
    }

    @Operation(summary = "Show list of users")
    @GetMapping("/index")
    public String showIndex(Model model) {
        List<User> userList = usersService.findAll();
        model.addAttribute("users", userList);
        return "users/listUsers";
    }

    @Operation(summary = "Delete a user by ID")
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int userId, RedirectAttributes attributes) {
        usersService.delete(userId);
        attributes.addFlashAttribute("msg", "The user has been deleted.");
        return "redirect:/users/index";
    }
}
