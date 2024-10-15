package objects;

import java.math.BigDecimal;

public class Ladder extends Tool {

	public Ladder(String toolCode, String brand) {
		super(toolCode, brand, "Ladder");
		this.dailyCharge = new BigDecimal(1.99);
		this.weekdayCharge = true;
		this.weekendCharge = true;
		this.holidayCharge = false;
	}
	
}
