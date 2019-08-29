package com.app.tcvm.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.impl.BlackTeaService;
import com.app.tcvm.utils.Utility;

public class BlackTeaServiceTest {

	private VendingMachine vendingMachine = VendingMachine.getInstance();
	private BlackTeaService blackTeaService = new BlackTeaService();

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfTeaPresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(true, vendingMachine.getTeaCapacity() >= (Utility.BLACKTEA_CONSUMPTION + Utility.BLACKTEA_WASTE) * 1);
		assertEquals(true, blackTeaService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfTeaNotPresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(false,vendingMachine.getTeaCapacity() >= (Utility.BLACKTEA_CONSUMPTION + Utility.BLACKTEA_WASTE) * 700);
		assertEquals(false, blackTeaService.checkContainerPresentMaterial(700));
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfWaterPresentInContainer() {
		assertEquals(true, vendingMachine.getWaterCapacity() >= (Utility.BLACKTEA_WATER_CONSUMPTION + Utility.BLACKTEA_WATER_WASTE) * 1);
		assertEquals(true, blackTeaService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfWaterNotPresentInContainer() {
		Long pastTeacapactiy = vendingMachine.getTeaCapacity();
		vendingMachine.setTeaCapacity(2500);
		assertEquals(false, vendingMachine.getWaterCapacity() >= (Utility.BLACKTEA_WATER_CONSUMPTION + Utility.BLACKTEA_WATER_WASTE) * 500);
		assertEquals(false, blackTeaService.checkContainerPresentMaterial(500));
		vendingMachine.setTeaCapacity(pastTeacapactiy);
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfSuagarPresentInContainer() {
		assertEquals(true, vendingMachine.getSuagarCapacity() >= (Utility.BLACKTEA_SUGAR_CONSUMPTION + Utility.BLACKTEA_SUGAR_WASTE) * 1);
		assertEquals(true, blackTeaService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfSugarNotPresentInContainer() {
		Long pastTeacapactiy = vendingMachine.getTeaCapacity();
		vendingMachine.setTeaCapacity(2500);
		Long pastWatercapactiy = vendingMachine.getWaterCapacity();
		vendingMachine.setWaterCapacity(57000);
		assertEquals(false, vendingMachine.getSuagarCapacity() >= (Utility.BLACKTEA_SUGAR_CONSUMPTION + Utility.BLACKTEA_SUGAR_WASTE) * 500);
		assertEquals(false, blackTeaService.checkContainerPresentMaterial(500));
		vendingMachine.setTeaCapacity(pastTeacapactiy);
		vendingMachine.setWaterCapacity(pastWatercapactiy);
	}

	@Test
	public void shouldReturnTrueWhenNumberOfCupProvidedIsGreaterThanZero() {
		vendingMachine.initializeContainer();
		assertEquals(true, blackTeaService.makeDrink(1));
	}

	@Test
	public void shouldReturnTrueWhenContainerHasRequiredMaterial() {
		assertEquals(true, blackTeaService.checkContainerPresentMaterial(1));
		assertEquals(true, blackTeaService.makeDrink(1));
	}

	@Test(expected = ContainerUnderflowException.class)
	public void shouldReturnExceptionWhenContainerIsEmpty() {
		blackTeaService.checkContainerPresentMaterial(500);
		blackTeaService.makeDrink(500);
	}

}
