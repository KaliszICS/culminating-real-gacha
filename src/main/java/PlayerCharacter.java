/**
 * Class representing a player character, holds their ultimates, rarity, equipped weapon, among other stats.
 * @author gacha
 * @version 1.0.0
 */
public class PlayerCharacter extends Entity {
    private String dialogue;
    private String name;
    private int rarity;
    private Weapon weaponEquipped;
    private double critChance;
    private int critDamage;
    private int numTargetSkill;
    private int numTargetUltimate;
    private String skillEffect;

    /**
     * 
     * @param maxHp
     * @param hp
     * @param speed
     * @param actionPoints
     * @param attack
     * @param numTargets
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
    public PlayerCharacter(int maxHp, int hp, int speed, int attack, int numTargets, String name, String dialogue,
    int rarity, Weapon weaponEquipped, double critChance, int critDamage, int numTargetSkill, int numTargetUltimate, String skillEffect) {
        // TODO: find a way to NOT MAKE THIS 16 PARAMETERS
        super(maxHp, hp, speed, attack, numTargets, name);
        this.dialogue = dialogue;
        this.name = name;
        this.rarity = rarity;
        this.weaponEquipped = weaponEquipped;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.numTargetSkill = numTargetSkill;
        this.numTargetUltimate = numTargetUltimate;
        this.skillEffect = skillEffect;
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

    public void weaponEffect(){
        switch (this.weaponEquipped.getSpecialEffectIndex()){
            case "":
            break; //does not have special effect
            
            case "Speed":
            super.setSpeed(super.getSpeed()*2);//doubles current entity speed
            break;
        
            case "CritChance": 
            setCritChance(getCritChance()*1.5);//multiplier to crit chance
            break;
            
            case "CritDamage":
            setCritDamage(getCritDamage()*2);//double crit damage
            break;
            
            case "Heal":
            super.setHp(getHp()+ (int)(super.getHp()*1.05));//heals 5% of current hp
            if (super.getHp()> super.getMaxHp()){
                super.setHp(super.getMaxHp());
            }//if heal exceeds max health, hp is max health
            break;
        
        }
    }
    
    @Override
    public void attack(int attackType, int mainTarget, Entity[] enemies) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void turnBegin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'turnBegin'");
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
     * @return
     */
    public int getNumTargetUltimate() {
        return numTargetUltimate;
    }

    public String getName() {
        return name;
    }

    public String getSkillEffect() {
        return skillEffect;
    }

}
