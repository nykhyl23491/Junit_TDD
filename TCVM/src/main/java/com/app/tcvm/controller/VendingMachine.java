package com.app.tcvm.controller;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.app.tcvm.exception.ContainerUnderflowException;
import com.app.tcvm.factory.MakeDrinkFactory;
import com.app.tcvm.service.DrinkService;
import com.app.tcvm.utils.UserInput;
import com.app.tcvm.utils.Utility;

public class VendingMachine {
	private static final Logger LOGGER = Logger.getLogger(VendingMachine.class);
	private static VendingMachine instance;
	private long teaCapacity;
	private long coffeeCapacity;
	private long suagarCapacity;
	private long waterCapacity;
	private long milkCapacity;
	private long wasteTea;
	private long wasteCoffee;
	private long wasteSugar;
	private long wasteWater;
	private long wasteMilk;

	private Map<String, Integer> totalSaleMap = new HashMap<>();
	private UserInput input;

	private VendingMachine() {
		input = new UserInput();
	}

	public long getTeaCapacity() {
		return teaCapacity;
	}

	public void setTeaCapacity(long teaCapacity) {
		this.teaCapacity = teaCapacity;
	}

	public long getCoffeeCapacity() {
		return coffeeCapacity;
	}

	public void setCoffeeCapacity(long coffeeCapacity) {
		this.coffeeCapacity = coffeeCapacity;
	}

	public long getSuagarCapacity() {
		return suagarCapacity;
	}

	public void setSuagarCapacity(long suagarCapacity) {
		this.suagarCapacity = suagarCapacity;
	}

	public long getWaterCapacity() {
		return waterCapacity;
	}

	public void setWaterCapacity(long waterCapacity) {
		this.waterCapacity = waterCapacity;
	}

	public long getMilkCapacity() {
		return milkCapacity;
	}

	public void setMilkCapacity(long milkCapacity) {
		this.milkCapacity = milkCapacity;
	}

	public long getWasteTea() {
		return wasteTea;
	}

	public void setWasteTea(long wasteTea) {
		this.wasteTea = wasteTea;
	}

	public long getWasteCoffee() {
		return wasteCoffee;
	}

	public void setWasteCoffee(long wasteCoffee) {
		this.wasteCoffee = wasteCoffee;
	}

	public long getWasteSugar() {
		return wasteSugar;
	}

	public void setWasteSugar(long wasteSugar) {
		this.wasteSugar = wasteSugar;
	}

	public long getWasteWater() {
		return wasteWater;
	}

	public void setWasteWater(long wasteWater) {
		this.wasteWater = wasteWater;
	}

	public long getWasteMilk() {
		return wasteMilk;
	}

	public void setWasteMilk(long wasteMilk) {
		this.wasteMilk = wasteMilk;
	}

	public static VendingMachine getInstance() {
		if (instance == null) {
			instance = new VendingMachine();
			return instance;
		}
		return instance;
	}

	public Map<String, Integer> getTotalSaleMap() {
		return totalSaleMap;
	}

	public void initializeContainer() {

		try {
			Object obj = new JSONParser().parse(new FileReader("container.json"));
			JSONObject containerJson = (JSONObject) obj;
			setTeaCapacity((Long) containerJson.get("teaCapacity"));
			setCoffeeCapacity((Long) containerJson.get("coffeeCapacity"));
			setSuagarCapacity((Long) containerJson.get("sugarCapacity"));
			setMilkCapacity((Long) containerJson.get("milkCapacity"));
			setWaterCapacity((Long) containerJson.get("waterCapacity"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int acceptRecord() {
		System.out.print("Enter Number of Cup :: ");
		return input.getUserInput();
	}

	public void makeDrinkForCustomer() {
		int choice;
		MakeDrinkFactory makeDrinkFactory = new MakeDrinkFactory();
		while ((choice = makeDrinkMenuList()) != 0) 
		{
			DrinkService drinkService = makeDrinkFactory.getDrinkServiceInstance(choice);
			if (drinkService != null) {
				try {
					if (drinkService.makeDrink(acceptRecord()))
						LOGGER.info("ENJOY...Your Drink....... :)");
				} catch (ContainerUnderflowException e) {
					LOGGER.info(e.getMessage());
				}
			} else {
				LOGGER.info("Invalid Choice..... :(");
			}
		}
	}

	public void checkContainerStatus() {
		String heading = String.format("Container :: %4s %10s %8s %9s %9s", "TEA", "COFFEE", "SUGAR", "MILK", "WATER");
		LOGGER.info(heading);
		String capacity = String.format("Remaining :: %5d %8d %9d %9d %9d", getTeaCapacity(), getCoffeeCapacity(),
				getSuagarCapacity(), getMilkCapacity(), getWaterCapacity());
		LOGGER.info(capacity);
		String wastage = String.format("Wastage  ::%6d %8d %9d %9d %9d", getWasteTea(), getWasteCoffee(),
				getWasteSugar(), getWasteMilk(), getWasteWater());
		LOGGER.info(wastage);
		LOGGER.info("\n");
	}

	public void refillContainer() {
		try {
			Object obj = new JSONParser().parse(new FileReader("container.json"));
			JSONObject containerJson = (JSONObject) obj;
			setTeaCapacity(getTeaCapacity() + ((Long) containerJson.get("teaCapacity") - getTeaCapacity()));
			setCoffeeCapacity(getCoffeeCapacity() + ((Long) containerJson.get("coffeeCapacity") - getCoffeeCapacity()));
			setSuagarCapacity(getSuagarCapacity() + ((Long) containerJson.get("sugarCapacity") - getSuagarCapacity()));
			setMilkCapacity(getMilkCapacity() + ((Long) containerJson.get("milkCapacity") - getMilkCapacity()));
			setWaterCapacity(getWaterCapacity() + ((Long) containerJson.get("waterCapacity") - getWaterCapacity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetContainer() {
		initializeContainer();
		setWasteTea(0);
		setWasteCoffee(0);
		setWasteSugar(0);
		setWasteMilk(0);
		setWasteWater(0);
	}

	public int getUnitPricing(String s) {
		switch (s) {
		case Utility.TEA:
			return Utility.COST_PER_TEA_CUP;
		case Utility.COFFEE:
			return Utility.COST_PER_COFFEE_CUP;
		case Utility.BLACK_TEA:
			return Utility.COST_PER_BLACK_TEA_CUP;
		case Utility.BLACK_COFFEE:
			return Utility.COST_PER_BLACK_COFFEE_CUP;
		}
		return 0;
	}

	public int getSaleValue(String s) {
		return null == totalSaleMap.get(s) ? 0 : totalSaleMap.get(s);
	}

	public void checkTotalSales() {
		totalSaleMap.keySet().stream()
				.forEach(s -> LOGGER.info(String.format("%-27s", " Sold " + s) + " :: " + getSaleValue(s)));
		totalSaleMap.keySet().stream().forEach(s -> LOGGER
				.info(String.format("%-27s", " Price of Total " + s) + " :: " + getSaleValue(s) * getUnitPricing(s)));
		LOGGER.info(String.format("%-27s", " Total Cup ") + " :: "
				+ totalSaleMap.keySet().stream().mapToInt(s -> getSaleValue(s)).sum());
		LOGGER.info(String.format("%-27s", " Total Sale ") + " :: "
				+ totalSaleMap.keySet().stream().mapToInt(s -> getSaleValue(s) * getUnitPricing(s)).sum());
	}

	public int vendingMachineMenuList() {
		int choice = 0;
		LOGGER.info("0.Exit Vending Machine");
		LOGGER.info("1. Make Drinks");
		LOGGER.info("2. Check Container Status");
		LOGGER.info("3. Refill Container");
		LOGGER.info("4. Reset Container");
		LOGGER.info("5. Check Total Sale");
		choice = input.getUserInput();
		return choice;
	}

	public int makeDrinkMenuList() {
		int choice = 0;
		LOGGER.info("0. EXIT");
		LOGGER.info("1. TEA");
		LOGGER.info("2. COFFEE");
		LOGGER.info("3. BLACK TEA");
		LOGGER.info("4. BLACK COFFEE");
		choice = input.getUserInput();
		return choice;
	}

	public void startVendingMachine() {
		int choice;
		initializeContainer();
		while ((choice = vendingMachineMenuList()) != 0) {
			switch (choice) {
			case 1:
				makeDrinkForCustomer();
				break;
			case 2:
				checkContainerStatus();
				break;
			case 3:
				refillContainer();
				break;
			case 4:
				resetContainer();
				break;
			case 5:
				checkTotalSales();
				break;
			default:
				LOGGER.info("Invalid Option");
			}
		}
	}
}