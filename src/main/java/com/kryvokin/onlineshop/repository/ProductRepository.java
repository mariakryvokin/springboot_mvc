package com.kryvokin.onlineshop.repository;

import com.kryvokin.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

}
