import java.util.ArrayList;
import java.util.Random;
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
    private int ownedIndex;

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
     * @param critChance
     * @param critDamage
     */
    public PlayerCharacter(int ownedIndex, String name, String dialogue, int maxHp, int speed, int attack,
    int rarity, double critChance, int critDamage, int numTargets, String skillEffect) {
        super(maxHp, speed, attack, name, numTargets);
        this.ultCharge = 0;
        this.ultMax = 100;
        this.dialogue = dialogue;
        this.name = name;
        this.rarity = rarity;
        this.weaponEquipped = null;//default to null unless later on equipped
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.skillEffect = skillEffect;
        this.ownedIndex = ownedIndex;;
    }

    public PlayerCharacter(int ownedIndex, String name, String dialogue, int maxHp, int speed, int attack, int rarity, 
    double critChance, int critDamage, int numTargets, String skillEffect, int ultMax) {
        super(maxHp, speed, attack, name, numTargets);
        this.ultCharge = 0;
        this.ultMax = ultMax;
        this.dialogue = dialogue;
        this.name = name;
        this.rarity = rarity;
        this.weaponEquipped = null;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.skillEffect = skillEffect;
        this.ownedIndex = ownedIndex;;
    }

    /**
     * 
     */
    public void ultimate(Entity[] targets) {
        System.out.println(getName() + " activates their ultimate!");
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
        switch (getSkillEffect()) {
            case "Damage":
                for (int i = 0; i < targets.length; i++) {
                    Random random = new Random();
                        if (random.nextInt(101)>getCritChance()){
                        setAttack(getAttack()*getCritDamage());
                    }
                        super.normalAttack(targets[i]);
                        setAttack(getAttack() / getCritDamage());
                }
                break;
            case "Heal":
            //heals allies
                for (int i = 0; i < targets.length; i++) {
                    int change = getAttack();
                    System.out.println(targets[i].getName() + " was healed for " + change + " HP!");
                    targets[i].setHp(Math.min(targets[i].getMaxHp(), targets[i].getHp() + change));
                }
                break;
            case "Push":
                // reduces enemy action point
                for (int i = 0; i < targets.length; i++) {
                    int change = getAttack();
                    System.out.println(targets[i].getName() + " had their action points decreased by " + change + "!");
                    targets[i].setActionPoints(targets[i].getActionPoints() - change);
                }
                break;
            case "Pull":
                // read heal comment
                for (int i = 0; i < targets.length; i++) {
                    int change = getAttack();
                    System.out.println(targets[i].getName() + " had their action points increased by " + change + "!");
                    targets[i].setActionPoints(targets[i].getActionPoints() + change);
                }
                break;
        }
    }

    public void weaponEffect(){
        if (this.getWeaponEquipped()!=null){
        switch (this.weaponEquipped.getSpecialEffect()){
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
    protected Entity[] selectTarget(int mainTarget, Entity[] enemies) {
        ArrayList<Entity> targetable = new ArrayList<>();
        if (getSkillEffect().equals("Heal") || getSkillEffect().equals("Pull")) {
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] instanceof PlayerCharacter) {
                    targetable.add(enemies[i]);
                }
            }
        } else {
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] instanceof Enemy) {
                    targetable.add(enemies[i]);
                }
            }
        }
        System.out.println("Please choose your main target (target will be in the center of aoe): ");
        for (int i = 0; i < targetable.size(); i++) {
            System.out.println((i + 1) + " - " + targetable.get(i).toString());
        }
        boolean choosing = true;
        int target = 0;
        while (choosing) {
            if (Main.scanner.hasNextInt()) {
                target = Main.scanner.nextInt() - 1;
                Main.scanner.nextLine();
                if (target >= 0 && target < targetable.size()) {
                    choosing = false;
                }
            } else {
                Main.scanner.next();
            }
        }
        return super.selectTarget(target, (Entity[]) targetable.toArray());
    }

    @Override
    public void attack(int attackType, int mainTarget, Entity[] enemies) {

        Entity[] targets = selectTarget(mainTarget, enemies);
        if (getUltCharge() == getUltMax()) {
            ultimate(targets);
        } 
        else {
            if (attackType == 1) {
                normalAttack(enemies[mainTarget]);
                setUltCharge(Math.min(getUltCharge() + 20, getUltMax()));
            } 
            else {
                skill(targets);
                setUltCharge(Math.min(getUltCharge() + 33, getUltMax()));
            }
        }
        
    }

    @Override
    public int turnBegin(int skillPointsAvailable, Entity[] targets) {
        System.out.println(getName() + "'s turn!");
        boolean choosing = true;
        while (choosing) {
            System.out.println("What kind of attack would you like to do?");
            System.out.println("1 - Normal Attack");
            System.out.println("2 - Skill");
            System.out.println("Skill points available: " + skillPointsAvailable);
            System.out.print("Enter your choice: ");
            if (Main.scanner.hasNextInt()) {
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
                if (choice == 2 && skillPointsAvailable == 0) {
                    System.out.println("Out of skill points, can't use skill.");
                } else if (choice > 2 || choice < 1) {
                    System.out.println("Invalid number entered.");
                } else {
                    attack(choice, 0, targets);
                    if (choice == 1) {
                        return 1;
                    }
                    choosing = false;
                }
            } else {
                Main.scanner.next();
            }
        }
        return -1;
    }

    @Override
        protected void normalAttack(Entity target) {
        Random random = new Random();
        if (random.nextInt(101)>getCritChance()){
            setAttack(getAttack()*getCritDamage());
        }
        super.normalAttack(target);
        setAttack(getAttack() / getCritDamage());
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

    public String detailedToString(){
        String detailedString = getName() + ": Attack: " + getAttack() + ", Speed: " + getSpeed() + ", Hp: " + getHp()
        + "\n" + "\"" + getDialogue() + "\""+ "\n" + "Rarity: " + getRarity();
        if (getWeaponEquipped() != null) {
            detailedString += "\n" + "Weapon: " + getWeaponEquipped().getName();
        }
        return detailedString;
    }

    public int getOwnedIndex(){
        return this.ownedIndex;
    }

    public String getSkillEffect() {
        return skillEffect;
    }
}
