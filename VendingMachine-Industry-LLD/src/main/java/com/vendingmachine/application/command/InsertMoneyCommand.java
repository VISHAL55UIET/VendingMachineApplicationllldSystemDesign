package com.vendingmachine.application.command;
public record InsertMoneyCommand(long amountPaise) { public InsertMoneyCommand { if (amountPaise <= 0) throw new IllegalArgumentException("amountPaise must be positive"); } }
