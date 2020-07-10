package com.kryvokin.onlineshop.model;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name="users_id", nullable=true)
    private User user;
    @OneToMany(mappedBy = "order")
    private List<OrderHasItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderHasItem> getItems() {
        return items;
    }

    public void setItems(List<OrderHasItem> items) {
        this.items = items;
    }
}
