public class Weapon {
    
    private int attackMod;
    private int hpMod;
    private String specialEffect;
    private String name;
    private String description;
    private PlayerCharacter equippedTo;
    private int rarity;

    public Weapon(String name, int attackMod, int hpMod, String specialEffect, String description, int rarity) {
        this.attackMod = attackMod;
        this.hpMod = hpMod;
        this.specialEffect = specialEffect;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
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
    
    public String getSpecialEffectIndex() {
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

    public void setEquippedTo(PlayerCharacter equippedTo) {
        this.equippedTo = equippedTo;
    }

    public int getRarity() {
        return rarity;
    }
}
