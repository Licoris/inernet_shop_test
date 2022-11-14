package com.example.spring_internet_shop.controller;

import com.example.spring_internet_shop.model.Category;
import com.example.spring_internet_shop.model.Product;
import com.example.spring_internet_shop.service.CategoryService;
import com.example.spring_internet_shop.service.ProductService;
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
public class ProductsController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @RequestMapping("/show-products")
    public String showAllProducts(Model model) {
        model.addAttribute("allProducts", productService.getAll());
        return "all-products";
    }

    @RequestMapping("/delete-product")
    public String deleteProduct(@RequestParam(value = "id") Long id) {
        productService.delete(id);
        return "redirect:/show-products";
    }

    @RequestMapping("/add-product")
    public String addNewProduct(Model model) {
        Product product = new Product();
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("product", product);
        model.addAttribute("allCategories", categories);
        return "product-info";
    }

    @RequestMapping("/save-product")
    public String saveProduct(@Valid @ModelAttribute(name = "product") Product product,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("allCategories", categories);
            return "/product-info";
        }

        if (product.getId() != null) {

            productService.update(product.getId(), product);
            return "redirect:/show-products";
        }

        productService.save(product);
        return "redirect:/show-products";
    }

    @RequestMapping("/update-product")
    public String updateProduct(@RequestParam("id") Long id, Model model) {
        Product product = productService.get(id);
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("product", product);
        model.addAttribute("allCategories", categories);
        return "product-info";
    }
}
