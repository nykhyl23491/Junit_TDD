package com.app.tcvm.service.impl;

import java.util.function.IntPredicate;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.DrinkService;
import com.app.tcvm.utils.Utility;

public class BlackCoffeeService implements DrinkService {
	private VendingMachine vendingMachine = VendingMachine.getInstance();
	@Override
	public boolean makeDrink(int quantity) {
		if (checkContainerPresentMaterial(quantity)) {
			vendingMachine.setTeaCapacity(vendingMachine.getTeaCapacity()
					- (Utility.BLACKCOFFEE_CONSUMPTION + Utility.BLACKCOFFEE_WASTE) * quantity);
			vendingMachine.setWaterCapacity(vendingMachine.getWaterCapacity()
					- (Utility.BLACKCOFFEE_WATER_CONSUMPTION + Utility.BLACKCOFFEE_WATER_WASTE) * quantity);
			vendingMachine.setSuagarCapacity(vendingMachine.getSuagarCapacity()
					- (Utility.BLACKCOFFEE_SUGAR_CONSUMPTION + Utility.BLACKCOFFEE_SUGAR_WASTE) * quantity);

			vendingMachine.setWasteTea(vendingMachine.getWasteTea() + Utility.BLACKCOFFEE_WASTE * quantity);
			vendingMachine.setWasteWater(vendingMachine.getWasteWater() + Utility.BLACKCOFFEE_WATER_WASTE * quantity);
			vendingMachine.setWasteSugar(vendingMachine.getWasteSugar() + Utility.BLACKCOFFEE_SUGAR_WASTE * quantity);

			if (vendingMachine.getTotalSaleMap().containsKey(Utility.BLACK_COFFEE))
				vendingMachine.getTotalSaleMap().put(Utility.BLACK_COFFEE,
						vendingMachine.getTotalSaleMap().get(Utility.BLACK_COFFEE) + quantity);
			else
				vendingMachine.getTotalSaleMap().put(Utility.BLACK_COFFEE, quantity);
			return true;
		}
		throw new ContainerUnderflowException("Container is empty");
	}

	@Override
	public boolean checkContainerPresentMaterial(int quantity) {
		IntPredicate hasCoffee = capacity -> vendingMachine
				.getCoffeeCapacity() >= (Utility.BLACKCOFFEE_CONSUMPTION + Utility.BLACKCOFFEE_WASTE) * quantity ? true
						: false;
		IntPredicate hasWater = capacity -> vendingMachine
				.getWaterCapacity() >= (Utility.BLACKCOFFEE_WATER_CONSUMPTION + Utility.BLACKCOFFEE_WATER_WASTE)
						* quantity ? true : false;
		IntPredicate hasSugar = capacity -> vendingMachine
				.getSuagarCapacity() >= (Utility.BLACKCOFFEE_SUGAR_CONSUMPTION + Utility.BLACKCOFFEE_SUGAR_WASTE)
						* quantity ? true : false;
		return hasCoffee.and(hasWater).and(hasSugar).test(quantity);
	}

}
