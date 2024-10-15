package test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import objects.RentalAgreement;
import objects.SalesSystem;

public class ChainsawJulyFourthTest {

	@Test
	public void chainsawJulyFourthTest() {
		SalesSystem system = new SalesSystem();
		RentalAgreement result = system.checkout("CHNS", 5, 25, "07/02/2015");
		BigDecimal expected = new BigDecimal("3.35");
		//1.49 per day no weekend charge
		//2 weekdays 2 weekends 1 holiday = 3 billable days
		//4.47 prediscount amount
		//1.12 discount amount
		//3.35 final charge
				
		assertTrue(result.finalCharge.compareTo(expected) == 0);
	}

}
