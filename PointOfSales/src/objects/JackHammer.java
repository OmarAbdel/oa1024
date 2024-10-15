package objects;

import java.math.BigDecimal;

public class JackHammer extends Tool {
	
	public JackHammer(String toolCode, String brand) {
		super(toolCode, brand, "JackHammer");
		this.dailyCharge = new BigDecimal(2.99);
		this.weekdayCharge = true;
		this.weekendCharge = false;
		this.holidayCharge = false;
	}
	
}