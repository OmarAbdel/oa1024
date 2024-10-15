package test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import objects.RentalAgreement;
import objects.SalesSystem;

public class DewaltJackHammerLaborDayTest {

	@Test
	public void dewaltJackHammerLaborDayTest() {
		SalesSystem system = new SalesSystem();
		RentalAgreement result = system.checkout("JAKD", 6, 0, "09/03/2015");
		BigDecimal expected = new BigDecimal("8.97");
		//2.99 per day no weekend no holiady charge
		//3 weekdays 2 weekends 1 holiday = 3 billable days
		//8.97 prediscount amount, no discount
				
		assertTrue(result.finalCharge.compareTo(expected) == 0);
	}

}
