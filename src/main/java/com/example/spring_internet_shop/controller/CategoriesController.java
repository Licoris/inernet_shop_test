package com.example.spring_internet_shop.controller;

import com.example.spring_internet_shop.entity.Category;
import com.example.spring_internet_shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;


    @RequestMapping("/show-categories")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("allCategories", categories);
        return "all-categories";

    }

    @RequestMapping("/save-category")
    public String saveOrUpdateCategory(@Valid @ModelAttribute(name = "category") Category category,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category-info";
        }
        if (category.getId() != null) {
            categoryService.update(category.getId(), category);
            return "redirect:/show-categories";
        }
        categoryService.save(category);
        return "redirect:/show-categories";
    }

    @RequestMapping("/add-category")
    public String addNewCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category-info";
    }

    @RequestMapping("/delete-category")
    public String deleteCategory(@RequestParam(name = "id") Long id) {
        categoryService.delete(id);
        return "redirect:/show-categories";
    }

    @RequestMapping("/update-category")
    public String updateCategory(@RequestParam Long id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "category-info";
    }
}
