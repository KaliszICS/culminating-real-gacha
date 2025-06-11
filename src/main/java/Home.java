import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Home {
    // update manually with every character
    
    public final PlayerCharacter[] PLAYER_CHARACTER_LIST = new PlayerCharacter[]{
    new PlayerCharacter(0, "Diting", "Woof!", 2934, 102, 300, 4, 50, 2, 1, "Damage"), 
    new PlayerCharacter(1, "Insect Guy", "Mm… It’s Gregor. Well, feel free to talk to me unless I’m asleep.", 3120, 149, 250, 4, 50, 3, 5, "Damage"),
    new PlayerCharacter(2, "Ika", "Miewwww… Boing", 4673, 122, 530, 4, 100, 2, 3, "Heal"),
    new PlayerCharacter(3, "Lighting", "HHHHHeeeeeeeeellllllllllllllllllllllllllllllllllllllllooooooooooooo", 6235, 1, 1000, 5, 0, 1, 1, "Damage"),
    new PlayerCharacter(4, "Anaxa", "Rule number one: Do not call me Anaxa. Rule number two: Never interrupt me — silence is golden. Remember that.", 2800, 97, 756, 5, 75, 2, 5, "Damage"),
    new PlayerCharacter(5, "Discord", "Make sense? Oh, what fun is there in making sense?", 5341, 92, 329, 5, 50, 2, 5,  "Push", 150),
    new PlayerCharacter(6, "Rosalina", "May the stars shine down on you...", 3500, 120, 300, 5, 75, 2, 1, "Pull", 130),
    new PlayerCharacter(7, "The Ventriloquist", "Its suffering that brings meaning to the futile life", 3450, 100, 250, 5, 35, 3, 3, "Push", 70),
    new PlayerCharacter(8, "Shamita", ":blushing smilie-face emoji:", 5342, 125, 500, 5, 50, 3, 3, "Push")
};

    public final Weapon[] WEAPONS = new Weapon[]{
        new Weapon("Halibut", 100, 400, "Heal", "Take a bite out of this weapon and you can feel fulfilled. It doesn’t taste great though", 4, 0),
        new Weapon("Bow", 150, 300, "CritDamage", "Wow, a dog using a bow, who could’ve seen that coming.", 4, 1), 
        new Weapon("Stick", 200, 100, "CritChance", "I'm gonna beat you wiht a STICK!!!", 4, 2),
        new Weapon("Stone", 200, 100, "CritDamage", "Sticks and stones may break my bones but ouch stop hitting me", 4, 3),
        new Weapon("Hiking Stick", 100, 100, "Speed", "I’m a stick, but I make you go fast!", 4, 4),
        new Weapon("Flashlight", 400, 950, "CritChance", "If they can’t see me then I have advantage hehe", 5, 5),
        new Weapon("Magical Key", 450, 300, "CritDamage", "Key which hides the powers of the star, Reveal your true form before me. I, Sakura Kinomoto, command you under our contract. Release!", 5, 6),
        new Weapon("Magical gun", 500, 50, "Speed", "If there is a next life, you should really work out", 5, 7),
    };
    private Account account;
    private Shop shop;
    private boolean playing;
    private Gacha gacha;

    public Home(Account account) {
        this.account = account;
        this.playing = true;
        this.shop = new Shop();
        this.gacha = new Gacha(PLAYER_CHARACTER_LIST, WEAPONS);
    }

    public void characterGacha() {
        System.out.println("You currently have : "+ account.getGachaCurrency() + " Gacha Currencies");
        System.out.println("How many times do you want to pull? (160 Gacha Currency per pull)");
        int choice = 0;
        while (choice==0){
        if (Main.scanner.hasNextInt()) {
                choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
        }
        else{
            Main.scanner.nextLine();
        }
        }
        if (account.getGachaCurrency()-choice*160 > account.getGachaCurrency()){
            System.out.println("You do not have enough Gacha Currency: "+ account.getGachaCurrency() + ", Gacha cancelled");
        }
        else{
            account.setGachaCurrency(account.getGachaCurrency()-choice*160);
            PlayerCharacter[] pulled = getGacha().pullCharacter(choice);
            for (int i = 0; i<choice; i++){
                if (account.getPlayerCharacterUnlocked()[pulled[i].getOwnedIndex()]==false){
                    account.getPlayerCharacterUnlocked()[pulled[i].getOwnedIndex()]=true;
                }
                else{
                    System.out.println("You have " + pulled[i].getName() + " already, shopCurrency +" + gacha.REPEAT_COMPENSATION);
                    account.setShopCurrency(account.getShopCurrency() + gacha.REPEAT_COMPENSATION);
                }
            }
        }
    }
    
    public void weaponGacha() {
        System.out.println("You currently have : "+ account.getGachaCurrency() + " Gacha Currencies");
        System.out.println("How many times do you want to pull? (160 Gacha Currency per pull)");
        int choice = 0;
        while (choice==0){
        if (Main.scanner.hasNextInt()) {
                choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
        }
        else{
            Main.scanner.nextLine();
        }
        }
        if (account.getGachaCurrency()-choice*160 > account.getGachaCurrency()){
            System.out.println("You do not have enough Gacha Currency: "+ account.getGachaCurrency() + ", Gacha cancelled");
        }
        else{
            account.setGachaCurrency(account.getGachaCurrency()-choice*160);
        Weapon[] pulled = getGacha().pullWeapon(choice);
        for (int i = 0; i<choice; i++){
            if (account.getWeaponsUnlocked()[pulled[i].getOwnedIndex()]==false){
                account.getWeaponsUnlocked()[pulled[i].getOwnedIndex()]=true;
            }
            else{
                System.out.println("You have " + pulled[i].getName() + " already, shopCurrency +" + gacha.REPEAT_COMPENSATION);
                account.setShopCurrency(account.getShopCurrency() + gacha.REPEAT_COMPENSATION);
            }
        }
        }
    }

    public void battle() {
        boolean hasCharacter = false;
        for (int i = 0; i<4; i++){//4 is team size
            if (account.getTeam().getOnTeam()[i]!=null){
                hasCharacter = true;
            }
        }
        if (!hasCharacter){// all are null
            System.out.println("Your team is empty, you cannot engage in battle.");
            return;
        }
        Battle battle = new Battle(account.getTeam().getOnTeam());
        battle.startBattle();
        account.setGachaCurrency(account.getGachaCurrency() + battle.getBattleReward());
        for (int i = 0; i<account.getTeam().getOnTeam().length;i++){
            if (account.getTeam().getOnTeam()[i].getHp()<=0){
            account.getTeam().getOnTeam()[i].setActionPoints((int)(account.getTeam().getOnTeam()[i].getSpeed()*1.25+100));
            account.getTeam().getOnTeam()[i].setHp(1);
            }
        }
    }

    public void shop(Account account) {

        System.out.println("\nWelcome to the shop!");
        System.out.println("Shop Currency: " + account.getShopCurrency() + "\n");

        if (shop.getItemsOnSale().size()>0) {
            System.out.println("Here are the items currently on sale:");
            for (int i=0; i<shop.getItemsOnSale().size(); i++) {
                System.out.println(shop.getItemsOnSale().get(i).getName());
            }
            System.out.println("");
        }

        else {
            System.out.println("Buy a refresh for 150 shop currency to see what new items are on sale!\n");
        }
        
        boolean choosing = true;

		while (choosing) {
			System.out.println("What would you like to do?");
            System.out.println();
            System.out.println("You currently have : "+ account.getGachaCurrency() + "Gacha Currencies");
            System.out.println();
			System.out.println("1 - Refresh Items on Sale (Cost: 150 shop currency)");
            if (shop.getItemsOnSale().size()>0) {
			    System.out.println("2 - Buy Item (Will be applied to each charatcer currently on your team)");
            }
			System.out.println("3 - Exit");
			System.out.println("\nPlease enter your choice: ");

			if (Main.scanner.hasNextInt()) {
				int choice = Main.scanner.nextInt();
				Main.scanner.nextLine();

                    switch (choice) {
                        case 1:
                            shop.refresh(account);
                            break;
                        
                        case 2:
                            if (shop.getItemsOnSale().size()>0) {
                                System.out.println("Which item would you like to buy?\n");

                                for (int i=0; i<shop.getItemsOnSale().size(); i++) {
                                    System.out.println(i+1 + " - " + shop.getItemsOnSale().get(i).getName());
                                }

                                System.out.println("\nPlease enter your choice: ");

                                if (Main.scanner.hasNextInt()) {
                                    int buyChoice = Main.scanner.nextInt();
                                    Main.scanner.nextLine();

                                    //buyChoice is the item index +1
                                    if (buyChoice>=1 && buyChoice<=shop.getItemsOnSale().size()) {
                                        shop.buy(shop.getItemsOnSale().get(buyChoice-1), account);
                                        break;                                      
                                    }

                                    else {
                                        System.out.println("\nInvalid choice.\n");
                                    }
                                }
                                else {
                                    Main.scanner.next();
                                }
                            }
                            else {
                                System.out.println("\nThere are currently no items on sale.\n");
                            }
                            break;
                        
                        case 3:
                            choosing = false;
                            break;

					default:
						System.out.println("Invalid choice.");
				}
			}
            
            else {
				Main.scanner.next();
			}
		}
    }

    public void viewAccount() {
        System.out.println(account.toString());
    }

    public void logout() {
        System.out.println("Your characters will go back home, leaving behind their weapon and team.\nDon't forget to reassign them once you come back!");
        setPlaying(false);
        save();
    }

    public void changeTeam() {
        boolean choosing = true;
        while (choosing){
            System.out.println(account.getTeam().toString());
            boolean canAdd = account.getTeam().getNumCharactersOnTeam() < 4;
            boolean canRemove = account.getTeam().getNumCharactersOnTeam() > 0;
            int exitOption = 1;
            int removeOption = 1;
            if (canAdd) {
                exitOption++;
                removeOption++;
                System.out.println("1: Add a character to team");
            }
            if (canRemove) {
                exitOption += 2;
                System.out.println(removeOption + ": Remove a character from team");
                System.out.println((removeOption + 1) + ": Equip weapons");
            }
            System.out.println(exitOption + ": Exit");
            if (Main.scanner.hasNextInt()) {
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
                if (choice == exitOption) {
                    choosing = false;
                } else if (choice ==1){
                    if (account.getTeam().getNumCharactersOnTeam()!=4){
                        //does not do anything
                        ArrayList<PlayerCharacter> applicablePC= new ArrayList<>();
                        for (int i = 0; i<PLAYER_CHARACTER_LIST.length;i++){
                            if (account.getPlayerCharacterUnlocked()[i]){
                                applicablePC.add(PLAYER_CHARACTER_LIST[i]);
                            }
                        }
                        for (int i =0; i<account.getTeam().getOnTeam().length; i++){
                            if (account.getTeam().getOnTeam()[i]!=null){
                                applicablePC.remove(account.getTeam().getOnTeam()[i]);
                            }
                        }
                        for (int i = 0; i<applicablePC.size(); i++){
                            System.out.println("Index " + i + ": " + applicablePC.get(i).detailedToString());
                        }
                        if (applicablePC.size()==0){
                            System.out.println("You have no more characters to add");
                            choosing = false;
                        }
                        else{
                            System.out.println("Choose a character to add, it will be added to the first open slot: ");
                        }
                        while (choosing){
                            if (Main.scanner.hasNextInt()){
                                int characterToAdd = Main.scanner.nextInt();
                                Main.scanner.nextLine();    
                                if (characterToAdd>-1 && characterToAdd<applicablePC.size()){                    
                                    account.getTeam().addToTeam(applicablePC.get(characterToAdd));
                                    applicablePC.remove(characterToAdd);
                                }
                                else{
                                    System.out.println("Input invalid.");
                                }
                            }
                            else{
                                Main.scanner.next();
                            }
                            choosing = false;
                        }
                        choosing = true;
                    }
                }
                else if (choice ==2){
                    if (account.getTeam().getNumCharactersOnTeam()!=0){
                        //does not do anything
                        System.out.println("Choose a character to remove: ");
                        while (choosing){
                            if (Main.scanner.hasNextInt()){
                                int characterToRemove = Main.scanner.nextInt();
                                Main.scanner.nextLine();
                                if (characterToRemove>=0&&characterToRemove<=3){
                                    if (account.getTeam().getOnTeam()[characterToRemove]!=null){
                                        account.getTeam().removeFromTeam(characterToRemove);
                                        choosing = false;
                                    }
                                }
                                else{
                                    System.out.println("Please enter a valid number");
                                }
                            }
                            else{
                                Main.scanner.next();
                            }
                        }
                        choosing = true;
                    }
                } else if (choice == 3 && canRemove) {
                    equipWeapons();
                }
                else{
                    Main.scanner.next();
                }
            }
             else {
                 Main.scanner.next();
             }
        }
    }

    private void save() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(getAccount().getUserName() + ".cha"));
            writer.write(getAccount().getPassword() + "\n");
            writer.write(convertBoolArrToString(getAccount().getPlayerCharacterUnlocked()));
            writer.write(convertBoolArrToString(getAccount().getWeaponsUnlocked()));
            writer.write(getAccount().getGachaCurrency() + "\n");
            writer.write(getAccount().getShopCurrency() + "\n");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
    
    public Account getAccount() {
        return this.account;
    }

    public Shop getShop() {
        return this.shop;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Gacha getGacha() {
        return this.gacha;
    }

    private void equipWeapons() {
        boolean choosing = true;
        PlayerCharacter[] team = getAccount().getTeam().getOnTeam();
        while (choosing) {
            String characterWeapons = "";
            characterWeapons += "Equipped weapons:\n";
            int charCount = 0;
            for (int i = 0; i < team.length; i++) {
                if (team[i] != null) {
                    charCount++;
                    characterWeapons += charCount + " - " + team[i].getName() + " Equipped: ";
                    if (team[i].getWeaponEquipped() == null) {
                        characterWeapons += "None\n";
                    } else {
                        characterWeapons += team[i].getWeaponEquipped().getName() + "\n";
                    }
                }
            }
            System.out.println("What would you like to do?");
            System.out.println("1: Equip weapon");
            System.out.println("2: Unequip weapon");
            System.out.println("3: Exit");
            int choice = 0;
            if (Main.scanner.hasNextInt()) {
                choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
                int character = 0;
                switch (choice) {
                    case 1:
                        System.out.println(characterWeapons);
                        System.out.print("Please enter the number of the character you want to equip: ");
                        if (Main.scanner.hasNextInt()) {
                            character = Main.scanner.nextInt();
                            Main.scanner.nextLine();
                            if (character < 1 || character > charCount) {
                                System.out.println("Invalid choice.");
                            } else {
                                System.out.println("Available weapons:");
                                int weapCount = 0;
                                boolean foundWeapon = false;
                                for (int i = 0; i < WEAPONS.length; i++) {
                                    if (getAccount().getWeaponsUnlocked()[i]) {
                                        foundWeapon = true;
                                        weapCount++;
                                        System.out.println(weapCount + ": " + WEAPONS[i].toString());
                                    }
                                }
                                if (foundWeapon) {
                                    System.out.print("Please enter the number of the weapon you want to equip: ");
                                    int weaponNum = 0;
                                    if (Main.scanner.hasNextInt()) {
                                        weaponNum = Main.scanner.nextInt();
                                        Main.scanner.nextLine();
                                        if (weaponNum < 1 || weaponNum > weapCount) {
                                            System.out.println("Invalid choice.");
                                        } else {
                                            Weapon weapon = null;
                                            int currWeapon = 0;
                                            for (int i = 0; i < WEAPONS.length && currWeapon < weaponNum; i++) {
                                                if (getAccount().getWeaponsUnlocked()[i]) {
                                                    currWeapon++;
                                                    if (currWeapon == weaponNum) {
                                                        weapon = WEAPONS[i];
                                                    }
                                                }
                                            }
                                            if (weapon.getEquippedTo() != null) {
                                                System.out.println("Weapon already equipped to someone.");
                                            } else {
                                                int actualCharacterIndex = 0;
                                                PlayerCharacter actualCharacter = null;
                                                for (int i = 0; i < team.length &&actualCharacterIndex < character; i++) {
                                                    if (team[i] != null) {
                                                        actualCharacterIndex++;
                                                        if (actualCharacterIndex == character) {
                                                            actualCharacter = team[i];
                                                        }
                                                    }
                                                }
                                                actualCharacter.setWeaponEquipped(weapon);
                                                weapon.setEquippedTo(actualCharacter);
                                                System.out.println("Weapon equipped successfully.");
                                            }
                                        }
                                    } else {
                                        System.out.println("Invalid choice.");
                                        Main.scanner.next();
                                    }
                                } else {
                                    System.out.println("No weapons found.");
                                }
                            }
                        } else {
                            Main.scanner.next();
                        }
                        break;
                    case 2:
                        System.out.println(characterWeapons);
                        System.out.print("Please enter the number of the character you want to unequip: ");
                        if (Main.scanner.hasNextInt()) {
                            character = Main.scanner.nextInt();
                            Main.scanner.nextLine();
                            if (character < 1 || character > charCount) {
                                System.out.println("Invalid choice.");
                            } 
                            else {
                                int actualCharacterIndex = 0;
                                PlayerCharacter actualCharacter = null;
                                for (int i = 0; i < team.length && actualCharacterIndex < character; i++) {
                                    if (team[i] != null) {
                                        actualCharacterIndex++;
                                        if (actualCharacterIndex == character) {
                                            actualCharacter = team[i];
                                        }
                                    }
                                }
                                if (actualCharacter.getWeaponEquipped()!=null){
                                    actualCharacter.getWeaponEquipped().setEquippedTo(null);
                                }
                                actualCharacter.setWeaponEquipped(null);
                                System.out.println("Weapon unequipped.");
                            }
                        } else {
                            System.out.println("Invalid choice.");
                            Main.scanner.next();
                        }
                        break;
                    case 3:
                        choosing = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } else {
                System.out.println("Invalid choice.");
                Main.scanner.next();
            }
        }
    }

    private String convertBoolArrToString(boolean[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                str += "1";
            } else {
                str += "0";
            }
        }
        return str + "\n";
    }
}
