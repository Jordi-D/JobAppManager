package com.jobs.jobapp.controller;


import com.jobs.jobapp.model.Category;
import com.jobs.jobapp.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private ICategoriesService categoriesService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(Model model) {
        List<Category> list = categoriesService.findAll();
        model.addAttribute("categories", list);
        return "categories/listCategories";
    }

    @GetMapping(value = "/indexPaginate")
    public String showPaginatedIndex(Model model, Pageable page) {
        Page<Category> list = categoriesService.findAll(page);
        model.addAttribute("categories", list);
        return "categories/listCategories";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Category category) {
        return "categories/formCategory";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Category category, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            System.out.println("Existieron errores");
            return "categories/formCategory";
        }

        // Guadamos el objeto categoria en la bd
        categoriesService.save(category);
        attributes.addFlashAttribute("msg", "The category data has been saved!");
        return "redirect:/categories/indexPaginate";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int idCategoria, Model model) {
        Category category = categoriesService.findById(idCategoria);
        model.addAttribute("categories", category);
        return "categories/formCategory";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
        categoriesService.delete(idCategoria);
        attributes.addFlashAttribute("msg", "The category has been deleted!");
        return "redirect:/categories/indexPaginate";
    }

}
