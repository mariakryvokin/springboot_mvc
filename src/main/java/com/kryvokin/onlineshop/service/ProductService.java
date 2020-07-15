package com.kryvokin.onlineshop.service;

import com.kryvokin.onlineshop.model.Product;
import com.kryvokin.onlineshop.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(int pageSize, int currentPage) {
        Pageable page = PageRequest.of(currentPage, pageSize);
        return productRepository.findAll(page);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Product findByIdWithLock(int id) {
        return productRepository.findByIdWithLock(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
