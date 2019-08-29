package com.app.tcvm.service.impl;

import java.util.function.IntPredicate;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.DrinkService;
import com.app.tcvm.utils.Utility;

public class CoffeeService implements DrinkService {
	private VendingMachine vendingMachine = VendingMachine.getInstance();

	@Override
	public boolean makeDrink(int quantity) {
		if (checkContainerPresentMaterial(quantity)) {
			vendingMachine.setCoffeeCapacity(vendingMachine.getCoffeeCapacity()
					- (Utility.COFFEE_CONSUMPTION + Utility.COFFEE_WASTE) * quantity);
			vendingMachine.setMilkCapacity(vendingMachine.getMilkCapacity()
					- (Utility.COFFEE_MILK_CONSUMPTION + Utility.COFFEE_MILK_WASTE) * quantity);
			vendingMachine.setWaterCapacity(vendingMachine.getWaterCapacity()
					- (Utility.COFFEE_WATER_CONSUMPTION + Utility.COFFEE_WATER_WASTE) * quantity);
			vendingMachine.setSuagarCapacity(vendingMachine.getSuagarCapacity()
					- (Utility.COFFEE_SUGAR_CONSUMPTION + Utility.COFFEE_SUGAR_WASTE) * quantity);

			vendingMachine.setWasteCoffee(vendingMachine.getWasteCoffee() + Utility.COFFEE_WASTE * quantity);
			vendingMachine.setWasteMilk(vendingMachine.getWasteMilk() + Utility.COFFEE_MILK_WASTE * quantity);
			vendingMachine.setWasteWater(vendingMachine.getWasteWater() + Utility.COFFEE_WATER_WASTE * quantity);
			vendingMachine.setWasteSugar(vendingMachine.getWasteSugar() + Utility.COFFEE_SUGAR_WASTE * quantity);

			if (vendingMachine.getTotalSaleMap().containsKey(Utility.COFFEE))
				vendingMachine.getTotalSaleMap().put(Utility.COFFEE,
						vendingMachine.getTotalSaleMap().get(Utility.COFFEE) + quantity);
			else
				vendingMachine.getTotalSaleMap().put(Utility.COFFEE, quantity);
			return true;
		}
		throw new ContainerUnderflowException("Container is empty");
	}

	@Override
	public boolean checkContainerPresentMaterial(int quantity) {
		IntPredicate hasCoffee = capacity -> vendingMachine
				.getCoffeeCapacity() >= (Utility.COFFEE_CONSUMPTION + Utility.COFFEE_WASTE) * quantity ? true : false;
		IntPredicate hasWater = capacity -> vendingMachine
				.getWaterCapacity() >= (Utility.COFFEE_WATER_CONSUMPTION + Utility.COFFEE_WATER_WASTE) * quantity ? true
						: false;
		IntPredicate hasMilk = capacity -> vendingMachine
				.getMilkCapacity() >= (Utility.COFFEE_MILK_CONSUMPTION + Utility.COFFEE_MILK_WASTE) * quantity ? true
						: false;
		IntPredicate hasSugar = capacity -> vendingMachine
				.getSuagarCapacity() >= (Utility.COFFEE_SUGAR_CONSUMPTION + Utility.COFFEE_SUGAR_WASTE) * quantity
						? true
						: false;
		return hasCoffee.and(hasWater).and(hasMilk).and(hasSugar).test(quantity);
	}
}
