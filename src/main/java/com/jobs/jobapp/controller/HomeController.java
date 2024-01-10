package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.Profile;
import com.jobs.jobapp.model.User;
import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.service.ICategoriesService;
import com.jobs.jobapp.service.IUsersService;
import com.jobs.jobapp.service.IVacanciesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    private final IVacanciesService vacanciesService;
    private final ICategoriesService categoriesService;
    private final IUsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(IVacanciesService vacanciesService, ICategoriesService categoriesService,
                          IUsersService usersService, PasswordEncoder passwordEncoder) {
        this.vacanciesService = vacanciesService;
        this.categoriesService = categoriesService;
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showHome(Model model) {
        return "home";
    }

    @GetMapping("/signup")
    public String register(User user) {
        return "formRegister";
    }

    @PostMapping("/signup")
    public String saveRecord(User user, RedirectAttributes attributes) {
        String pwdPlain = user.getPassword();
        String pwdEncrypted = passwordEncoder.encode(pwdPlain);

        user.setStatus(1);
        user.setRegistrationDate(new Date());
        user.setPassword(pwdEncrypted);

        Profile profile = new Profile();
        profile.setId(3);
        user.add(profile);

        usersService.save(user);
        attributes.addFlashAttribute("msg", "The record was saved successfully!");

        return "redirect:/users/index";
    }

    @GetMapping("/table")
    public String showTable(Model model) {
        List<Vacancy> list = vacanciesService.findAll();
        model.addAttribute("vacancies", list);
        return "table";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("search") Vacancy vacancy, Model model) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("salary");
        Example<Vacancy> example = Example.of(vacancy, matcher);
        List<Vacancy> list = vacanciesService.findByExample(example);
        model.addAttribute("vacancies", list);
        return "home";
    }

    @GetMapping("/index")
    public String showIndex(Authentication auth, HttpSession session) {
        String username = auth.getName();
        System.out.println("Nombre user : " + username);

        if (session.getAttribute("user") == null) {
            User user = usersService.findByUsername(username);
            user.setPassword(null);
            System.out.println("usuario = " + user);
            session.setAttribute("usuario", user);
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        return "redirect:/login";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public void setGenerics(Model model) {
        Vacancy vacancySearch = new Vacancy();
        vacancySearch.reset();
        model.addAttribute("vacancies", vacanciesService.findHighlighted());
        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("search", vacancySearch);
    }
}

