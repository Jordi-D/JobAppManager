package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.service.ICategoriesService;
import com.jobs.jobapp.service.IVacanciesService;
import com.jobs.jobapp.util.Utility;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacancies")
public class VacanciesController {

    private final String route;
    private final IVacanciesService vacanciesService;
    private final ICategoriesService categoriesService;

    @Autowired
    public VacanciesController(@Value("${jobs.route.images}") String route,
                               IVacanciesService vacanciesService,
                               ICategoriesService categoriesService) {
        this.route = route;
        this.vacanciesService = vacanciesService;
        this.categoriesService = categoriesService;
    }

    @Operation(summary = "Show all vacancies")
    @GetMapping("/index")
    public String showIndex(Model model) {
        List<Vacancy> vacancyList = vacanciesService.findAll();
        model.addAttribute("vacancies", vacancyList);
        return "vacancies/listVacancies";
    }

    @Operation(summary = "Show paginated list of vacancies")
    @GetMapping("/indexPaginate")
    public String showIndexPaginated(Model model, Pageable page) {
        Page<Vacancy> vacancyPage = vacanciesService.findAll(page);
        model.addAttribute("vacancies", vacancyPage);
        return "vacancies/listVacancies";
    }

    @Operation(summary = "Edit a vacancy by ID")
    @GetMapping("/edit/{id}")
    public String editVacancy(@PathVariable("id") int idVacancy, Model model) {
        Vacancy vacancy = vacanciesService.findById(idVacancy);
        model.addAttribute("vacancy", vacancy);
        return "vacancies/formVacancy";
    }

    @Operation(summary = "Show form to create a new vacancy")
    @GetMapping("/create")
    public String createVacancy(Vacancy vacancy, Model model) {
        return "vacancies/formVacancy";
    }

    @Operation(summary = "Save a new or updated vacancy")
    @PostMapping("/save")
    public String saveVacancy(@RequestParam("fileImg") MultipartFile multipartFile,
                              Vacancy vacancy, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Error occurred: " + error.getDefaultMessage()));
            return "vacancies/formVacancy";
        }

        if (!multipartFile.isEmpty()) {
            String nameImg = Utility.saveFile(multipartFile, route);
            if (nameImg != null) {
                vacancy.setImage(nameImg);
            }
        }

        vacanciesService.save(vacancy);
        attributes.addFlashAttribute("msg", "Record Saved");
        System.out.println("Vacancy: " + vacancy);
        return "redirect:/vacancies/indexPaginate";
    }

    @Operation(summary = "Delete a vacancy by ID")
    @GetMapping("/delete/{id}")
    public String deleteVacancy(@PathVariable("id") int idVacancy, RedirectAttributes attributes) {
        System.out.println("Deleting vacancy with id: " + idVacancy);
        vacanciesService.delete(idVacancy);
        attributes.addFlashAttribute("msg", "The vacancy has been deleted!");
        return "redirect:/vacancies/indexPaginate";
    }

    @Operation(summary = "View details of a vacancy by ID")
    @GetMapping("/view/{id}")
    public String viewDetail(@PathVariable("id") int idVacancy, Model model) {
        Vacancy vacancy = vacanciesService.findById(idVacancy);
        System.out.println("Vacancy: " + vacancy);
        model.addAttribute("vacancy", vacancy);
        return "detail";
    }

    @ModelAttribute
    public void setCategories(Model model) {
        model.addAttribute("categories", categoriesService.findAll());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
