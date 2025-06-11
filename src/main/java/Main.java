import java.util.Scanner;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static void main(String args[]) {
		boolean choosing = true;
		Account account = null;
		while (choosing) {
			System.out.println("What would you like to do?");
			System.out.println("1 - Login");
			System.out.println("2 - Sign up");
			System.out.println("3 - Exit");
			System.out.print("Please enter your choice: ");
			if (scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
					case 1:
						account = Login.login();
						if (account!=null){
							choosing = false;
						}
						break;
					case 2:
						account = Login.signup();
						if (account!=null){
							choosing = false;
						}
						break;
					case 3:
						System.out.println("Goodbye!");
						scanner.close();
						return;
					default:
						System.out.println("Invalid choice.");
				}
			} else {
				scanner.next();
			}
		}
		
		Login.gameStart(account);
		scanner.close();
	}
}