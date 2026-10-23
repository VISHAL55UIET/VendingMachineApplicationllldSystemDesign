package com.vendingmachine.infrastructure.event;

public interface DomainEventPublisher {
    void publish(Object event);
}
