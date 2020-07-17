package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.Order;
import com.kryvokin.onlineshop.model.OrderStatus;
import com.kryvokin.onlineshop.model.error.ProductSoldAmountExceededTotalAmount;
import com.kryvokin.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class OrderController {

    private OrderService orderService;
    private Exception orderNotExists = new Exception("Order doesn't not exist");

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public RedirectView createOrder(@CookieValue(value = "userEmail", required = false) String userEmail)
            throws ProductSoldAmountExceededTotalAmount {
        orderService.createOrder(userEmail);
        if (userEmail != null) {
            return new RedirectView("/user/main");
        }
        return new RedirectView("/main");
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public Order getOrderById(@PathVariable("id") int id) throws Exception {
        Optional<Order> order = orderService.getOrderById(id);
        return order.orElseThrow(() -> orderNotExists);
    }

    @PostMapping("/order/{id}")
    @ResponseBody
    public Order changeOrderStatus(@PathVariable("id") int id, @RequestParam("status") OrderStatus status)
            throws Exception {
        Optional<Order> orderOptional = orderService.getOrderById(id);
        Order order = orderOptional.orElseThrow(() -> orderNotExists);
        order.setStatus(status);
        return orderService.save(order);
    }

}
