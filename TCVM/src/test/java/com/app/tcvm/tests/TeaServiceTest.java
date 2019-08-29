package com.app.tcvm.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.service.impl.TeaService;
import com.app.tcvm.utils.Utility;

public class TeaServiceTest {
	private TeaService teaService = new TeaService();
	
	private VendingMachine vendingMachine = VendingMachine.getInstance();
	
	@Test
	public void shouldReturnTrueWhenRequiredAmountOfTeaPresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(true,vendingMachine.getTeaCapacity() >= (Utility.TEA_CONSUMPTION + Utility.TEA_WASTE) * 1);
		assertEquals(true, teaService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfTeaNotPresentInContainer() {
		vendingMachine.initializeContainer();
		assertEquals(false,vendingMachine.getTeaCapacity() >= (Utility.TEA_CONSUMPTION + Utility.TEA_WASTE) * 500);
		assertEquals(false, teaService.checkContainerPresentMaterial(500));
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfWaterPresentInContainer() {
		assertEquals(true,vendingMachine.getWaterCapacity() >= (Utility.TEA_WATER_CONSUMPTION + Utility.TEA_WATER_WASTE) * 1);
		assertEquals(true, teaService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfWaterNotPresentInContainer() {
		Long pastTeacapactiy = vendingMachine.getTeaCapacity();
		vendingMachine.setTeaCapacity(5000);
		assertEquals(false,vendingMachine.getWaterCapacity() >= (Utility.TEA_WATER_CONSUMPTION + Utility.TEA_WATER_WASTE) * 500);
		assertEquals(false, teaService.checkContainerPresentMaterial(500));
		vendingMachine.setTeaCapacity(pastTeacapactiy);
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfMilkPresentInContainer() {
		assertEquals(true,vendingMachine.getMilkCapacity() >= (Utility.TEA_MILK_CONSUMPTION + Utility.TEA_MILK_WASTE) * 1);
		assertEquals(true, teaService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfMilkNotPresentInContainer() {
		Long pastTeacapactiy = vendingMachine.getTeaCapacity();
		vendingMachine.setTeaCapacity(5000);
		Long pastWatercapactiy = vendingMachine.getWaterCapacity();
		vendingMachine.setWaterCapacity(33000);
		assertEquals(false,vendingMachine.getMilkCapacity() >= (Utility.TEA_MILK_CONSUMPTION + Utility.TEA_MILK_WASTE) * 500);
		assertEquals(false, teaService.checkContainerPresentMaterial(500));
		vendingMachine.setTeaCapacity(pastTeacapactiy);
		vendingMachine.setTeaCapacity(pastWatercapactiy);
	}

	@Test
	public void shouldReturnTrueWhenRequiredAmountOfSuagarPresentInContainer() {
		assertEquals(true,vendingMachine.getSuagarCapacity() >= (Utility.TEA_SUGAR_CONSUMPTION + Utility.TEA_SUGAR_WASTE) * 1);
		assertEquals(true, teaService.checkContainerPresentMaterial(1));
	}

	@Test
	public void shouldReturnFalseWhenRequiredAmountOfSugarNotPresentInContainer() {
		Long pastTeacapactiy = vendingMachine.getTeaCapacity();
		vendingMachine.setTeaCapacity(5000);
		Long pastWatercapactiy = vendingMachine.getWaterCapacity();
		vendingMachine.setWaterCapacity(33000);
		Long pasrMilkCapacity = vendingMachine.getMilkCapacity();
		vendingMachine.setMilkCapacity(23000);
		assertEquals(false,vendingMachine.getSuagarCapacity() >= (Utility.TEA_SUGAR_CONSUMPTION + Utility.TEA_SUGAR_WASTE) * 500);
		assertEquals(false, teaService.checkContainerPresentMaterial(500));
		vendingMachine.setTeaCapacity(pastTeacapactiy);
		vendingMachine.setTeaCapacity(pastWatercapactiy);
		vendingMachine.setMilkCapacity(pasrMilkCapacity);
	}

	@Test
	public void shouldReturnTrueWhenNumberOfCupProvidedIsGreaterThanZero() {
		vendingMachine.initializeContainer();
		assertEquals(true, teaService.makeDrink(1));
	}

	@Test
	public void shouldReturnTrueWhenContainerHasRequiredMaterial() {
		assertEquals(true, teaService.checkContainerPresentMaterial(1));
		assertEquals(true, teaService.makeDrink(1));
	}

	@Test(expected = ContainerUnderflowException.class)
	public void shouldReturnExceptionWhenContainerIsEmpty() {
		teaService.checkContainerPresentMaterial(500);
		teaService.makeDrink(500);
	}

}
