package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.service.ICategoriesService;
import com.jobs.jobapp.service.IVacanciesService;
import com.jobs.jobapp.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
    @Value("${jobs.route.images}")
    private String route;
    @Autowired
    private IVacanciesService vacanciesService;
    @Autowired
    private ICategoriesService categoriesService;


    @GetMapping("/index")
    public String showIndex(Model model) {
        List<Vacancy> list = vacanciesService.findAll();
        model.addAttribute("vacancies", list);
        return "vacancies/listVacancies";
    }

    @GetMapping(value = "/indexPaginate")
    public String showIndexPaginated(Model model, Pageable page) {
        Page<Vacancy> list = vacanciesService.findAll(page);
        model.addAttribute("vacancies", list);
        return "vacancies/listVacancies";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int idVacancy, Model model) {
        Vacancy vacancy = vacanciesService.findById(idVacancy);
        model.addAttribute("vacancy", vacancy);
        return "vacancies/formVacancy";

    }

    @GetMapping("/create")
    public String create(Vacancy vacancy, Model model) {
        return "vacancies/formVacancy";
    }

    @PostMapping("/save")
    public String save(@RequestParam("fileImg") MultipartFile multiPart,
                          Vacancy vacancy, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "vacancies/formVacancy";
        }
        if (!multiPart.isEmpty()) {
/*            String ruta = "img-vacantes/"; // Ruta relativa a la carpeta "empleos"
            String directorioActual = System.getProperty("user.dir");
            String rutaCompleta = directorioActual + "/" + ruta;*/

            String nameImg = Utility.saveFile(multiPart, route);

            if (nameImg != null) {
                vacancy.setImage(nameImg);
            }
        }


        vacanciesService.save(vacancy);
        attributes.addFlashAttribute("msg", "Registro Guardado");
        System.out.println("Vacante : " + vacancy);
        return "redirect:/vacancies/indexPaginate";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int idVacancy, RedirectAttributes attributes) {
        System.out.println("Borrando vacante con id: " + idVacancy);
        vacanciesService.delete(idVacancy);
        attributes.addFlashAttribute("msg", "La vacante fue eliminada!");
        return "redirect:/vacancies/indexPaginate";
    }

    @GetMapping("/view/{id}")
    public String viewDetail(@PathVariable("id") int idVacancy, Model model) {

        Vacancy vacancy = vacanciesService.findById(idVacancy);

        System.out.println("Vacante: " + vacancy);
        vacancy.getDescription();
        model.addAttribute("vacancy", vacancy);

        // Buscar los detalles de la vacante en la BD...
        return "detail";
    }

    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("categories", categoriesService.findAll());
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
