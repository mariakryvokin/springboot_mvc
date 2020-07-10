package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public String createOrder(@CookieValue(value = "userEmail") String userEmail){
        orderService.createOrder(userEmail);
        return "main";
    }

}
