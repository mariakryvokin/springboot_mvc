package com.kryvokin.onlineshop.repository;

import com.kryvokin.onlineshop.model.OrderHasItem;
import com.kryvokin.onlineshop.model.compositekey.OrderHasItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHasItemRepository extends JpaRepository<OrderHasItem, OrderHasItemKey> {

}
