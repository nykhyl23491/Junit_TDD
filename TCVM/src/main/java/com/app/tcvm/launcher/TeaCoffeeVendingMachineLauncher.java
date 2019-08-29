package com.app.tcvm.launcher;

import com.app.tcvm.controller.VendingMachine;

public class TeaCoffeeVendingMachineLauncher {
	public static void main(String[] args) {
		VendingMachine.getInstance().startVendingMachine();
	}
}
