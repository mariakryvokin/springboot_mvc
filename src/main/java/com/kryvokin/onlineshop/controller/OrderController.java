package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.error.ProductSoldAmountExceededTotalAmount;
import com.kryvokin.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public RedirectView createOrder(@CookieValue(value = "userEmail", required = false) String userEmail)
            throws ProductSoldAmountExceededTotalAmount {
        orderService.createOrder(userEmail);
        if(userEmail != null){
            return new RedirectView("/user/main");
        }
        return new RedirectView("/main");
    }

}
