package com.app.tcvm.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.impl.CoffeeService;
import com.app.tcvm.utils.Utility;

public class CoffeeServiceTest {

	private VendingMachine vendingMachine = VendingMachine.getInstance();
	private CoffeeService coffeeService = new CoffeeService();

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfCoffeePresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(true,
				vendingMachine.getCoffeeCapacity() >= (Utility.COFFEE_CONSUMPTION + Utility.COFFEE_WASTE) * 1);
		assertEquals(true, coffeeService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfCoffeeNotPresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(false,
				vendingMachine.getCoffeeCapacity() >= (Utility.COFFEE_CONSUMPTION + Utility.COFFEE_WASTE) * 500);
		assertEquals(false, coffeeService.checkContainerPresentMaterial(500));
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfWaterPresentInContainer() {
		assertEquals(true, vendingMachine
				.getWaterCapacity() >= (Utility.COFFEE_WATER_CONSUMPTION + Utility.COFFEE_WATER_WASTE) * 1);
		assertEquals(true, coffeeService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfWaterNotPresentInContainer() {
		Long pastCoffeecapactiy = vendingMachine.getCoffeeCapacity();
		vendingMachine.setCoffeeCapacity(5000);
		assertEquals(false, vendingMachine
				.getWaterCapacity() >= (Utility.COFFEE_WATER_CONSUMPTION + Utility.COFFEE_WATER_WASTE) * 670);
		assertEquals(false, coffeeService.checkContainerPresentMaterial(670));
		vendingMachine.setCoffeeCapacity(pastCoffeecapactiy);
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfMilkPresentInContainer() {
		assertEquals(true,
				vendingMachine.getMilkCapacity() >= (Utility.COFFEE_MILK_CONSUMPTION + Utility.COFFEE_MILK_WASTE) * 1);
		assertEquals(true, coffeeService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfMilkNotPresentInContainer() {
		Long pastCoffeecapactiy = vendingMachine.getCoffeeCapacity();
		vendingMachine.setCoffeeCapacity(5000);
		Long pastWatercapactiy = vendingMachine.getWaterCapacity();
		vendingMachine.setWaterCapacity(12000);
		assertEquals(false, vendingMachine
				.getMilkCapacity() >= (Utility.COFFEE_MILK_CONSUMPTION + Utility.COFFEE_MILK_WASTE) * 500);
		assertEquals(false, coffeeService.checkContainerPresentMaterial(500));
		vendingMachine.setCoffeeCapacity(pastCoffeecapactiy);
		vendingMachine.setWaterCapacity(pastWatercapactiy);
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfSuagarPresentInContainer() {
		assertEquals(true, vendingMachine
				.getSuagarCapacity() >= (Utility.COFFEE_SUGAR_CONSUMPTION + Utility.COFFEE_SUGAR_WASTE) * 1);
		assertEquals(true, coffeeService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfSugarNotPresentInContainer() {
		Long pastCoffeecapactiy = vendingMachine.getCoffeeCapacity();
		vendingMachine.setCoffeeCapacity(5000);
		Long pastWatercapactiy = vendingMachine.getWaterCapacity();
		vendingMachine.setWaterCapacity(12000);
		Long pasrMilkCapacity = vendingMachine.getMilkCapacity();
		vendingMachine.setMilkCapacity(45000);
		assertEquals(false, vendingMachine
				.getSuagarCapacity() >= (Utility.COFFEE_SUGAR_CONSUMPTION + Utility.COFFEE_SUGAR_WASTE) * 500);
		assertEquals(false, coffeeService.checkContainerPresentMaterial(500));
		vendingMachine.setCoffeeCapacity(pastCoffeecapactiy);
		vendingMachine.setWaterCapacity(pastWatercapactiy);
		vendingMachine.setMilkCapacity(pasrMilkCapacity);
	}

	@Test
	public void shouldReturnTrueWhenNumberOfCupProvidedIsGreaterThanZero() {
		vendingMachine.initializeContainer();
		assertEquals(true, coffeeService.makeDrink(1));
	}

	@Test
	public void shouldReturnTrueWhenContainerHasRequiredMaterial() {
		assertEquals(true, coffeeService.checkContainerPresentMaterial(1));
		assertEquals(true, coffeeService.makeDrink(1));
	}

	@Test(expected = ContainerUnderflowException.class)
	public void shouldReturnExceptionWhenContainerIsEmpty() {
		coffeeService.checkContainerPresentMaterial(500);
		coffeeService.makeDrink(500);
	}

}
