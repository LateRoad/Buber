package com.lateroad.buber.entity;

import java.sql.Date;
import java.util.Objects;

public class Order extends Entity{
 private int id;
 private String clientLogin;
 private String driverLogin;
 private String money;
 private boolean isDone;
 private Date date;

    public Order() {
    }

    public Order(int id, String clientLogin, String driverLogin, String money, boolean isDone, Date date) {
        this.id = id;
        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.money = money;
        this.isDone = isDone;
        this.date = date;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                isDone == order.isDone &&
                Objects.equals(clientLogin, order.clientLogin) &&
                Objects.equals(driverLogin, order.driverLogin) &&
                Objects.equals(money, order.money) &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientLogin, driverLogin, money, isDone, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientLogin='" + clientLogin + '\'' +
                ", driverLogin='" + driverLogin + '\'' +
                ", money='" + money + '\'' +
                ", isDone=" + isDone +
                ", date=" + date +
                '}';
    }
}
