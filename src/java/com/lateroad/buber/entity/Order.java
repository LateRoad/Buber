package com.lateroad.buber.entity;

import com.lateroad.buber.entity.type.OrderType;

import java.sql.Date;
import java.util.Objects;

/**
 * Class {@code Order} is an object with order data.
 *
 * @author LateRoad
 * @see Entity
 * @since JDK1.8
 */
public class Order implements Entity {
    private int id;
    private String clientLogin;
    private String driverLogin;
    private String money;
    private OrderType status;
    private Date date;
    private Location origin;
    private Location destination;


    /**
     * Public constructor for Client class.
     */
    public Order() {
    }

    /**
     * Public constructor for Client class.
     */
    public Order(int id, String clientLogin, String driverLogin, String money, OrderType status, Date date) {
        this.id = id;
        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.status = status;
        this.date = date;
    }

    /**
     * Public constructor for Client class.
     */
    public Order(String clientLogin, String driverLogin, String money) {
        long time = System.currentTimeMillis();

        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.date = new Date(time);
        this.status = OrderType.UNDONE;
    }

    /**
     * Public constructor for Client class.
     */
    public Order(String clientLogin, String driverLogin, String money, OrderType orderType) {
        long time = System.currentTimeMillis();

        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.date = new Date(time);
        this.status = orderType;
    }


    /**
     * Standard getter for id variable.
     */
    public int getId() {
        return id;
    }

    /**
     * Standard setter for id variable.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Standard getter for clientLogin variable.
     */
    public String getClientLogin() {
        return clientLogin;
    }

    /**
     * Standard setter for clientLogin variable.
     */
    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    /**
     * Standard getter for driverLogin variable.
     */
    public String getDriverLogin() {
        return driverLogin;
    }

    /**
     * Standard setter for driverLogin variable.
     */
    public void setDriverLogin(String driverLogin) {
        this.driverLogin = driverLogin;
    }

    /**
     * Standard getter for money variable.
     */
    public String getMoney() {
        return money;
    }

    /**
     * Standard setter for money variable.
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * Standard getter for status variable.
     */
    public OrderType getStatus() {
        return status;
    }

    /**
     * Standard setter for status variable.
     */
    public void setStatus(OrderType status) {
        this.status = status;
    }

    /**
     * Standard getter for date variable.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Standard setter for date variable.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Standard getter for origin variable.
     */
    public Location getOrigin() {
        return origin;
    }

    /**
     * Standard setter for origin variable.
     */
    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    /**
     * Standard getter for destination variable.
     */
    public Location getDestination() {
        return destination;
    }

    /**
     * Standard setter for destination variable.
     */
    public void setDestination(Location destination) {
        this.destination = destination;
    }


    /**
     * Standard equals method for Order class.
     */
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

    /**
     * Standard hashCode method for Order class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, clientLogin, driverLogin, money, status, date, origin, destination);
    }

    /**
     * Standard toString method for Order class.
     */
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
