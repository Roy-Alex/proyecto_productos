package com.example.crud_productos.controller;

import com.example.crud_productos.model.Product;
import com.example.crud_productos.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // Listar
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", repository.findAll());
        return "products-list";
    }

    // Form nuevo
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // Guardar
    @PostMapping("/save")
    public String save(@ModelAttribute Product product) {
        repository.save(product);
        return "redirect:/products";
    }

    // Eliminar
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/products";
    }
}
