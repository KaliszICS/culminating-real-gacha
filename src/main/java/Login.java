import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class representing a login menu for the game.
 * @version 1.0.0
 * @author gacha
 */
public class Login {
    /**
     * Shouldn't be used, private constructor.
     */
    private Login() {
    }

    /**
     * A login page for the game. Uses user input to get a username and password, checks if an account with that username and password exists, and creates an account object from information in that file.
     * @return the resulting account object, or null if no account with given username and password exists
     */
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
                System.out.println("\nWelcome back " + username + "!");
                boolean[] playerCharacterUnlocked = convertToBoolArr(accFile.readLine());
                boolean[] weaponInv = convertToBoolArr(accFile.readLine());
                int gachaCurrency = Integer.parseInt(accFile.readLine());
                int shopCurrency = Integer.parseInt(accFile.readLine());
                account = new Account(username, password, playerCharacterUnlocked, weaponInv, gachaCurrency, shopCurrency);
            }

            else {
                System.out.println("\nIncorrect login info.");
            }
        } 

        catch (FileNotFoundException e) {
            System.out.println("\nAccount does not exist");
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

    /**
     * Gets a username and password from userinput, asks user to re-enter their password, and creates an account object with default values if password is re-entered successfully and username or password is not empty.
     * @return the resulting account object, or null if username or password is empty, or if password is not properly re-entered
     */
    public static Account signup() {
        Account account = null;
        System.out.print("Please enter your username: ");
        String username = Main.scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = Main.scanner.nextLine();
        System.out.print("Please enter your password again: ");
        String confirmPass = Main.scanner.nextLine();
        
        if (confirmPass.equals(password) && !username.equals("") && !password.equals("")) {
            System.out.println("\nWelcome " + username + "!");
            account = new Account(username, password);
        }

        else if (username.equals("") || password.equals("")) {
            System.out.println("\nSome areas are blank, account not made.");
        }

        else {
            System.out.println("\nSecond password didn't match first, account not made.");
        }

        return account;
    }

    /**
     * Acts as the main menu of the game. Gets user input for what operation to do, then runs the corresponding method from the Home class for that operation.
     * @param account the account used for game operations
     * @see Home
     */
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
                        }
                        else {
                            System.out.println("\nYou don't have any characters unlocked. Roll for a character first!");
                        }
                        break;

                    case 6:
                        home.viewAccount();
                        break;

                    case 7:
                        home.logout();
                        break;

                    default:
                        System.out.println("\nInvalid choice.");
                }

            }
            
            else {
                System.out.println("\nInvalid choice.");
                Main.scanner.next();
            }
        }
    }

    /**
     * Converts a string of 1s and 0s into a boolean array. 1 represents a true at that index in that boolean array, and 0 represents a false.
     * @param toConvert the string to be converted
     * @return the boolean array as a result of the conversion
     */
    private static boolean[] convertToBoolArr(String toConvert) {
        boolean[] arr = new boolean[toConvert.length()];
        
        for (int i = 0; i < toConvert.length(); i++) {
            arr[i] = toConvert.charAt(i) == '1';
        }

        return arr;
    }
}