package com.app.tcvm.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.app.tcvm.controller.VendingMachine;
import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.factory.MakeDrinkFactory;
import com.app.tcvm.service.DrinkService;
import com.app.tcvm.service.impl.BlackCoffeeService;
import com.app.tcvm.service.impl.BlackTeaService;
import com.app.tcvm.service.impl.CoffeeService;
import com.app.tcvm.service.impl.TeaService;
import com.app.tcvm.utils.UserInput;
import com.app.tcvm.utils.Utility;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {
	@Mock
	private Appender mockAppender;

	@Mock
	private UserInput userInputMock;

	@Mock
	private DrinkService drinkServiceMock;

	@Mock
	private MakeDrinkFactory drinkFactoryMock;

	@InjectMocks
	private VendingMachine vendingMachine;

	@Before
	public void setAppender() {
		mockAppender = mock(Appender.class);
		Logger.getRootLogger().addAppender(mockAppender);
	}

	@After
	public void removeAppender() {
		Logger.getRootLogger().removeAppender(mockAppender);
	}

	@Before
	public void resetSingleton()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field instance = VendingMachine.class.getDeclaredField("instance");
		instance.setAccessible(true);
		instance.set(null, null);
	}

	@Test
	public void shouldInitializeContainerWithDefaultCapacity() {
		vendingMachine.initializeContainer();
		assertEquals(2000, vendingMachine.getTeaCapacity());
		assertEquals(2000, vendingMachine.getCoffeeCapacity());
		assertEquals(8000, vendingMachine.getSuagarCapacity());
		assertEquals(10000, vendingMachine.getMilkCapacity());
		assertEquals(15000, vendingMachine.getWaterCapacity());
	}

	@Test
	public void shouldTakeNumberOfCupsFromUser() {
		when(userInputMock.getUserInput()).thenReturn(5);
		vendingMachine.acceptRecord();
	}

	@Test
	public void shouldCheckContainerStatus() {
		vendingMachine.checkContainerStatus();
		verify(mockAppender, times(4)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldCheckTotalSale() {
		Map<String, Integer> totalSale = vendingMachine.getTotalSaleMap();
		totalSale.put(Utility.TEA, 3);
		totalSale.put(Utility.COFFEE, 3);
		totalSale.put(Utility.BLACK_TEA, 3);
		totalSale.put(Utility.BLACK_COFFEE, 3);
		vendingMachine.checkTotalSales();
		verify(mockAppender, times(10)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldRefillContainer() {
		vendingMachine.refillContainer();
		assertEquals(2000, vendingMachine.getTeaCapacity());
		assertEquals(2000, vendingMachine.getCoffeeCapacity());
		assertEquals(8000, vendingMachine.getSuagarCapacity());
		assertEquals(10000, vendingMachine.getMilkCapacity());
		assertEquals(15000, vendingMachine.getWaterCapacity());
	}

	@Test
	public void shouldResetContainer() {
		vendingMachine.resetContainer();
		assertEquals(2000, vendingMachine.getTeaCapacity());
		assertEquals(2000, vendingMachine.getCoffeeCapacity());
		assertEquals(8000, vendingMachine.getSuagarCapacity());
		assertEquals(10000, vendingMachine.getMilkCapacity());
		assertEquals(15000, vendingMachine.getWaterCapacity());
		assertEquals(0, vendingMachine.getWasteTea());
		assertEquals(0, vendingMachine.getWasteCoffee());
		assertEquals(0, vendingMachine.getWasteSugar());
		assertEquals(0, vendingMachine.getWasteMilk());
		assertEquals(0, vendingMachine.getWasteWater());
	}

	@Test
	public void shouldNotStartVendingMachineWithInvalidChoice() {
		when(userInputMock.getUserInput()).thenReturn(6, 0);
		vendingMachine.initializeContainer();
		vendingMachine.startVendingMachine();
	}

	@Test
	public void shouldStratVendingMachineForMakeDrinkWhenChoiceIsOne() {
		when(userInputMock.getUserInput()).thenReturn(1, 0);
		vendingMachine.startVendingMachine();
		vendingMachine.initializeContainer();
		vendingMachine.makeDrinkForCustomer();
	}

	@Test
	public void shouldStratVendingMachineForCheckContainerWhenChoiceIsTwo() {
		when(userInputMock.getUserInput()).thenReturn(2, 0);
		vendingMachine.startVendingMachine();
		vendingMachine.initializeContainer();
		vendingMachine.checkContainerStatus();
	}

	@Test
	public void shouldStratVendingMachineForRefillContainerWhenChoiceIsThree() {
		when(userInputMock.getUserInput()).thenReturn(3, 0);
		vendingMachine.startVendingMachine();
		vendingMachine.initializeContainer();
		vendingMachine.refillContainer();
	}

	@Test
	public void shouldStratVendingMachineForResetContainerWhenChoiceIsFour() {
		when(userInputMock.getUserInput()).thenReturn(4, 0);
		vendingMachine.startVendingMachine();
		vendingMachine.initializeContainer();
		vendingMachine.resetContainer();
	}

	@Test
	public void shouldStratVendingMachineForTotalSaleWhenChoiceIsFive() {
		when(userInputMock.getUserInput()).thenReturn(4, 0);
		vendingMachine.startVendingMachine();
		vendingMachine.initializeContainer();
		vendingMachine.checkTotalSales();
	}

	@Test
	public void shouldReturnTeaServiceWhenUserChoiceIsOne() 
	{
		vendingMachine.makeDrinkForCustomer();
		when(userInputMock.getUserInput()).thenReturn(1);
		userInputMock.getUserInput();
		verify(userInputMock,times(2)).getUserInput();
		when(drinkFactoryMock.getDrinkServiceInstance(1)).thenReturn(new TeaService());
		drinkFactoryMock.getDrinkServiceInstance(1);
		verify(drinkFactoryMock).getDrinkServiceInstance(1);
		when(drinkServiceMock.makeDrink(5)).thenReturn(true);
		drinkServiceMock.makeDrink(5);
		verify(drinkServiceMock).makeDrink(5);
		
	}

	@Test
	public void shouldReturnCoffeeServiceWhenUserChoiceIsTwo() {
		vendingMachine.makeDrinkForCustomer();
		when(userInputMock.getUserInput()).thenReturn(2);
		userInputMock.getUserInput();
		verify(userInputMock,times(2)).getUserInput();
		when(drinkFactoryMock.getDrinkServiceInstance(2)).thenReturn(new CoffeeService());
		drinkFactoryMock.getDrinkServiceInstance(2);
		verify(drinkFactoryMock).getDrinkServiceInstance(2);
		when(drinkServiceMock.makeDrink(5)).thenReturn(true);
		drinkServiceMock.makeDrink(5);
		verify(drinkServiceMock).makeDrink(5);
	}

	@Test
	public void shouldReturnBlackTeaServiceWhenUserChoiceIsThree() {
		vendingMachine.makeDrinkForCustomer();
		when(userInputMock.getUserInput()).thenReturn(3);
		userInputMock.getUserInput();
		verify(userInputMock,times(2)).getUserInput();
		when(drinkFactoryMock.getDrinkServiceInstance(3)).thenReturn(new BlackTeaService());
		drinkFactoryMock.getDrinkServiceInstance(3);
		verify(drinkFactoryMock).getDrinkServiceInstance(3);
		when(drinkServiceMock.makeDrink(5)).thenReturn(true);
		drinkServiceMock.makeDrink(5);
		verify(drinkServiceMock).makeDrink(5);
	}

	@Test
	public void shouldReturnBlackCoffeeServiceWhenUserChoiceIsFour() {
		vendingMachine.makeDrinkForCustomer();
		when(userInputMock.getUserInput()).thenReturn(4);
		userInputMock.getUserInput();
		verify(userInputMock,times(2)).getUserInput();
		when(drinkFactoryMock.getDrinkServiceInstance(4)).thenReturn(new BlackCoffeeService());
		drinkFactoryMock.getDrinkServiceInstance(4);
		verify(drinkFactoryMock).getDrinkServiceInstance(4);
		when(drinkServiceMock.makeDrink(5)).thenReturn(true);
		drinkServiceMock.makeDrink(5);
		verify(drinkServiceMock).makeDrink(5);
	}
	

	@Test
	public void shouldReturnNullWhenUserChoiceIsInvalid() {
		vendingMachine.makeDrinkForCustomer();
		when(userInputMock.getUserInput()).thenReturn(5);
		userInputMock.getUserInput();
		verify(userInputMock,times(2)).getUserInput();
		when(drinkFactoryMock.getDrinkServiceInstance(5)).thenReturn(null);
		drinkFactoryMock.getDrinkServiceInstance(5);
		verify(drinkFactoryMock).getDrinkServiceInstance(5);
	}

	@Test
	public void shouldReturnTrueWhenDrinkMaterialAvailable() {
		vendingMachine.initializeContainer();
		vendingMachine.startVendingMachine();
		when(drinkServiceMock.makeDrink(1)).thenReturn(true);
		drinkServiceMock.makeDrink(1);
		verify(mockAppender, times(6)).doAppend((LoggingEvent) anyObject());
	}

	@Test(expected = ContainerUnderflowException.class)
	public void shouldThrowExceptionWhenDrinkMaterialNotAvailable() {
		vendingMachine.initializeContainer();
		vendingMachine.startVendingMachine();
		doThrow(ContainerUnderflowException.class).when(drinkServiceMock).makeDrink(500);
		drinkServiceMock.makeDrink(500);
	}
	
	@Test
	public void shoulCheckDrinkServiceIsNotNull()
	{
		vendingMachine.initializeContainer();
		vendingMachine.startVendingMachine();
		vendingMachine.makeDrinkForCustomer();
		when(drinkFactoryMock.getDrinkServiceInstance(1)).thenReturn(drinkServiceMock);
		when(drinkFactoryMock.getDrinkServiceInstance(2)).thenReturn(drinkServiceMock);
		when(drinkFactoryMock.getDrinkServiceInstance(3)).thenReturn(drinkServiceMock);
		when(drinkFactoryMock.getDrinkServiceInstance(4)).thenReturn(drinkServiceMock);
		
		assertNotNull(drinkServiceMock);
		
	}

}
