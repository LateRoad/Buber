package com.lateroad.buber.entity;

import java.sql.Date;
import java.util.Objects;

public class Order extends Entity {
    private int id;
    private String clientLogin;
    private String driverLogin;
    private String money;
    private OrderType orderType;
    private Date date;
    private Location origin;
    private Location destination;

    public Order() {
    }

    public Order(int id, String clientLogin, String driverLogin, String money, OrderType orderType, Date date) {
        this.id = id;
        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.orderType = orderType;
        this.date = date;
    }

    public Order(String clientLogin, String driverLogin, String money) {
        long time = System.currentTimeMillis();

        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.date = new Date(time);
        this.orderType = OrderType.UNDONE;
    }

    public Order(String clientLogin, String driverLogin, String money, OrderType orderType) {
        long time = System.currentTimeMillis();

        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.date = new Date(time);
        this.orderType = orderType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public String getDriverLogin() {
        return driverLogin;
    }

    public void setDriverLogin(String driverLogin) {
        this.driverLogin = driverLogin;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(clientLogin, order.clientLogin) &&
                Objects.equals(driverLogin, order.driverLogin) &&
                Objects.equals(money, order.money) &&
                orderType == order.orderType &&
                Objects.equals(date, order.date) &&
                Objects.equals(origin, order.origin) &&
                Objects.equals(destination, order.destination);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, clientLogin, driverLogin, money, orderType, date, origin, destination);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientLogin='" + clientLogin + '\'' +
                ", driverLogin='" + driverLogin + '\'' +
                ", money='" + money + '\'' +
                ", orderType=" + orderType +
                ", date=" + date +
                ", origin=" + origin +
                ", destination=" + destination +
                '}';
    }
}
