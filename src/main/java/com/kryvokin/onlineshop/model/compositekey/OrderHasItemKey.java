package com.kryvokin.onlineshop.model.compositekey;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
public class OrderHasItemKey implements Serializable {

    @Column(name = "orders_id")
    private int orderId;
    @Column(name = "item_id")
    private int itemId;

    public OrderHasItemKey() {
    }

    public OrderHasItemKey(int orderId, int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHasItemKey)) return false;
        OrderHasItemKey that = (OrderHasItemKey) o;
        return orderId == that.orderId &&
                itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId);
    }
}
