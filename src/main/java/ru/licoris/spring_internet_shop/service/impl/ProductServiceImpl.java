package ru.licoris.spring_internet_shop.service.impl;

import ru.licoris.spring_internet_shop.model.Category;
import ru.licoris.spring_internet_shop.model.Product;
import ru.licoris.spring_internet_shop.exception.BadRequestException;
import ru.licoris.spring_internet_shop.exception.ResourceNotFoundException;
import ru.licoris.spring_internet_shop.repository.ProductRepository;
import ru.licoris.spring_internet_shop.service.CategoryService;
import ru.licoris.spring_internet_shop.service.ProductService;
import ru.licoris.spring_internet_shop.service.enums.ErrorsMessageEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product get(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
    }

    @Override
    public void save(Product product) {
        if (product.getId() != null) {
            throw new BadRequestException(ErrorsMessageEnum.BAD_POST_ID_REQUEST.getMessage());
        }

        productRepository.save(product);
    }

    @Override
    public void update(Long id, Product receivedProduct) {
        var currentProduct = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category doesn't exist"));

        var receivedCategory = categoryService.get(receivedProduct.getCategory().getId());

        updateProduct(currentProduct, receivedProduct, receivedCategory);
        productRepository.save(currentProduct);
    }

    private void updateProduct(Product curProd, Product recProduct, Category recCategory) {
        curProd.setProductName(recProduct.getProductName());
        curProd.setUnitPrice(recProduct.getUnitPrice());
        curProd.setUnitsInStock(recProduct.getUnitsInStock());
        curProd.setCategory(recCategory);
    }

    @Override
    public void delete(Long id) {
        productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));

        productRepository.deleteById(id);
    }
}
