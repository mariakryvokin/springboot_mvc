package com.kryvokin.onlineshop.repository;

import com.kryvokin.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Product findByIdWithLock(@Param("productId") int id);

}
