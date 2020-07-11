package com.kryvokin.onlineshop.model.error;

public class ProductSoldAmountExceededTotalAmount extends Exception {

    public ProductSoldAmountExceededTotalAmount(String message) {
        super(message);
    }
}
