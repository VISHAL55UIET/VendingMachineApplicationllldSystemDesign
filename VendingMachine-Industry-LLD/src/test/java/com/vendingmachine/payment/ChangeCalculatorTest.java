package com.vendingmachine.payment;

import com.vendingmachine.payment.service.ChangeCalculator;
import com.vendingmachine.domain.payment.MoneyDenomination;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ChangeCalculatorTest {
    @Test void calculatesChange() { assertEquals(500, new ChangeCalculator().calculateChange(2000, 1500)); }
    @Test void calculatesCoinsWithLimitedInventory() {
        var result = new ChangeCalculator().calculateCoins(700, Map.of(
            MoneyDenomination.FIVE_RUPEES, 1, MoneyDenomination.TWO_RUPEES, 1));
        assertEquals(1, result.get(MoneyDenomination.FIVE_RUPEES));
        assertEquals(1, result.get(MoneyDenomination.TWO_RUPEES));
    }
}
