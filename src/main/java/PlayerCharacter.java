import java.util.Scanner;

/**
 * Class representing a player character, holds their ultimates, rarity, equipped weapon, among other stats.
 * @author gacha
 * @version 1.0.0
 */
public class PlayerCharacter extends Entity {
    private int ultCharge;
    private int ultMax;
    private String dialogue;
    private String name;
    private int rarity;
    private Weapon weaponEquipped;
    private double critChance;
    private int critDamage;
    private String skillEffect;
    private boolean defending;

    /**
     * 
     * @param maxHp
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
     */
    public PlayerCharacter(int maxHp, int speed, int attack, String name, String dialogue,
    int rarity, double critChance, int critDamage, int numTargetSkill, int numTargetUltimate, String skillEffect) {
        super(maxHp, speed, attack, name);
        this.ultCharge = 0;
        this.ultMax = 100;
        this.dialogue = dialogue;
        this.name = name;
        this.rarity = rarity;
        this.weaponEquipped = null;//default to null unless later on equipped
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.skillEffect = skillEffect;
        this.defending = false;
    }

    public PlayerCharacter(int maxHp, int speed, int attack, String name, int ultMax, String dialogue,
        int rarity, double critChance, int critDamage, int numTargetSkill, int numTargetUltimate, String skillEffect) {
        super(maxHp, speed, attack, name);
        this.ultCharge = 0;
        this.ultMax = ultMax;
        this.dialogue = dialogue;
        this.name = name;
        this.rarity = rarity;
        this.weaponEquipped = null;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.skillEffect = skillEffect;
        this.defending = false;
    }

    /**
     * 
     */
    public void ultimate(Entity[] targets) {
        int runSkillTimesForUlt = 3;
        for (int i = 0; i < runSkillTimesForUlt; i++) {
            skill(targets);
        }
        setUltCharge(0);
    }

    /**
     * 
     */
    @Override
    protected void skill(Entity[] targets) {
        // battle should check if skill effect is heal or pull, so target will consist of playercharacters if true
        switch (getSkillEffect()) {
            case "Damage":
                for (int i = 0; i < targets.length; i++) {
                    normalAttack(targets[i]);
                }
                break;
            case "Heal":
                for (int i = 0; i < targets.length; i++) {
                    // replace getAttack with some value, might heal too much if based on attack
                    targets[i].setHp(Math.min(targets[i].getMaxHp(), targets[i].getHp() + getAttack()));
                }
                break;
            case "Push":
                // read heal comment
                for (int i = 0; i < targets.length; i++) {
                    targets[i].setActionPoints(targets[i].getActionPoints() - getAttack());
                }
                break;
            case "Pull":
                // read heal comment
                for (int i = 0; i < targets.length; i++) {
                    targets[i].setActionPoints(targets[i].getActionPoints() + getAttack());
                }
                break;
        }
    }

    public void weaponEffect(){
        if (this.getWeaponEquipped()!=null){
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
                super.setHp(Math.min(getHp()+ (int)(super.getHp()*1.05), getMaxHp()));//heals 5% of current hp
                //if heal exceeds max health, hp is max health
                break;

        }
        }
    }
    
    @Override
    public void attack(int attackType, int mainTarget, Entity[] enemies) {
        if (getUltCharge() == getUltMax()) {
            Entity[] targets = selectTarget(mainTarget, enemies);
            ultimate(targets);
        } else {
            if (attackType == 1) {
                normalAttack(enemies[mainTarget]);
                setUltCharge(Math.min(getUltCharge() + 20, getUltMax()));
            } else {
                Entity[] targets = selectTarget(mainTarget, enemies);
                skill(targets);
                setUltCharge(Math.min(getUltCharge() + 33, getUltMax()));
            }
        }
        
    }

    @Override
    public void turnBegin() {
        System.out.println(getName() + "'s turn!");
        setDefending(false);
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Attack");
        System.out.println("2 - Defend");
        System.out.print("Would you like to attack or defend? ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        scanner.close();
        // check if user wants to defend, don't do anything here if no because once battle sees defending is false, it will call attack instead
        if (choice == 2) {
            setDefending(true);
        }
    }

    public int getUltCharge() {
        return this.ultCharge;
    }

    public void setUltCharge(int ultCharge) {
        this.ultCharge = ultCharge;
    }

    public int getUltMax() {
        return this.ultMax;
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
    
    public String getName() {
        return name;
    }

    public String getSkillEffect() {
        return skillEffect;
    }

    public boolean isDefending() {
        return this.defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }
}
