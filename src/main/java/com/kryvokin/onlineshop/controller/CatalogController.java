package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.Product;
import com.kryvokin.onlineshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CatalogController {

    private ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String listBooks(Model model, @RequestParam(value = "page", defaultValue = "1") int currentPage,
                            @RequestParam(value = "size", defaultValue = "1") int size) {
        Page<Product> productsPage = productService.findAll(size, currentPage - 1);
        model.addAttribute("productsPage", productsPage);
        int totalPages = productsPage.getTotalPages();
        List<Integer> pageNumbers = new ArrayList<>();
        if (totalPages > 0) {
            pageNumbers = getAllPagesNumbers(totalPages);
        }
        model.addAttribute("pageNumbers", pageNumbers);
        return "catalog";
    }

    private List<Integer> getAllPagesNumbers(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
               .boxed()
               .collect(Collectors.toList());
    }

    @DeleteMapping("/catalog/item/delete/{id}")
    @ResponseBody
    public boolean deleteProduct(@PathVariable("id") int id){
        return productService.deleteProductById(id);
    }
}
