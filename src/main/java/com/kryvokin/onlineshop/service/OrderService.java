package com.kryvokin.onlineshop.service;

import com.kryvokin.onlineshop.model.Order;
import com.kryvokin.onlineshop.model.OrderHasItem;
import com.kryvokin.onlineshop.model.OrderStatus;
import com.kryvokin.onlineshop.model.Product;
import com.kryvokin.onlineshop.model.User;
import com.kryvokin.onlineshop.model.compositekey.OrderHasItemKey;
import com.kryvokin.onlineshop.repository.OrderRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private UserService userService;
    private ProductService productService;
    private OrderHasItemService orderHasItemService;

    public OrderService(OrderRepository orderRepository, CartService cartService, UserService userService,
                        ProductService productService, OrderHasItemService orderHasItemService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderHasItemService = orderHasItemService;
    }

    @Transactional
    public Order createOrder(@Nullable String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        Order order = Order.builder().user(user).status(OrderStatus.NEW).price(calculateOrderPrice()).build();
        orderRepository.save(order);
        List<OrderHasItem> orderHasItem = createOrderHasItem(order);
        orderHasItemService.saveAll(orderHasItem);
        updateProductAmount();
        return order;
    }

    @Transactional
    private void updateProductAmount(){
        cartService.getCart().getCart().entrySet().stream().forEach(productIntegerEntry -> {
            Product product = productIntegerEntry.getKey();
            product.setSoldAmount(product.getSoldAmount() + productIntegerEntry.getValue());
            productService.save(product);
        });
    }

    @Transactional
    private List<OrderHasItem> createOrderHasItem(Order order) {
        return cartService.getCart().getCart().entrySet().stream().map(productIntegerEntry -> {
            return OrderHasItem.builder()
                    .id(getOrderHasItemPrimaryKey(order, productIntegerEntry))
                    /*.order(order)
                    .item(productIntegerEntry.getKey())*/
                    .amount(productIntegerEntry.getValue())
                    .build();
        }).collect(Collectors.toList());
    }

    private OrderHasItemKey getOrderHasItemPrimaryKey(Order order, Map.Entry<Product, Integer> productIntegerEntry) {
        return OrderHasItemKey.builder()
                .itemId(productIntegerEntry.getKey().getId())
                .orderId(order.getId())
                .build();
    }

    private double calculateOrderPrice() {
        return cartService.getCart().getCart().entrySet().stream()
                .mapToDouble(productIntegerEntry ->
                        productIntegerEntry.getKey().getPrice() * productIntegerEntry.getValue())
                .sum();
    }

    @Transactional
    public void submitOrder(Order order) {
        orderRepository.save(order);
    }
}