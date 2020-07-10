package com.kryvokin.onlineshop.model;

import com.kryvokin.onlineshop.model.compositekey.OrderHasItemKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "orders_has_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHasItem {

    @EmbeddedId
    private OrderHasItemKey id;
    @ManyToOne
    @MapsId("orders_id")
    @JoinColumn(name = "orders_id")
    private Order order;
    @ManyToOne
    @MapsId("item_id")
    @JoinColumn(name = "item_id")
    private Product item;
    private int amount;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
