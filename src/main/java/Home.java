public class Home {
    // update manually with every character
    public final PlayerCharacter[] PLAYER_CHARACTER_LIST = new PlayerCharacter[]{
        
    };
    public final Weapon[] WEAPONS = new Weapon[0];
    public final Enemy[] ENEMIES = new Enemy[0];
    private Account account;
    private Shop shop;
    private boolean playing;
    private Gacha gacha;

    public Home(Account account, Shop shop, Weapon[] weapons, Gacha gacha) {
        this.account = account;
        this.shop = shop;
        this.gacha = gacha;
    }

    public PlayerCharacter[] characterGacha(int numOfPulls) {
        return null;
    }
    
    public Weapon[] weaponGacha(int numOfPulls) {
        return null;
    }

    public void battle() {
        // TODO: figure it out
    }

    public void shop() {
        // TODO: figure it out
    }

    public void viewAccount() {
        // TODO: make the account tostring to use here, no idea how it's formatted yet
    }

    public void logout() {
        setPlaying(false);
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
}
