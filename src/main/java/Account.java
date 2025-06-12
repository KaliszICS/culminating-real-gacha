/**
 * Class representing an account, stores username, password, and important stats.
 * @version 1.0.0
 * @author gacha
 */
public class Account {
    private String userName;
    private String password;
    private boolean[] playerCharacterUnlocked;
    private boolean[] weaponsUnlocked;
    private int gachaCurrency;
    private int shopCurrency;
    private Team team;

    /**
     * Creates an account with specified parameters, used for initializing accounts from a file.
     * @param userName the username of the account
     * @param password the password of the account
     * @param playerCharacterUnlocked boolean parallel array representing which characters are unlocked
     * @param weaponsUnlocked boolean parallel array representing which weapons are unlocked
     * @param gachaCurrency amount of currency account has for gacha pulls
     * @param shopCurrency amount of currency account has for shop items
     */
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

    /**
     * Creates an account with specified username and password, used for signup.
     * @param userName the username of the account.
     * @param password the password of the account
     */
    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.playerCharacterUnlocked = new boolean[9];//there are 9 characters in the game
        this.weaponsUnlocked = new boolean[8];
        this.gachaCurrency = 160000;
        this.shopCurrency = 0;
        this.team = new Team();
    }

    /**
     * Returns the username of the account.
     * @return the username of the account
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the username of the account.
     * @return the password of the account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a parallel array representing which characters are unlocked.
     * @return boolean parallel array representing which characters are unlocked
     */
    public boolean[] getPlayerCharacterUnlocked() {
        return playerCharacterUnlocked;
    }

    /**
     * Returns a parallel array representing which weapons are unlocked.
     * @return boolean parallel array representing which weapons are unlocked
     */
    public boolean[] getWeaponsUnlocked() {
        return weaponsUnlocked;
    }

    /**
     * Returns the amount of currency the account has for gacha pulls.
     * @return amount of currency account has for gacha pulls
     */
    public int getGachaCurrency() {
        return gachaCurrency;
    }

    /**
     * Sets the amount of currency the account has for gacha pulls.
     * @param currency new amount of currency account has for gacha pulls
     */
    public void setGachaCurrency(int currency){
        this.gachaCurrency = currency;
    }

    /**
     * Returns the amount of currency the account has for shop items.
     * @return amount of currency account has for shop items
     */
    public int getShopCurrency() {
        return shopCurrency;
    }

    /**
     * Sets the amount of currency the account has for shop items.
     * @param currency amount of currency account has for shop items
     */
    public void setShopCurrency(int currency){
        this.shopCurrency = currency;
    }

    /**
     * Returns a team object representing the account's current team.
     * @return team object representing the current team
     * @see Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Returns a string representation of the Account object. The string will be formatted as follows:<br><br>
     * 
     * Username: (this.username)<br>
     * (string representation of team object)<br>
     * Pull currency: (getGachaCurrency())<br>
     * Shop currency: (getShopCurrency)
     * @return string representation of the account object
     */
    @Override
    public String toString() {
        String accString = "\nUsername: " + getUserName() + "\n";
        accString += getTeam().toString();
        accString += "Pull currency: " + getGachaCurrency() + "\n" + "Shop currency: " + getShopCurrency() + "\n";
        return accString;
    }
}
