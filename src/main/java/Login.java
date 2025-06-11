import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    public static Account login() {
        Account account = null;;
        System.out.print("Please enter your username: ");
        String username = Main.scanner.nextLine();
        System.out.print("Please enter your password: ");
        String inputPassword = Main.scanner.nextLine();
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
        } 
        catch (FileNotFoundException e) {
            System.out.println("Account does not exist");
        } 
        catch (IOException e) {
            System.out.println(e);
        } 
        finally {
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
        System.out.print("Please enter your username: ");
        String username = Main.scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = Main.scanner.nextLine();
        System.out.print("Please enter your password again: ");
        String confirmPass = Main.scanner.nextLine();
        if (confirmPass.equals(password) && !username.equals("") && !password.equals("")) {
            System.out.println("Welcome " + username + "!");
            account = new Account(username, password);
        } 
        else if (username.equals("") || password.equals("")){
            System.out.println("Some areas are blank, account not made.");
        }
        else{
            System.out.println("Second password didn't match first, account not made.");
        }
        return account;
    }

    public static void gameStart(Account account) {
        Home home = new Home(account);
        while (home.isPlaying()) {
            System.out.println("What do you want to do?\n");
            System.out.println("1 - Battle");
            System.out.println("2 - Shop");
            System.out.println("3 - Change Team");
            System.out.println("4 - Character Gacha");
            System.out.println("5 - Weapon Gacha");
            System.out.println("6 - View account");
            System.out.println("7 - Logout\n");
            System.out.print("Please enter your choice: ");
            if (Main.scanner.hasNextInt()) {
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
                switch (choice) {
                    case 1:
                        home.battle();
                        break;
                    case 2:
                        home.shop(account);
                        break;
                    case 3:
                        home.changeTeam();
                        break;
                    case 4:
                        home.characterGacha();
                        break;
                    case 5:
                        boolean ownCharacter = false;
                        for (int i = 0; i < account.getPlayerCharacterUnlocked().length; i++) {
                            ownCharacter = account.getPlayerCharacterUnlocked()[i] || ownCharacter;
                        }
                        if (ownCharacter) {
                            home.weaponGacha();
                        } else {
                            System.out.println("You don't have any characters unlocked. Roll for a character first!");
                        }
                        break;
                    case 6:
                        home.viewAccount();
                        break;
                    case 7:
                        home.logout();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid choice.");
                Main.scanner.next();
             }
        }
    }

    private static boolean[] convertToBoolArr(String toConvert) {
        boolean[] arr = new boolean[toConvert.length()];
        for (int i = 0; i < toConvert.length(); i++) {
            arr[i] = toConvert.charAt(i) == '1';
        }
        return arr;
    }
}
