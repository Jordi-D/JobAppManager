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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController {

    private final ICategoriesService categoriesService;

    @Autowired
    public CategoriesController(ICategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/index")
    public String showAllCategories(Model model) {
        List<Category> categories = categoriesService.findAll();
        model.addAttribute("categories", categories);
        return "categories/listCategories";
    }

    @GetMapping("/indexPaginate")
    public String showPaginatedCategories(Model model, Pageable pageable) {
        Page<Category> categories = categoriesService.findAll(pageable);
        model.addAttribute("categories", categories);
        return "categories/listCategories";
    }

    @GetMapping("/create")
    public String showCreateCategoryForm(Category category) {
        return "categories/formCategory";
    }

    @PostMapping("/save")
    public String saveCategory(Category category, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            System.out.println("ERROR :");
            return "categories/formCategory";
        }

        categoriesService.save(category);
        attributes.addFlashAttribute("msg", "The category data has been saved!");
        return "redirect:/categories/indexPaginate";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") int categoryId, Model model) {
        Category category = categoriesService.findById(categoryId);
        model.addAttribute("category", category);
        return "categories/formCategory";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int categoryId, RedirectAttributes attributes) {
        categoriesService.delete(categoryId);
        attributes.addFlashAttribute("msg", "The category has been deleted!");
        return "redirect:/categories/indexPaginate";
    }
}
