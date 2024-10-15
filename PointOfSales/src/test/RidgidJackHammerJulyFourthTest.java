package test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import objects.RentalAgreement;
import objects.SalesSystem;

public class RidgidJackHammerJulyFourthTest {

	@Test
	public void ridgidJackHammerJulyFourthTest() {
		SalesSystem system = new SalesSystem();
		RentalAgreement result = system.checkout("JAKR", 4, 50, "07/02/2020");
		BigDecimal expected = new BigDecimal("2.99");
		//2.99 per day no weekend  no holiday charge
		//2 weekdays 2 weekends = 2 billable days
		//5.98 prediscount
		//2.99 discount amount
		//2.99 final charge
		
		assertTrue(result.finalCharge.compareTo(expected) == 0);
	}

}
