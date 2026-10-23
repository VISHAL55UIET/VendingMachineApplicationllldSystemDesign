package com.vendingmachine.application.command;
public record CancelTransactionCommand(String reason) { public CancelTransactionCommand { if (reason == null) reason = "Customer cancelled"; } }
