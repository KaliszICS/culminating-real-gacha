import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		boolean choosing = true;
		Scanner scanner = new Scanner(System.in);
		Account account = null;
		while (choosing) {
			System.out.println("What would you like to do?");
			System.out.println("1 - Login");
			System.out.println("2 - Sign up");
			System.out.println("3 - Exit");
			System.out.print("Please enter your choice: ");
			if (scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				switch (choice) {
					case 1:
						account = Login.login();
						break;
					case 2:
						account = Login.signup();
						break;
					case 3:
						System.out.println("Goodbye!");
						scanner.close();
						return;
					default:
						System.out.println("Invalid choice.");
				}
			}
		}
		scanner.close();
		Login.gameStart(account);
	}

}
