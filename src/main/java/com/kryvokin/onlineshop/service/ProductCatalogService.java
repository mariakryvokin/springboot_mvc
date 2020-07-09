package com.kryvokin.onlineshop.service;

import com.kryvokin.onlineshop.model.Product;
import com.kryvokin.onlineshop.repository.ProductCatalogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogService {

    private ProductCatalogRepository productCatalogRepository;

    public ProductCatalogService(ProductCatalogRepository productCatalogRepository) {
        this.productCatalogRepository = productCatalogRepository;
    }

    public Page<Product> findAll(int pageSize, int currentPage){
        Pageable page = PageRequest.of(currentPage, pageSize);
        return productCatalogRepository.findAll(page);
    }
}
