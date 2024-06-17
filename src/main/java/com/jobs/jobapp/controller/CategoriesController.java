package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.Category;
import com.jobs.jobapp.service.ICategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Show all categories")
    @GetMapping("/index")
    public String showAllCategories(Model model) {
        List<Category> categories = categoriesService.findAll();
        model.addAttribute("categories", categories);
        return "categories/listCategories";
    }

    @Operation(summary = "Show paginated categories")
    @GetMapping("/indexPaginate")
    public String showPaginatedCategories(Model model, Pageable pageable) {
        Page<Category> categories = categoriesService.findAll(pageable);
        model.addAttribute("categories", categories);
        return "categories/listCategories";
    }

    @Operation(summary = "Show form to create a new category")
    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/formCategory";
    }

    @Operation(summary = "Save a new category")
    @PostMapping("/save")
    public String saveCategory(@Valid Category category, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "categories/formCategory";
        }

        categoriesService.save(category);
        attributes.addFlashAttribute("msg", "The category data has been saved!");
        return "redirect:/categories/indexPaginate";
    }

    @Operation(summary = "Show form to edit an existing category")
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") @Parameter(description = "Category ID") int categoryId, Model model) {
        Category category = categoriesService.findById(categoryId);
        model.addAttribute("category", category);
        return "categories/formCategory";
    }

    @Operation(summary = "Delete an existing category")
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") @Parameter(description = "Category ID") int categoryId, RedirectAttributes attributes) {
        categoriesService.delete(categoryId);
        attributes.addFlashAttribute("msg", "The category has been deleted!");
        return "redirect:/categories/indexPaginate";
    }
}
