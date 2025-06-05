/**
 * Class representing a player character, holds their ultimates, rarity, equipped weapon, among other stats.
 * @author gacha
 * @version 1.0.0
 */
public class PlayerCharacter extends Entity {
    private int ultCharge;
    private String dialogue;
    private String name;
    private int rarity;
    private Weapon weaponEquipped;
    private boolean owned;
    private double critChance;
    private int critDamage;
    private int numTargetSkill;
    private int numTargetUltimate;

    /**
     * 
     * @param maxHp
     * @param hp
     * @param speed
     * @param actionPoints
     * @param attack
     * @param numTargets
     * @param ultCharge
     * @param dialogue
     * @param name
     * @param rarity
     * @param weaponEquipped
     * @param owned
     * @param critChance
     * @param critDamage
     * @param numTargetSkill
     * @param numTargetUltimate
     */
    public PlayerCharacter(int maxHp, int hp, int speed, int actionPoints, int attack, int numTargets, int ultCharge, String dialogue, String name,
    int rarity, Weapon weaponEquipped, boolean owned, double critChance, int critDamage, int numTargetSkill, int numTargetUltimate) {
        // TODO: find a way to NOT MAKE THIS 16 PARAMETERS
        super(maxHp, hp, speed, actionPoints, attack, numTargets);
        this.ultCharge = ultCharge;
        this.dialogue = dialogue;
        this.name = name;
        this.rarity = rarity;
        this.weaponEquipped = weaponEquipped;
        this.owned = owned;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.numTargetSkill = numTargetSkill;
        this.numTargetUltimate = numTargetUltimate;
    }

    /**
     * 
     */
    public void ultimate() {
        //TODO: please don't just give every character their ultimate through overrides that's a really bad way of doing it
    }

    /**
     * 
     */
    @Override
    protected void skill(Entity[] targets) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'skill'");
    }

    /**
     * Returns how much charge the character has for their ultimate.
     * @return how much charge the character has for their ultimate
     */
    public int getUltCharge() {
        return ultCharge;
    }

    /**
     * Changes how much charge the character has for their ultimate.
     * @param ultCharge new amount of charge for the character's ultimate
     */
    public void setUltCharge(int ultCharge) {
        this.ultCharge = ultCharge;
    }

    /**
     * 
     * @return
     */
    public String getDialogue() {
        return dialogue;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public int getRarity() {
        return rarity;
    }

    /**
     * 
     * @return
     */
    public Weapon getWeaponEquipped() {
        return weaponEquipped;
    }

    /**
     * 
     * @param weaponEquipped
     */
    public void setWeaponEquipped(Weapon weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
    }

    /**
     * 
     * @return
     */
    public boolean isOwned() {
        return owned;
    }

    /**
     * 
     * @param owned
     */
    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    /**
     * 
     * @return
     */
    public double getCritChance() {
        return critChance;
    }

    /**
     * 
     * @param critChance
     */
    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    /**
     * 
     * @return
     */
    public int getCritDamage() {
        return critDamage;
    }

    /**
     * 
     * @param critDamage
     */
    public void setCritDamage(int critDamage) {
        this.critDamage = critDamage;
    }
    
    /**
     * 
     * @return
     */
    public int getNumTargetSkill() {
        return numTargetSkill;
    }

    /**
     * 
     * @param numTargetSkill
     */
    public void setNumTargetSkill(int numTargetSkill) {
        this.numTargetSkill = numTargetSkill;
    }

    /**
     * 
     * @return
     */
    public int getNumTargetUltimate() {
        return numTargetUltimate;
    }

    /**
     * 
     * @param numTargetUltimate
     */
    public void setNumTargetUltimate(int numTargetUltimate) {
        this.numTargetUltimate = numTargetUltimate;
    }
}
