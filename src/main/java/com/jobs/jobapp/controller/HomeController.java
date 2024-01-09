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
    @Autowired
    private IVacanciesService vacanciesService;
    @Autowired
    private ICategoriesService categoriesService;


    @Autowired
    private IUsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        String pwdPlane = user.getPassword();
        String pwdEncripted = passwordEncoder.encode(pwdPlane);

        user.setStatus(1); // By default 1
        user.setRegistrationDate(new Date());
        user.setPassword(pwdEncripted);
        Profile profile = new Profile();
        profile.setId(3); // Perfil USUARIO by default
        user.add(profile);

        /**
         *Save the user in the database. The profile is saved automatically.
         */
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

    @GetMapping("/detail")
    public String showDetail(Model model) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName("Ingeniero de comunicaciones");
        vacancy.setDescription("Se solicita ingeniero para dar soporte a intranet");
        vacancy.setDate(new Date());
        vacancy.setSalary(9700.0);
        model.addAttribute("vacancy", vacancy);
        return "detail";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<String> list = new LinkedList<String>();
        list.add("Ingeniero  de Sistemas");
        list.add("Auxiliar de Contabilidad");
        list.add("Vendedor");
        list.add("Arquitecto");

        model.addAttribute("vacancies", list);

        return "list";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("search") Vacancy vacancy, Model model) {
        System.out.println("Buscando " + vacancy);

        //Filtros
        // where descripcion like '?'
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

        for (GrantedAuthority rol : auth.getAuthorities()) {
            System.out.println("rol: " + rol.getAuthority());
        }

        if (session.getAttribute("user") == null) {
            User user = usersService.findByUsername(username);
            user.setPassword(null);
            System.out.println("usuario = " + user);
            session.setAttribute("usuario", user);
            return "redirect:/";
        }
        return "redirect:/";

    }

/*    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String ecriptar(@PathVariable("texto") String texto) {
        return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);

    }*/

    @GetMapping("/login")
    public String showLogin() {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }


    @InitBinder
    public void initBinde(WebDataBinder binder) {
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
