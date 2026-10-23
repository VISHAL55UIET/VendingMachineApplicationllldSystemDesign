package com.vendingmachine.application.result;

public record RefundResult(long refundedPaise, String reason, boolean successful) {}
