package com.vendingmachine.payment.service;

import com.vendingmachine.domain.payment.MoneyDenomination;
import java.util.*;

public class ChangeCalculator {
    public long calculateChange(long tenderedPaise, long pricePaise) {
        if (tenderedPaise < pricePaise) throw new IllegalArgumentException("Tendered amount is less than price");
        return tenderedPaise - pricePaise;
    }
    public Map<MoneyDenomination, Integer> calculateCoins(long changePaise, Map<MoneyDenomination, Integer> available) {
        if (changePaise < 0) throw new IllegalArgumentException("Change cannot be negative");
        long remaining = changePaise;
        Map<MoneyDenomination, Integer> result = new LinkedHashMap<>();
        MoneyDenomination[] denoms = MoneyDenomination.values();
        Arrays.sort(denoms, Comparator.comparingLong(MoneyDenomination::paise).reversed());
        for (MoneyDenomination denomination : denoms) {
            int availableCount = Math.max(0, available.getOrDefault(denomination, 0));
            int used = (int) Math.min(availableCount, remaining / denomination.paise());
            if (used > 0) result.put(denomination, used);
            remaining -= (long) used * denomination.paise();
        }
        if (remaining != 0) throw new IllegalStateException("Exact change is unavailable");
        return Collections.unmodifiableMap(result);
    }
}
