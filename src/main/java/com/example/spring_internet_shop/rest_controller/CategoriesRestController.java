package com.example.spring_internet_shop.rest_controller;

import com.example.spring_internet_shop.model.Category;
import com.example.spring_internet_shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesRestController {
    private final CategoryService categoryService;


    @GetMapping
    public List<Category> showAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category showCategory(@PathVariable Long id) {
        return categoryService.get(id);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        categoryService.save(category);
        return category;
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable(name = "id") Long categoryId, @RequestBody Category category) {
        categoryService.update(categoryId, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
