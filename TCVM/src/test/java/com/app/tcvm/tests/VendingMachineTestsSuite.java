
package com.app.tcvm.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ VendingMachineTest.class, TeaServiceTest.class, CoffeeServiceTest.class, BlackTeaServiceTest.class,
		BlackCoffeeServiceTest.class })
public class VendingMachineTestsSuite {

}
