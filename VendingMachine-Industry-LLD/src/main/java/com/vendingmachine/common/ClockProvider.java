package com.vendingmachine.common;

import java.time.Instant;

public class ClockProvider {
    public Instant now() {
        return Instant.now();
    }
}
