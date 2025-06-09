public class Account {
    private String userName;
    private String password;
    private boolean[] playerCharacterUnlocked;
    private boolean[] weaponsUnlocked;
    private int gachaCurrency;
    private int shopCurrency;
    private Team team;

    public Account(String userName, String password, boolean[] playerCharacterUnlocked, boolean[] weaponsUnlocked,
            int gachaCurrency, int shopCurrency) {
        this.userName = userName;
        this.password = password;
        this.playerCharacterUnlocked = playerCharacterUnlocked;
        this.weaponsUnlocked = weaponsUnlocked;
        this.gachaCurrency = gachaCurrency;
        this.shopCurrency = shopCurrency;
        this.team = new Team();
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.playerCharacterUnlocked = new boolean[8];
        this.weaponsUnlocked = new boolean[8];
        this.gachaCurrency = 10000000;
        this.shopCurrency = 0;
        this.team = new Team();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean[] getPlayerCharacterUnlocked() {
        return playerCharacterUnlocked;
    }

    public void setPlayerCharacterUnlocked(boolean[] playerCharacterUnlocked){
        this.playerCharacterUnlocked = playerCharacterUnlocked;
    }

    public boolean[] getWeaponsUnlocked() {
        return weaponsUnlocked;
    }

    public void setWeaponsUnlocked(boolean[] weaponsUnlocked){
        this.weaponsUnlocked = weaponsUnlocked;
    }


    public int getGachaCurrency() {
        return gachaCurrency;
    }

    public void setGachaCurrency(int currency){
        this.gachaCurrency = currency;
    }
    public int getShopCurrency() {
        return shopCurrency;
    }

    public void setShopCurrency(int currency){
        this.shopCurrency = currency;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        String accString = "Username: " + getUserName() + "\n";
        accString += getTeam().displayPlayerCharacter();
        accString += "Pull currency: " + getGachaCurrency() + "\n" + "Shop currency: " + getShopCurrency();
        return accString;
    }
}
