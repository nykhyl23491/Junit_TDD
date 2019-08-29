package com.app.tcvm.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.impl.BlackCoffeeService;
import com.app.tcvm.utils.Utility;

public class BlackCoffeeServiceTest {
	
	private VendingMachine vendingMachine = VendingMachine.getInstance();
	private BlackCoffeeService blackCoffeeService = new BlackCoffeeService();
	
	@Test
	public void shouldReturnTrueWhenRequiredAmountOfCoffeePresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(true,vendingMachine.getCoffeeCapacity() >= (Utility.BLACKCOFFEE_CONSUMPTION + Utility.BLACKCOFFEE_WASTE) * 1);
		assertEquals(true, blackCoffeeService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfCoffeeNotPresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(false,vendingMachine.getCoffeeCapacity() >= (Utility.BLACKCOFFEE_CONSUMPTION + Utility.BLACKCOFFEE_WASTE) * 700);
		assertEquals(false, blackCoffeeService.checkContainerPresentMaterial(700));
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfWaterPresentInContainer() {
		assertEquals(true,vendingMachine.getWaterCapacity() >= (Utility.BLACKCOFFEE_WATER_CONSUMPTION + Utility.BLACKCOFFEE_WATER_WASTE) * 1);
		assertEquals(true, blackCoffeeService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfWaterNotPresentInContainer() {
		Long pastCoffeecapactiy = vendingMachine.getCoffeeCapacity();
		vendingMachine.setCoffeeCapacity(2500);
		assertEquals(false,vendingMachine.getWaterCapacity() >= (Utility.BLACKCOFFEE_WATER_CONSUMPTION + Utility.BLACKCOFFEE_WATER_WASTE) * 670);
		assertEquals(false, blackCoffeeService.checkContainerPresentMaterial(670));
		vendingMachine.setCoffeeCapacity(pastCoffeecapactiy);
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfSuagarPresentInContainer() {
		assertEquals(true,vendingMachine.getSuagarCapacity() >= (Utility.BLACKCOFFEE_SUGAR_CONSUMPTION + Utility.BLACKCOFFEE_SUGAR_WASTE) * 1);
		assertEquals(true, blackCoffeeService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfSugarNotPresentInContainer() {
		Long pastCoffeecapactiy = vendingMachine.getCoffeeCapacity();
		vendingMachine.setCoffeeCapacity(2500);
		Long pastWatercapactiy = vendingMachine.getWaterCapacity();
		vendingMachine.setWaterCapacity(57000);
		assertEquals(false,vendingMachine.getSuagarCapacity() >= (Utility.BLACKCOFFEE_SUGAR_CONSUMPTION + Utility.BLACKCOFFEE_SUGAR_WASTE) * 500);
		assertEquals(false, blackCoffeeService.checkContainerPresentMaterial(500));
		vendingMachine.setCoffeeCapacity(pastCoffeecapactiy);
		vendingMachine.setWaterCapacity(pastWatercapactiy);
	}

	@Test
	public void shouldReturnTrueWhenNumberOfCupProvidedIsGreaterThanZero() {
		vendingMachine.initializeContainer();
		assertEquals(true, blackCoffeeService.makeDrink(1));
	}

	@Test
	public void shouldReturnTrueWhenContainerHasRequiredMaterial() {
		assertEquals(true, blackCoffeeService.checkContainerPresentMaterial(1));
		assertEquals(true, blackCoffeeService.makeDrink(1));
	}

	@Test(expected = ContainerUnderflowException.class)
	public void shouldReturnExceptionWhenContainerIsEmpty() {
		blackCoffeeService.checkContainerPresentMaterial(500);
		blackCoffeeService.makeDrink(500);
	}


}
