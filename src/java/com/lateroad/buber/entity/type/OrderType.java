package com.lateroad.buber.entity.type;

public enum OrderType implements EntityType {
    DONE("done"),
    ACCEPTED("accepted"),
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
