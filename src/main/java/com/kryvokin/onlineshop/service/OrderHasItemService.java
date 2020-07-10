package com.kryvokin.onlineshop.service;

import com.kryvokin.onlineshop.model.OrderHasItem;
import com.kryvokin.onlineshop.repository.OrderHasItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHasItemService {

    private OrderHasItemRepository orderHasItemRepository;

    public OrderHasItemService(OrderHasItemRepository orderHasItemRepository) {
        this.orderHasItemRepository = orderHasItemRepository;
    }

    public List<OrderHasItem> saveAll(Iterable<OrderHasItem> orderHasItem){
        return orderHasItemRepository.saveAll(orderHasItem);
    }
}
