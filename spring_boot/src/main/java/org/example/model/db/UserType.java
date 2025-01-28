package org.example.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;


public enum UserType {
    GUEST("GUEST", 0L),
    REGULAR("REGULAR", 500L),
    PREMIUM("PREMIUM", 1000L);

    String name;
    Long value;

    UserType(String name, Long value) {
        this.name = name;
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
