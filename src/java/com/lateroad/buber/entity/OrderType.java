package com.lateroad.buber.entity;

public enum OrderType {
    DONE("done"),
    UNDONE("undone"),
    CANCELLED("cancelled");

    private String type;

    OrderType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "type='" + type + '\'' +
                '}';
    }
}
