package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.User;
import com.jobs.jobapp.service.IUsersService;
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

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUsersService usersService;

    @GetMapping("/index")
    public String showIndex(Model model) {
        List<User> list = usersService.findAll();
        model.addAttribute("users", list);
        return "users/listUsers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int idUser, RedirectAttributes attributes) {
        // Eliminamos el usuario
        usersService.delete(idUser);
        attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
        return "redirect:/users/index";
    }
}
