package com.vendingmachine.admin;

import com.vendingmachine.application.service.MaintenanceService;

public class MaintenanceController {
    private final MaintenanceService service;
    public MaintenanceController(MaintenanceService service) { this.service = service; }
    public void begin() { service.startMaintenance(); }
    public void resume() { service.resume(); }
}
