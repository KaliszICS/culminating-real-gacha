import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static Account login() {
        Account account = null;;
        System.out.print("Please enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String inputPassword = scanner.nextLine();
        BufferedReader accFile = null;
        try {
            accFile = new BufferedReader(new FileReader(username + ".cha"));
            String password = accFile.readLine();
            if (password.equals(inputPassword)) {
                System.out.println("Welcome back " + username + "!");
                boolean[] playerCharacterUnlocked = convertToBoolArr(accFile.readLine());
                boolean[] weaponInv = convertToBoolArr(accFile.readLine());
                int gachaCurrency = Integer.parseInt(accFile.readLine());
                int shopCurrency = Integer.parseInt(accFile.readLine());
                account = new Account(username, password, playerCharacterUnlocked, weaponInv, gachaCurrency, shopCurrency);
            }  else {
                System.out.println("Incorrect login info.");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            scanner.close();
            if (accFile != null) {
                try {
                    accFile.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        return account;
    }

    public static Account signup() {
        Account account = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Please enter your password again: ");
        String confirmPass = scanner.nextLine();
        if (password.equals(confirmPass)) {
            System.out.println("Welcome " + username + "!");
            account = new Account(username, password);
        } else {
            System.out.println("Second password didn't match first, account not made.");
        }
        scanner.close();
        return account;
    }

    public static void gameStart(Account account) {
        Home home = new Home(account);
        Scanner scanner = new Scanner(System.in);
        while (home.isPlaying()) {
            System.out.println("What do you want to do?\n");
            System.out.println("1 - Battle");
            System.out.println("2 - Shop");
            System.out.println("3 - Change Team");
            System.out.println("4 - Character Gacha");
            System.out.println("5 - Weapon Gacha");
            System.out.println("6 - Logout\n");
            System.out.print("Please enter your choice: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        home.battle();
                        break;
                    case 2:
                        home.shop();
                        break;
                    case 3:
                        home.changeTeam();
                        break;
                    case 4:
                        home.characterGacha();
                        break;
                    case 5:
                        home.weaponGacha();
                        break;
                    case 6:
                        home.logout();
                        break;
                }
            }
        }
        scanner.close();
    }

    private static boolean[] convertToBoolArr(String toConvert) {
        boolean[] arr = new boolean[toConvert.length()];
        for (int i = 0; i < toConvert.length(); i++) {
            arr[i] = toConvert.charAt(i) == '1';
        }
        return arr;
    }
}
