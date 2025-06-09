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
        // replace 0 with number of characters
        this.playerCharacterUnlocked = new boolean[0];
        // same as above for weapons
        this.weaponsUnlocked = new boolean[0];
        this.gachaCurrency = 0;
        this.shopCurrency = 0;
        this.team = new Team();
    }

    public String displayTeam() {
        // doesn't need to be done but if this is all the method is it could be removed
        return getTeam().displayPlayerCharacter();
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
}
