package com.lateroad.buber.model;

import com.lateroad.buber.entity.Location;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.CommonUser;

import java.util.List;
import java.util.Objects;

public class CurrentModel {
    private CommonUser currentUser;
    private Location location;
    private Order currentOrder;
    private List<Order> orders;

    public CurrentModel(CommonUser commonUser) {
        this.currentUser = commonUser;
    }

    public CurrentModel(CommonUser currentUser, Location location, Order currentOrder, List<Order> orders) {
        this.currentUser = currentUser;
        this.location = location;
        this.currentOrder = currentOrder;
        this.orders = orders;
    }

    public CommonUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CommonUser currentUser) {
        this.currentUser = currentUser;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentModel that = (CurrentModel) o;
        return Objects.equals(currentUser, that.currentUser) &&
                Objects.equals(location, that.location) &&
                Objects.equals(currentOrder, that.currentOrder) &&
                Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentUser, location, currentOrder, orders);
    }

    @Override
    public String toString() {
        return "CurrentModel{" +
                "currentUser=" + currentUser +
                ", location=" + location +
                ", currentOrder=" + currentOrder +
                ", orders=" + orders +
                '}';
    }
}
