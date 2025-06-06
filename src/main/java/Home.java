public class Home {
    // update manually with every character
    public final PlayerCharacter[] PLAYER_CHARACTER_LIST = new PlayerCharacter[0];
    private Account account;
    private Shop shop;
    // this could prob be a constant i'm not sure if it's every weapon in the game
    private Weapon[] weapons;

    public Home(Account account, Shop shop, Weapon[] weapons) {
        this.account = account;
        this.shop = shop;
        this.weapons = weapons;
    }

    public PlayerCharacter[] characterGacha(int gachaCurrency) {
        // TODO: feel like gacha currency could honestly just be number of pulls instead but ok
        return null;
    }
    
    public Weapon[] weaponGacha(int gachaCurrency) {
        // TODO: gacha currency probably number of pulls instead
        return null;
    }

    public void inventory(Weapon[] weaponInv) {
        // TODO: hi hello what this do
    }

    public void battle() {
        // TODO: the battle class methods are a mess i don't even know how to begin writing this one
    }

    public void shop() {
        // TODO: no idea how to start someone explain how do shop
    }

    public void viewAccount() {
        // TODO: make the account tostring to use here, no idea how it's formatted yet
    }

    public boolean logout() {
        // TODO: why boolean return?
        return false;
    }

    public String filterDisplayedCharacter(int rarity) {
        // i forgot if this is needed for actually getting the playercharacter objects
        String characters = "Characters:\n";
        for (int i = 0; i < PLAYER_CHARACTER_LIST.length; i++) {
            if (getAccount().getPlayerCharacterUnlocked()[i] && PLAYER_CHARACTER_LIST[i].getRarity() == rarity) {
                characters += PLAYER_CHARACTER_LIST[i].getName() + "\n";
            }
        }
        return characters;
    }

    public void changeTeam() {
        // TODO: figure out parameters for this
    }

    private void save() {
        // TODO: figure out file saving later
    }
    
    public Account getAccount() {
        return account;
    }

    public Shop getShop() {
        return shop;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }
}
