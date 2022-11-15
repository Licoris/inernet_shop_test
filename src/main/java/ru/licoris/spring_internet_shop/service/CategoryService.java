package ru.licoris.spring_internet_shop.service;

import ru.licoris.spring_internet_shop.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category get(Long id);

    void save(Category category);

    void delete(Long id);

    void update(Long id, Category category);
}
