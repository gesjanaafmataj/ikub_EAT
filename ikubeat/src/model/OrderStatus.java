package model;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum OrderStatus {
    CREATED("CREATED"),
    APPROVED("APPROVED"),
    PREPARED("PREPARED"),
    WAITING_FOR_DELIVERY("WAITING_FOR_DELIVERY"),
    DELIVERED("DELIVERED"),
    REJECTED("REJECTED");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus findStatus(String value) {
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.getValue().equals(value))
                .collect(Collectors.toList())
                .get(0);
    }
}
