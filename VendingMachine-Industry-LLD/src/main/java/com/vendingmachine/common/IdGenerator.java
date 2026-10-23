package com.vendingmachine.common;

import java.util.UUID;

public class IdGenerator {
    public String newId() {
        return UUID.randomUUID().toString();
    }
}
