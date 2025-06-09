public class Home {
    // update manually with every character
    
    public final PlayerCharacter[] PLAYER_CHARACTER_LIST = new PlayerCharacter[]{
    new PlayerCharacter("Diting", "Woof!", 2934, 102, 300, 4, 50, 2, 1, "Damage"), 
    new PlayerCharacter("Insect Guy", "Mm… It’s Gregor. Well, feel free to talk to me unless I’m asleep.", 3120, 149, 250, 4, 50, 3, 5, "Damage"),
    new PlayerCharacter("Ika", "Miewwww… Boing", 4673, 122, 530, 4, 100, 2, 3, "Heal"),
    new PlayerCharacter("Lighting", "HHHHHeeeeeeeeellllllllllllllllllllllllllllllllllllllllooooooooooooo", 6235, 1, 1000, 5, 0, 0, 1, "Damage"),
    new PlayerCharacter("Anaxa", "Rule number one: Do not call me Anaxa. Rule number two: Never interrupt me — silence is golden. Remember that.", 2800, 97, 756, 5, 75, 2, 5, "Damage"),
    new PlayerCharacter("Discord", "Make sense? Oh, what fun is there in making sense?", 5341, 92, 329, 5, 50, 2, 5,  "Push", 150),
    new PlayerCharacter("Rosalina", "May the stars shine down on you...", 3500, 120, 300, 5, 75, 2, 1, "Pull"),
    new PlayerCharacter("The Ventriloquist", "Its suffering that brings meaning to the futile life", 3450, 100, 250, 5, 35, 3, 3, "Push", 70)
};

    public final Weapon[] WEAPONS = new Weapon[0];
    public final Enemy[] ENEMIES = new Enemy[0];
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

    public PlayerCharacter[] characterGacha() {
        // would probably be better to do user input for pulls in here, it's already messy as hell in gamestart for login
        return null;
    }
    
    public Weapon[] weaponGacha() {
        // read above comment
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
