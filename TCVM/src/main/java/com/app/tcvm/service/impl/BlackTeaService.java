package com.app.tcvm.service.impl;

import java.util.function.IntPredicate;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.DrinkService;
import com.app.tcvm.utils.Utility;

public class BlackTeaService implements DrinkService {

	private VendingMachine vendingMachine = VendingMachine.getInstance();
	@Override
	public boolean makeDrink(int quantity) {
		if (checkContainerPresentMaterial(quantity)) {
			vendingMachine.setTeaCapacity(vendingMachine.getTeaCapacity()
					- (Utility.BLACKTEA_CONSUMPTION + Utility.BLACKTEA_WASTE) * quantity);
			vendingMachine.setWaterCapacity(vendingMachine.getWaterCapacity()
					- (Utility.BLACKTEA_WATER_CONSUMPTION + Utility.BLACKTEA_WATER_WASTE) * quantity);
			vendingMachine.setSuagarCapacity(vendingMachine.getSuagarCapacity()
					- (Utility.BLACKTEA_SUGAR_CONSUMPTION + Utility.BLACKTEA_SUGAR_WASTE) * quantity);

			vendingMachine.setWasteTea(vendingMachine.getWasteTea() + Utility.BLACKTEA_WASTE * quantity);
			vendingMachine.setWasteWater(vendingMachine.getWasteWater() + Utility.BLACKTEA_WATER_WASTE * quantity);
			vendingMachine.setWasteSugar(vendingMachine.getWasteSugar() + Utility.BLACKTEA_SUGAR_WASTE * quantity);
			if (vendingMachine.getTotalSaleMap().containsKey(Utility.BLACK_TEA))
				vendingMachine.getTotalSaleMap().put(Utility.BLACK_TEA,
						vendingMachine.getTotalSaleMap().get(Utility.BLACK_TEA) + quantity);
			else
				vendingMachine.getTotalSaleMap().put(Utility.BLACK_TEA, quantity);
			return true;
		}
		throw new ContainerUnderflowException("Container is empty");
	}

	@Override
	public boolean checkContainerPresentMaterial(int quantity) {
		IntPredicate hasTea = capacity -> vendingMachine
				.getTeaCapacity() >= (Utility.BLACKTEA_CONSUMPTION + Utility.BLACKTEA_WASTE) * quantity ? true : false;
		IntPredicate hasWater = capacity -> vendingMachine
				.getWaterCapacity() >= (Utility.BLACKTEA_WATER_CONSUMPTION + Utility.BLACKTEA_WATER_WASTE) * quantity
						? true
						: false;
		IntPredicate hasSugar = capacity -> vendingMachine
				.getSuagarCapacity() >= (Utility.BLACKTEA_SUGAR_CONSUMPTION + Utility.BLACKTEA_SUGAR_WASTE) * quantity
						? true
						: false;
		return hasTea.and(hasWater).and(hasSugar).test(quantity);
	}

}
