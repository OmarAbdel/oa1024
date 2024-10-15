import java.util.Scanner;

import objects.SalesSystem;

public class Main {

	public static void main(String[] args) {
		SalesSystem sales = new SalesSystem();
		Scanner scanner = new Scanner(System.in);
		String input = "yes";

		while(input.equalsIgnoreCase("yes")) {
			sales.requestRentalInterface(scanner);
			System.out.println("Type 'yes' to place another order. ");
			input = scanner.next();
		}
		scanner.close();
		System.out.println("Enjoy your tools! Thanks for coming.");

	}

}
