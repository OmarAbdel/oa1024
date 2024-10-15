package objects;

import java.math.BigDecimal;

public class Chainsaw extends Tool {

	public Chainsaw(String toolCode, String brand) {
		super(toolCode, brand, "Chainsaw");
		this.dailyCharge = new BigDecimal("1.49");
		this.weekdayCharge = true;
		this.weekendCharge = false;
		this.holidayCharge = true;
	}
	
}