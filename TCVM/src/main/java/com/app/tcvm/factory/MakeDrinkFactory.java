package com.app.tcvm.factory;

import com.app.tcvm.service.DrinkService;
import com.app.tcvm.service.impl.BlackCoffeeService;
import com.app.tcvm.service.impl.BlackTeaService;
import com.app.tcvm.service.impl.CoffeeService;
import com.app.tcvm.service.impl.TeaService;

public class MakeDrinkFactory {
	public MakeDrinkFactory() {
	}

	public  DrinkService getDrinkServiceInstance(int choice) {
		switch (choice) {
		case 1:
			return new TeaService();
		case 2:
			return new CoffeeService();
		case 3:
			return new BlackTeaService();
		case 4:
			return new BlackCoffeeService();
		}
		return null;
	}

}
