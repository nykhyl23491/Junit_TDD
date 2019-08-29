package com.app.tcvm.service.impl;

import java.util.function.IntPredicate;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.DrinkService;
import com.app.tcvm.utils.Utility;

public class TeaService implements DrinkService {
	private VendingMachine vendingMachine = VendingMachine.getInstance();

	public boolean makeDrink(int quantity) {

		if (checkContainerPresentMaterial(quantity)) {
			vendingMachine.setTeaCapacity(
					vendingMachine.getTeaCapacity() - (Utility.TEA_CONSUMPTION + Utility.TEA_WASTE) * quantity);
			vendingMachine.setMilkCapacity(vendingMachine.getMilkCapacity()
					- (Utility.TEA_MILK_CONSUMPTION + Utility.TEA_MILK_WASTE) * quantity);
			vendingMachine.setWaterCapacity(vendingMachine.getWaterCapacity()
					- (Utility.TEA_WATER_CONSUMPTION + Utility.TEA_WATER_WASTE) * quantity);
			vendingMachine.setSuagarCapacity(vendingMachine.getSuagarCapacity()
					- (Utility.TEA_SUGAR_CONSUMPTION + Utility.TEA_SUGAR_WASTE) * quantity);

			vendingMachine.setWasteTea(vendingMachine.getWasteTea() + Utility.TEA_WASTE * quantity);
			vendingMachine.setWasteMilk(vendingMachine.getWasteMilk() + Utility.TEA_MILK_WASTE * quantity);
			vendingMachine.setWasteWater(vendingMachine.getWasteWater() + Utility.TEA_WATER_WASTE * quantity);
			vendingMachine.setWasteSugar(vendingMachine.getWasteSugar() + Utility.TEA_SUGAR_WASTE * quantity);

			if (vendingMachine.getTotalSaleMap().containsKey(Utility.TEA))
				vendingMachine.getTotalSaleMap().put(Utility.TEA,
						vendingMachine.getTotalSaleMap().get(Utility.TEA) + quantity);
			else
				vendingMachine.getTotalSaleMap().put(Utility.TEA, quantity);
			return true;
		}
		throw new ContainerUnderflowException("Container is empty");
	}

	public boolean checkContainerPresentMaterial(int quantity) {
		IntPredicate hasTea = capacity -> vendingMachine.getTeaCapacity() >= (Utility.TEA_CONSUMPTION + Utility.TEA_WASTE) * quantity ? true : false;
		IntPredicate hasWater = capacity -> vendingMachine
				.getWaterCapacity() >= (Utility.TEA_WATER_CONSUMPTION + Utility.TEA_WATER_WASTE) * quantity ? true
						: false;
		IntPredicate hasMilk = capacity -> vendingMachine
				.getMilkCapacity() >= (Utility.TEA_MILK_CONSUMPTION + Utility.TEA_MILK_WASTE) * quantity ? true : false;
		IntPredicate hasSugar = capacity -> vendingMachine
				.getSuagarCapacity() >= (Utility.TEA_SUGAR_CONSUMPTION + Utility.TEA_SUGAR_WASTE) * quantity ? true
						: false;
		return hasTea.and(hasWater).and(hasMilk).and(hasSugar).test(quantity);
	}
}
