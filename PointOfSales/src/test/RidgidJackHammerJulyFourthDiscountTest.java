package test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import objects.RentalAgreement;
import objects.SalesSystem;

public class RidgidJackHammerJulyFourthDiscountTest {

	@Test
	public void ridgidJackHammerJulyFourthDiscountTest() {
		SalesSystem system = new SalesSystem();
		RentalAgreement result = system.checkout("JAKR", 9, 0, "07/02/2015");
		BigDecimal expected = new BigDecimal("17.94");
		//2.99 per day no weekend no holiday charge
		//6 weekdays 2 weekends 1 holiday = 6 billable days
		//17.94 prediscount amount, no discount
		
		assertTrue(result.finalCharge.compareTo(expected) == 0);
	}

}
