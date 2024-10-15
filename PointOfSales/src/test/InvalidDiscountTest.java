package test;

import org.junit.Test;
import objects.SalesSystem;

public class InvalidDiscountTest {

	@Test(expected = IllegalArgumentException.class)
	public void invalidDiscountTest() {
		SalesSystem system = new SalesSystem();
		system.checkout("JAKR", 5, 101, "09/03/2015");
	}

}
