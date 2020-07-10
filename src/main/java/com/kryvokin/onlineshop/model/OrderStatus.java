package com.kryvokin.onlineshop.model;

public enum OrderStatus {

    NEW("NEW"), CANCELLED("CANCELLED"), CLOSED("CLOSED"), IN_PROGRESS("IN PROGRESS");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

}
