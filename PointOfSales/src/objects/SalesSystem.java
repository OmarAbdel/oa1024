package objects;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class SalesSystem {
	public HashMap<String, Tool> tools;
	
	//Default tool list
	public SalesSystem() {
		HashMap<String, Tool> toolSet = new HashMap<>();
		toolSet.put("CHNS", new Chainsaw("CHNS", "Stihl"));
		toolSet.put("LADW", new Ladder("LADW", "Werner"));
		toolSet.put("JAKD", new JackHammer("JAKD", "DeWalt"));
		toolSet.put("JAKR", new JackHammer("JAKR", "Ridgid"));
		this.tools = toolSet;
	}
	
	//Constructor using given list of tools
	public SalesSystem(HashMap<String, Tool> toolSet) {
		this.tools = toolSet;
	}
	
	public void requestRentalInterface(Scanner scanner) {
		displayTools();
		String toolCode = getToolCode(scanner);
		LocalDate checkoutDate = getRentalStart(scanner);
		int rentalLength = getRentalLength(scanner);
		int discountPercent = getRentalDiscount(scanner);
		
		RentalAgreement rental = checkout(toolCode, rentalLength, discountPercent, checkoutDate);
		rental.printAgreement();
	}
	
	//Checkout method that takes in a string for the checkout date
	public RentalAgreement checkout(String toolCode, int rentalLength, int discountPercent, String checkoutDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		formatter = formatter.withLocale(Locale.US);
		LocalDate date = LocalDate.parse(checkoutDate, formatter);
		return checkout(toolCode, rentalLength, discountPercent, date);	
	}
	
	//Checkout method that uses a 
	public RentalAgreement checkout(String toolCode, int rentalLength, int discountPercent, LocalDate checkoutDate) {
		if(rentalLength < 1) {
			System.out.println("Rental length must be at least 1 day.");

			throw new IllegalArgumentException();
		}
		if(discountPercent < 0 || discountPercent > 100) {
			System.out.println("Discount percent must be between 1 and 100.");
			throw new IllegalArgumentException();
		}
		
		RentalAgreement rental = new RentalAgreement(toolCode, rentalLength, discountPercent, checkoutDate);
		rental.tool = tools.get(toolCode);
		
		int billableDays = getBillableDays(rental);

		rental.generateBillingValues(billableDays);
		
		return rental;
	}
	
	private int getBillableDays(RentalAgreement rental) {
		int weekdays = 0;
		int weekends = 0;
		int holidays = 0;
		
		LocalDate day = rental.checkoutDate;
		int year = day.getYear();
		LocalDate laborDay = getLaborDay(year);
		LocalDate julyFourth = getJulyFourth(year);
		
		
		for(int i = 0; i < rental.rentalLength; i++) {
			if(year != day.getYear()) {
				year ++;
				laborDay = getLaborDay(year);
				julyFourth = getJulyFourth(year);
			}
			
			if(day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY) weekends++;
			else {
				if(day.isEqual(julyFourth) || day.isEqual(laborDay)) {
					holidays++;
				} else weekdays++;
			}
			day = day.plusDays(1);
		}
		
		int billableDays = 0;
		if(rental.tool.weekdayCharge) billableDays += weekdays;
		if(rental.tool.weekendCharge) billableDays += weekends;
		if(rental.tool.holidayCharge) billableDays += holidays;
		
		return billableDays;
	}
	
	private LocalDate getLaborDay(int year) {
		Calendar laborDay = Calendar.getInstance();
		laborDay.set(Calendar.YEAR, year);
		laborDay.set(Calendar.MONTH, Calendar.SEPTEMBER);
		laborDay.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		laborDay.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		return LocalDateTime.ofInstant(laborDay.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}
	
	private LocalDate getJulyFourth(int year) {
		Calendar julyFourth = Calendar.getInstance();
		julyFourth.set(Calendar.YEAR, year);
		julyFourth.set(Calendar.MONTH, Calendar.JULY);
		julyFourth.set(Calendar.DAY_OF_MONTH, 4);
		while(julyFourth.get(Calendar.DAY_OF_WEEK) == 1 || julyFourth.get(Calendar.DAY_OF_WEEK) == 7) julyFourth.add(Calendar.DATE, 1);
		return LocalDateTime.ofInstant(julyFourth.toInstant(), ZoneId.systemDefault()).toLocalDate();		
	}

	//Optional code for a user interface
	private void displayTools() {
		System.out.println("Welcome to the tool shop. Here is our current stock");
		System.out.printf("%-6s%-15s%-10s\n", "Code", "Type", "Brand");
		Tool tool;
		for(String key : tools.keySet()) {
			tool = tools.get(key);
			System.out.printf("%-6s%-15s%-10s\n", tool.toolCode, tool.type, tool.brand);
		}
		System.out.println();		
	}
	
	private String getToolCode(Scanner scanner) {
		String toolCode;
		
		while(true) {
			System.out.println("Enter tool code: ");
			toolCode = scanner.next();
			if(tools.containsKey(toolCode)) {
				return toolCode;
			}
			System.out.println("Given tool code not found in system.");
		}
	}
	
	private LocalDate getRentalStart(Scanner scanner) {
		String rentalDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		while(true) {
			System.out.println("Enter rental start date mm/dd/yyyy: ");
			rentalDate = scanner.next();
			LocalDate startDate;
			try {
				startDate = LocalDate.parse(rentalDate, formatter);
				//Extra code to check if start date is before today
				if(startDate.isBefore(LocalDate.now())){
					System.out.println("Please enter a date today or later.");
				} else {
					return startDate;
				}
			} catch (RuntimeException e) {
				System.out.println("Error reading rental start date. Please try again.");
			}

		}
		
	}
	
	private int getRentalLength(Scanner scanner) {
		int days;
		
		while(true) {
			System.out.println("Enter the number of days you want to rent for (must be greater than 0): ");
			try {
				days = scanner.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("Rental length must be a whole number.");
				scanner.next();
				continue;
			}
			if(days < 1) {
				System.out.println("Rental length must be greater than 0.");
			}  else {
				return days;
			}
		}
	}
	
	private int getRentalDiscount(Scanner scanner) {
		int discount;
		
		while(true) {
			System.out.println("Enter the discount percentage for the rental (whole number 0-100): ");
			try {
				discount = scanner.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("Discount percentage must be a whole number.");
				scanner.next();
				continue;
			}
			if(discount < 0 || discount > 100) {
				System.out.println("Discount percentage must be a whole number between 0 and 100.");
			}  else {
				return discount;
			}
		}
	}
}
