package objects;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
	public String toolCode;
	public LocalDate checkoutDate;
	public int rentalLength;
	public int discountPercent;
	
	public int billableDays;
	public Tool tool;
	public BigDecimal preDiscount;
	public BigDecimal discountAmount;
	public BigDecimal finalCharge;
	public LocalDate returnDate;
	
	public RentalAgreement(String toolCode, int rentalLength, int discountPercent, LocalDate checkoutDate) {
		this.toolCode = toolCode;
		this.checkoutDate = checkoutDate;
		this.rentalLength = rentalLength;
		this.discountPercent = discountPercent;
	}
	
	public void generateBillingValues(int billableDays) {
		this.billableDays = billableDays;
		this.preDiscount = tool.dailyCharge.multiply(new BigDecimal(this.billableDays));
		this.preDiscount = this.preDiscount.setScale(2, RoundingMode.HALF_UP);
		this.discountAmount = this.preDiscount.multiply(new BigDecimal(discountPercent)).divide(new BigDecimal("100"));
		this.discountAmount = this.discountAmount.setScale(2, RoundingMode.HALF_UP);
		this.finalCharge = preDiscount.subtract(discountAmount);
		this.returnDate = checkoutDate.plusDays(rentalLength);	
	}
	
	public void printAgreement() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.println("Printing out rental agreement...");
		System.out.printf("%-20s%-15s\n", "Tool code: ", toolCode);
		System.out.printf("%-20s%-15s\n", "Tool type: ", tool.type);
		System.out.printf("%-20s%-15s\n", "Tool brand: ", tool.brand);
		System.out.printf("%-20s%-15s\n", "Rental length: ",  String.format("%,d", rentalLength) + " days");
		System.out.printf("%-20s%-15s\n", "Checkout date: ", checkoutDate.format(formatter));
		System.out.printf("%-20s%-15s\n", "Due date: ", returnDate.format(formatter));
		System.out.printf("%-20s%-15s\n", "Daily rental rate: ", "$" + String.format("%,.2f", tool.dailyCharge));
		System.out.printf("%-20s%-15s\n", "Charge days: ", String.format("%,d", billableDays));
		System.out.printf("%-20s%-15s\n", "Prediscount charge: ", "$" + String.format("%,.2f", preDiscount));
		System.out.printf("%-20s%-15s\n", "Discount percent: ", discountPercent + "%");
		System.out.printf("%-20s%-15s\n", "Discount amount:",  "$" + String.format("%,.2f", discountAmount));
		System.out.printf("%-20s%-15s\n", "Final charge: ",  "$" + String.format("%,.2f", finalCharge));
	}
}
