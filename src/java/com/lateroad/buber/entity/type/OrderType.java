package com.lateroad.buber.entity.type;

/**
 * Enum {@code OrderType} is the enumeration of order types.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public enum OrderType {
    DONE("done"),
    ACCEPTED("accepted"),
    UNDONE("undone"),
    CANCELLED("cancelled");

    private String type;

    /**
     * Public constructor for OrderType class.
     */
    OrderType(String type) {
        this.type = type;
    }

    /**
     * Standard toString method for OrderType class.
     */
    @Override
    public String toString() {
        return "OrderType{" +
                "type='" + type + '\'' +
                '}';
    }
}
