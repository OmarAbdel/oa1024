package objects;

import java.math.BigDecimal;

public abstract class Tool {
	public String toolCode;
	public String brand;
	public String type;
	public BigDecimal dailyCharge;
	public boolean weekdayCharge;
	public boolean weekendCharge;
	public boolean holidayCharge;
	
	public Tool(String toolCode, String brand, String type) {
		this.toolCode = toolCode;
		this.brand = brand;
		this.type = type;
	}
}
