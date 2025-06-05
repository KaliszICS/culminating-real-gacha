public class Weapon {
    private int attackMod;
    private int hpMod;
    private String specialEffect;
    private String name;
    private String description;
    private PlayerCharacter equippedTo;

    public Weapon(int attackMod, int hpMod, String specialEffect, String name, String description) {
        this.attackMod = attackMod;
        this.hpMod = hpMod;
        this.specialEffect = specialEffect;
        this.name = name;
        this.description = description;
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

    public void setHpMod(int hpMod) {
        this.hpMod = hpMod;
    }
    
    public String getSpecialEffectIndex() {
            return this.specialEffect;
    }

    public void setSpecialEffectIndex(String specialEffectIndex) {
        this.specialEffect = specialEffectIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlayerCharacter getEquippedTo() {
        return this.equippedTo;
    }

    public void setEquippedTo(PlayerCharacter equippedTo) {
        this.equippedTo = equippedTo;
    }
}
