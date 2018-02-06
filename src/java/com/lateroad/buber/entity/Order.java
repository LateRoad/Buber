package com.lateroad.buber.entity;

import com.lateroad.buber.entity.type.OrderType;

import java.sql.Date;
import java.util.Objects;

public class Order implements Entity {
    private int id;
    private String clientLogin;
    private String driverLogin;
    private String money;
    private OrderType status;
    private Date date;
    private Location origin;
    private Location destination;

    public Order() {
    }

    public Order(int id, String clientLogin, String driverLogin, String money, OrderType status, Date date) {
        this.id = id;
        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.status = status;
        this.date = date;
    }

    public Order(String clientLogin, String driverLogin, String money) {
        long time = System.currentTimeMillis();

        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.date = new Date(time);
        this.status = OrderType.UNDONE;
    }

    public Order(String clientLogin, String driverLogin, String money, OrderType orderType) {
        long time = System.currentTimeMillis();

        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.date = new Date(time);
        this.status = orderType;
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

    public OrderType getStatus() {
        return status;
    }

    public void setStatus(OrderType status) {
        this.status = status;
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
                status == order.status &&
                Objects.equals(date, order.date) &&
                Objects.equals(origin, order.origin) &&
                Objects.equals(destination, order.destination);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, clientLogin, driverLogin, money, status, date, origin, destination);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientLogin='" + clientLogin + '\'' +
                ", driverLogin='" + driverLogin + '\'' +
                ", money='" + money + '\'' +
                ", orderType=" + status +
                ", date=" + date +
                ", origin=" + origin +
                ", destination=" + destination +
                '}';
    }
}
