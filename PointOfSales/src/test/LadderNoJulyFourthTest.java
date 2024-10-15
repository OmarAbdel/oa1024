package test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import objects.RentalAgreement;
import objects.SalesSystem;

public class LadderNoJulyFourthTest {

	@Test
	public void ladderNoJulyFourthTest() {
		SalesSystem system = new SalesSystem();
		RentalAgreement result = system.checkout("LADW", 3, 10, "07/02/2020");
		BigDecimal expected = new BigDecimal("5.37");
		//1.99 per day no holiday charge
		//2 weekdays 1 weekend = 3 billable days
		//5.97 prediscount amount
		//0.60 discount amount
		//5.37 final charge
		
		assertTrue(result.finalCharge.compareTo(expected) == 0);
	}

}
