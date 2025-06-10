public class Weapon {
    
    private int attackMod;
    private int hpMod;
    private String specialEffect;
    private String name;
    private String description;
    private PlayerCharacter equippedTo;
    private int rarity;
    private int ownedIndex;

    public Weapon(String name, int attackMod, int hpMod, String specialEffect, String description, int rarity, int ownedIndex) {
        this.attackMod = attackMod;
        this.hpMod = hpMod;
        this.specialEffect = specialEffect;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.ownedIndex = ownedIndex;
    }

    public int getAttackMod() {
        return this.attackMod;
    }

    public void setAttackMod(int attackMod) {
        this.attackMod = attackMod;
    }

    public int getHpMod() {
        return hpMod;
    }
    
    public String getSpecialEffect() {
            return this.specialEffect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }

    public PlayerCharacter getEquippedTo() {
        return this.equippedTo;
    }

    public int getOwnedIndex(){
        return this.ownedIndex;
    }

    public void setEquippedTo(PlayerCharacter equippedTo) {
        this.equippedTo = equippedTo;
    }

    public int getRarity() {
        return rarity;
    }

    public String detailedToString(){
        return getName() +  ": \nAttack: " + getAttackMod() + ", MaxHP mod: " + 
        getHpMod() + ". \n" + getDescription() + "\n" + getSpecialEffect() + "\n" + "Rarity: " + getRarity();
    }

}
