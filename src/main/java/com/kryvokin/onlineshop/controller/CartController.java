package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.Product;
import com.kryvokin.onlineshop.service.CartService;
import com.kryvokin.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class CartController {

    private CartService cartService;
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/add/product/{id}")
    public RedirectView addProductToCard(@PathVariable("id") int productId, @RequestParam(value = "amount",
            defaultValue = "1", required = false) int amount) {
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {
            cartService.addProductToCart(product.get(), amount);
        }
        return new RedirectView("/cart");
    }

    @GetMapping("/cart")
    @RequestMapping
    public String getCartView(Model model) {
        model.addAttribute("cart", cartService.getCart().getCart());
        return "cart";
    }

}
