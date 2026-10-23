package com.vendingmachine.domain.payment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test void addsMoneyExactly() { assertEquals(350L, new Money(100).plus(new Money(250)).paise()); }
    @Test void rejectsNegativeMoney() { assertThrows(IllegalArgumentException.class, () -> new Money(-1)); }
    @Test void preventsNegativeResult() { assertThrows(IllegalArgumentException.class, () -> new Money(100).minus(new Money(101))); }
}
