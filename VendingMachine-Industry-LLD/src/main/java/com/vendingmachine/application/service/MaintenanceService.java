package com.vendingmachine.application.service;

import com.vendingmachine.domain.machine.MachineStatus;
import com.vendingmachine.domain.machine.VendingMachine;

public class MaintenanceService {
    private final VendingMachine machine;
    public MaintenanceService(VendingMachine machine) { this.machine = machine; }
    public void startMaintenance() { machine.setStatus(MachineStatus.MAINTENANCE); }
    public void resume() { machine.setStatus(MachineStatus.ACTIVE); }
    public void takeOutOfService() { machine.setStatus(MachineStatus.OUT_OF_SERVICE); }
}
