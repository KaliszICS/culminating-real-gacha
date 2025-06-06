public class Account {
    private String userName;
    private String password;
    private boolean[] playerCharacterUnlocked;
    // probably should not be a weapon array idk could be boolean
    private Weapon[] weaponInv;
    private int gachaCurrency;
    private int shopCurrency;
    private Team team;

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

    public Weapon[] getWeaponInv() {
        return weaponInv;
    }

    public int getGachaCurrency() {
        return gachaCurrency;
    }

    public int getShopCurrency() {
        return shopCurrency;
    }

    public Team getTeam() {
        return team;
    }
}
