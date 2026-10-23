package com.vendingmachine.infrastructure.event;

public class LoggingDomainEventPublisher implements DomainEventPublisher {
    @Override public void publish(Object event) { System.out.println("[DOMAIN EVENT] " + event); }
}
