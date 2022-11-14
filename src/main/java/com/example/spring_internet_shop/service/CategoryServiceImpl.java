package com.example.spring_internet_shop.service;

import com.example.spring_internet_shop.model.Category;
import com.example.spring_internet_shop.exception.BadRequestException;
import com.example.spring_internet_shop.exception.ResourceNotFoundException;
import com.example.spring_internet_shop.repository.CategoryRepository;
import com.example.spring_internet_shop.service.enums.ErrorsMessageEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category get(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
    }

    @Override
    public void save(Category category) {
        if (category.getId() != null) {
            throw new BadRequestException(ErrorsMessageEnum.BAD_POST_ID_REQUEST.getMessage());
        }
        categoryRepository.save(category);
    }

    @Override
    public void update(Long id, Category receivedCategory) {
        var currentObj = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
        updateCategory(currentObj, receivedCategory);

        categoryRepository.save(currentObj);
    }

    private void updateCategory(Category curObj, Category recObj) {
        curObj.setCategoryName(recObj.getCategoryName());
        curObj.setDescription(recObj.getDescription());
    }

    @Override
    public void delete(Long id) {
        categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
        categoryRepository.deleteById(id);
    }
}
