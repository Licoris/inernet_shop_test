package com.example.spring_internet_shop.rest_controller;


import com.example.spring_internet_shop.entity.Product;
import com.example.spring_internet_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsRestController {
    private final ProductService productService;


    @GetMapping
    public List<Product> showAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product showProduct(@PathVariable Long id) {
        return productService.get(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

}
