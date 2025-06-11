/**
 * Class representing a weapon, stores name, stat modifiers, and one of 4 (Heal, CritDMG, CritChance, Speed) special effects.
 * @version 1.0.0
 * @author gacha
 */
public class Weapon {
    
    private int attackMod;
    private int hpMod;
    private String specialEffect;
    private String name;
    private String description;
    private PlayerCharacter equippedTo;
    private int rarity;
    private int ownedIndex;

    /**
     * Creates a weapon using given parameters, defaults to not equipped to any Player Character (PC)
     * @param name
     * @param attackMod
     * @param hpMod
     * @param specialEffect
     * @param description
     * @param rarity
     * @param ownedIndex
     */
    public Weapon(String name, int attackMod, int hpMod, String specialEffect, String description, int rarity, int ownedIndex) {
        this.attackMod = attackMod;
        this.hpMod = hpMod;
        this.specialEffect = specialEffect;
        this.name = name;
        this.description = description;
        this.equippedTo = null;
        this.rarity = rarity;
        this.ownedIndex = ownedIndex;
    }

    /**
     * Returns the attack modifier that the weapon would add onto the PlayerCharacter object it's equipped to
     * @return the attack modifier
     */
    public int getAttackMod() {
        return this.attackMod;
    }

    /**
     * returns the hp modifier that the weapon applies on its equipped character
     * @return the hp modifier
     */
    public int getHpMod() {
        return hpMod;
    }
    
    /**
     * returns the special effect of the weapon (what it can do during battle)
     * @return the special effect of the weapon
     */
    public String getSpecialEffect() {
            return this.specialEffect;
    }

    /**
     * returns the name of the weapon
     * @return the name of the weapon
     */
    public String getName() {
        return name;
    }

    /**
     * returns the description (a fun quote) of the weapon
     * @return the description (a fun quote) of the weapon
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * returns the PlayerCharacter that the weapon is equipped to
     * @return the PlayerCharacter that the weapon is equipped to
     */
    public PlayerCharacter getEquippedTo() {
        return this.equippedTo;
    }
    
    /**
     * equip the weapon to a PlayerCharacter (allowing its specialEffect to apply for battle())
     * @param equippedTo the PlayerCharacter object that this weapon will be equipped to
     */
    public void setEquippedTo(PlayerCharacter equippedTo) {
        this.equippedTo = equippedTo;
    }
    
    /**
     * Returns the index of the weapon with respect to the final static array of weapons available
     * @return the index of the weapon with respect to the final static array of weapons available
     */
    public int getOwnedIndex(){
        return this.ownedIndex;
    }
    
    /**
     * Returns the rarity of the weapon
     * @return the rarity (either 4 or 5) of the weapon
     */
    public int getRarity() {
        return rarity;
    }

    
    /**
     * Returns a simple string representation of the Weapon object. The string will be formatted as follows:<br><br>
     * Name: <br>
     * Attack: (getAttackMod()), MaxHP mod: (getHpMod()), Special effect: (getSpecialEffect()), Equipped to (getEquippedTo().getName())
     * the getEquippedTo().getName() section will only show up if the weapon is equipped to a PLayerCharacter object
     * @return simple string representation of the weapon object
     */
    @Override
    public String toString(){
        String str = getName() +  ": \nAttack: " + getAttackMod() + ", MaxHP mod: " + 
        getHpMod() + ", Special effect: " + getSpecialEffect();
        if (getEquippedTo() != null) {
            str += ", Equipped to " + getEquippedTo().getName();
        }
        return str;
    }

    /**
     * Returns a detailed string representation of the Weapon object, which includes the description and rarity of the weapon
     * This will show up when pulled during gacha
     * . The string will be formatted as follows:<br><br>
     * 
     * Name: <br>
     * Attack: (getAttackMod()), MaxHP mod: (getHpMod())<br>
     * (getDescription())<br>
     * Special effect: (getSpecialEffect()<br>
     * Rarity: (getRarity())
     * the getEquippedTo().getName() section will only show up if the weapon is equipped to a PLayerCharacter object
     * @return detailed string representation of the weapon object
     */
    public String detailedToString(){
        return getName() +  ": \nAttack: " + getAttackMod() + ", MaxHP mod: " + 
        getHpMod() + ". \n" + getDescription() + "\n" + getSpecialEffect() + "\n" + "Rarity: " + getRarity();
    }

}
