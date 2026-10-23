# Vending Machine — Industry-Oriented Java LLD

A runnable Java 17 + Maven reference implementation demonstrating domain modeling, State and Strategy patterns, repository abstractions, purchase orchestration, idempotency, inventory locking, dispensing abstraction, refunds, maintenance, and unit tests.

## Run

```bash
mvn test
mvn package
java -cp target/classes com.vendingmachine.VendingMachineApplication
```

If Maven is not installed, open the project in IntelliJ IDEA and allow it to import `pom.xml`. Run `VendingMachineApplication`.

## Example flow

The demo inserts cash, selects a product, purchases it, and prints the result. Cash amounts are represented as integer paise (never floating-point currency).

## Architecture notes

- `domain`: core entities/value objects, transaction state, inventory and domain exceptions.
- `state`: state pattern for valid machine operations.
- `application`: use-case orchestration and commands/results.
- `payment`: strategy-based cash/card payment abstractions, change calculation, refunds.
- `dispensing`: device abstraction and failure result.
- `repository`: persistence ports; `infrastructure.persistence`: in-memory adapters.
- `infrastructure.hardware`: mock hardware adapter.
- `admin`: restock, maintenance and monitoring operations.
- `api`: framework-neutral controller/DTO boundary. This is a core Java LLD project, not a Spring Boot HTTP server.
- `common`: ID and clock abstractions.

## Important production considerations

This is an interview-ready, executable reference—not certified vending-machine firmware. Real card/UPI integrations need provider-specific authorization, idempotency, webhook verification and reconciliation. A production deployment should use durable transactional storage, observability, secrets management, hardware safety controls, and integration tests. The in-memory repositories are intentionally replaceable adapters.

## Tests

Tests cover money arithmetic, inventory stock safety, state transitions, purchase success/failure, cancellation/refund, change calculation, and dispensing failures.
