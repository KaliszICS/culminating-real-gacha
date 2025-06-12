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
     * Creates a playercharacter with specified parameters. ultCharge is initialized to 0, ultMax is initialized to 100, and weaponEquipped is initialized to null.
     * @param ownedIndex the index of the playercharacter in Home.PLAYER_CHARACTER_LIST
     * @param name the name of the playercharacter
     * @param dialogue the dialogue the playercharacter says when pulled
     * @param maxHp the max hp of the playercharacter
     * @param speed the speed of the playercharacter
     * @param attack the attack stat of the playercharacter
     * @param rarity the rarity of the playercharacter
     * @param critChance the chance the playercharacter has of getting a critical hit
     * @param critDamage the multiplier the playercharacter attack stat gets on critical hit
     * @param numTargets the number of entities the playercharacter targets with their skill
     * @param skillEffect a string representing what effect the playercharacter skill has (either Damage, Heal, Pull, or Push)
     * @see Home#PLAYER_CHARACTER_LIST
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

    /**
     * Creates a playercharacter with specified parameters. ultCharge is initialized to 0 and weaponEquipped is initialized to null.
     * @param ownedIndex the index of the playercharacter in Home.PLAYER_CHARACTER_LIST
     * @param name the name of the playercharacter
     * @param dialogue the dialogue the playercharacter says when pulled
     * @param maxHp the max hp of the playercharacter
     * @param speed the speed of the playercharacter
     * @param attack the attack stat of the playercharacter
     * @param rarity the rarity of the playercharacter
     * @param critChance the chance the playercharacter has of getting a critical hit
     * @param critDamage the multiplier the playercharacter attack stat gets on critical hit
     * @param numTargets the number of entities the playercharacter targets with their skill
     * @param skillEffect a string representing what effect the playercharacter skill has (either Damage, Heal, Pull, or Push)
     * @param ultMax the max value of the playercharacter's ultcharge, ultimate will be used once ultcharge reaches this value
     * @see Home#PLAYER_CHARACTER_LIST
     */
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
     * Activates the playercharacter's ultimate once their ultCharge reaches ultMax by running skill 3 times.
     * @param targets the available targets for the ultimate
     * @see PlayerCharacter#skill
     */
    public void ultimate(Entity[] targets) {
        int runSkillTimesForUlt = 3;

        for (int i = 0; i < runSkillTimesForUlt; i++) {
            skill(targets);
        }

        setUltCharge(0);
    }

    /**
     * Activates the playercharacter's skill on specified targets. A skill can have 4 possible skill effects:<br><br>
     * 
     * Damage:<br>
     * - Performs the entity's basic attack on all specified targets at once.<br>
     * Heal:<br>
     * - Heals the targets of the skill, increasing their hp by the entity's attack stat.<br>
     * Push:<br>
     * - Reduces the action points of the targets, making them move later.<br>
     * Pull:<br>
     * - Increases the action points of the targets, making them move earlier.<br><br>
     * 
     * In order to check which skill to run, playercharacter checks the value of this.skillEffect.
     * @param targets the entities that are targetted by the skill
     * @see PlayerCharacter#skillEffect
     */
    @Override
    protected void skill(Entity[] targets) {
        
        switch (getSkillEffect()) {
            case "Damage":
                for (int i = 0; i < targets.length; i++) {
                    Random random = new Random();
                    
                    if (random.nextInt(101)>getCritChance()){
                        setAttack(getAttack()*getCritDamage());
                        setAttack(getAttack()*4);//default multiplier of 4 for skill
                    }

                    super.normalAttack(targets[i]);
                    setAttack(getAttackDefault());
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
                    int change = getAttack()/3;
                    System.out.println(targets[i].getName() + " had their action points increased by " + change + "!");
                    targets[i].setActionPoints(targets[i].getActionPoints() + change);
                }
                break;
        }
    }

    /**
     * Applies the effect of the weapon onto the playercharacter's stats. There are 4 possible effects for the weapon:<br><br>
     * 
     * Speed:<br>
     * - Doubles the playercharacter's speed.<br>
     * CritChance:<br>
     * - Multiplies the playercharacter's crit chance by 1.5.<br>
     * CritDamage:<br>
     * - Doubles the playercharacter's damage multiplier on crit.<br>
     * Heal:<br>
     * - Heals the playercharacter by 5% of their current hp. This heal can make the playercharacter's hp go above maxHp.
     */
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
    
    /**
     * Playercharacter implementation of selectTarget. Depending on skill type, puts all enemies or playercharacters into an entity array, gets maintarget through user input, and passes those values into entity implementation.
     * @see Entity#selectTarget
     */
    @Override
    protected Entity[] selectTarget(int mainTarget, Entity[] enemies, int attackType) {
        ArrayList<Entity> targetable = new ArrayList<>();
        if (getSkillEffect().equals("Damage") || getSkillEffect().equals("Push") || attackType == 1){
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] instanceof Enemy&& !enemies[i].getName().equals("Zero-Line")) {
                    targetable.add(enemies[i]);
                }
            }
        }
        else{
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] instanceof PlayerCharacter) {
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
                System.out.println("Invalid choice.");
                Main.scanner.next();
            }
        }
        Entity[] targetableArray = new Entity[]{};
        return super.selectTarget(target, targetable.toArray(targetableArray), attackType);
    }

    /**
     * Playercharacter implementation of attack. Runs ultimate immediately if fully charged, otherwise increases ultCharge by 20 on normal attack or 33 on skill use, then runs entity implementation to run the actual attack.
     * @see Entity#attack
     */
    @Override
    public void attack(int attackType, int mainTarget, Entity[] enemies) {
        Entity[] targets = new Entity[]{};
        if (getUltCharge() == getUltMax()) {
            System.out.println(getName() + " activates their ultimate!");
            int skillType = 2;
            targets = selectTarget(mainTarget, enemies, skillType);
            ultimate(targets);
        } else {
            if (attackType == 1) {
                setUltCharge(Math.min(getUltCharge() + 20, getUltMax()));
            } else {
                setUltCharge(Math.min(getUltCharge() + 33, getUltMax()));
            }
            super.attack(attackType, mainTarget, enemies);
        }
        
    }

    /**
     * Playercharacter implementation of turnBegin. Gets type of attack through user input and passes it into attack with an arbitrary value for mainTarget. If ultimate is charged, arbitrary values are passed for attackType and mainTarget.
     * @see Entity#turnBegin
     */
    @Override
    public int turnBegin(int skillPointsAvailable, Entity[] targets) {
        System.out.println(getName() + "'s turn!");
        boolean choosing = true;
        if (getUltCharge() == getUltMax()) {
            // arbitrary values assigned, which will not be used
            attack(1, 0, targets);
            return 0;
        } else {
            while (choosing) {
                System.out.println("What kind of attack would you like to do?");
                System.out.println("1 - Normal Attack");
                System.out.println("2 - Skill: " + getSkillEffect());
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
                        if (choice == 2) {
                            return 1;
                        }
                        choosing = false;
                    }
                } else {
                    Main.scanner.next();
                }
            }
        }
        return -1;
    }

    /**
     * Uses the playercharacter normal attack on the target. Checks if the attack is a crit, multiplies attack stat, then runs entity implementation and resets attack stat after.
     * @see Entity#normalAttack
     */
    @Override
        protected void normalAttack(Entity target) {
        if (getWeaponEquipped()!=null){
            setAttack(getAttack() + getWeaponEquipped().getAttackMod());
        }
        Random random = new Random();
        if (random.nextInt(100)+1>getCritChance()){
            setAttack(getAttack()*getCritDamage());
        }
        super.normalAttack(target);
        setAttack(getAttackDefault());
    }

    /**
     * Returns how much charge the playercharacter has for their ultimate
     * @return an int representing how much charge the playercharacter has for their ultimate
     */
    public int getUltCharge() {
        return this.ultCharge;
    }

    /**
     * Changes how much charge the playercharacter has for their ultimate
     * @param ultCharge an int representing the new amount of charge the player character has for their ultimate
     */
    public void setUltCharge(int ultCharge) {
        this.ultCharge = ultCharge;
    }

    /**
     * Returns the max amount of ultCharge the playercharacter can have for their ultimate, in other words how much ultCharge is required for their ultimate.
     * @return the max amount of ultcharge the playercharacter can have for their ultimate
     */
    public int getUltMax() {
        return this.ultMax;
    }

    /**
     * Returns the dialogue the playercharacter says when they are pulled.
     * @return the dialogue the playercharacter says when pulled
     */
    public String getDialogue() {
        return dialogue;
    }
    
    /**
     * Returns the rarity of the playercharacter.
     * @return the rarity of the playercharacter
     */
    public int getRarity() {
        return rarity;
    }

    /**
     * Returns the weapon the playercharacter has equipped.
     * @return the weapon the playercharacter has equipped
     */
    public Weapon getWeaponEquipped() {
        return weaponEquipped;
    }

    /**
     * Changes the weapon the playercharacter has equipped.
     * @param weaponEquipped the new weapon the playercharacter should have equipped
     */
    public void setWeaponEquipped(Weapon weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
    }

    /**
     * Returns the chance the playercharacter has for a critical hit.
     * @return the chance the playercharacter has for a critical hit
     */
    public double getCritChance() {
        return critChance;
    }

    /**
     * Changes the chance the playercharacter has for a critical hit.
     * @param critChance the new chance the playercharacter should have for a critical hit
     */
    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    /**
     * Returns the multiplier the playercharacter has for their damage on crit.
     * @return the multiplier the playercharacter has for crit damage
     */
    public int getCritDamage() {
        return critDamage;
    }

    /**
     * Changes the multiplier the playercharacter has for their damage on crit.
     * @param critDamage the new multiplier the playercharacter should have for crit damage
     */
    public void setCritDamage(int critDamage) {
        this.critDamage = critDamage;
    }
    
    /**
     * Returns the name of the playercharacter.
     * @return the name of the playercharacter
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a more detailed string representation of the playercharacter, mainly for use when they are pulled. This string is in the following form:<br><br>
     * 
     * (this.name): Attack: (this.attack), Speed: (this.speed), Hp: (this.hp)<Br>
     * "(this.dialogue)"<br>
     * Rarity: (this.rarity) Stars
     * @return a more detailed string representation of the playercharacter
     */
    public String detailedToString(){
        String detailedString = getName() + ": Attack: " + getAttack() + ", Speed: " + getSpeed() + ", Hp: " + getHp()
        + "\n" + "\"" + getDialogue() + "\""+ "\n" + "Rarity: " + getRarity() + " Stars";
        if (getWeaponEquipped() != null) {
            detailedString += "\n" + "Weapon: " + getWeaponEquipped().getName();
        }
        return detailedString;
    }

    /**
     * Returns the playercharacter's position in Home.PLAYER_CHARACTER_LIST.
     * @return the playercharacter's position in Home.PLAYER_CHARACTEr_LIST
     * @see Home#PLAYER_CHARACTER_LIST
     */
    public int getOwnedIndex(){
        return this.ownedIndex;
    }

    /**
     * Returns the playercharacter's skill effect.
     * @return the playercharacter's skill effect.
     */
    public String getSkillEffect() {
        return skillEffect;
    }

    /**
     * Returns a string representation of the playercharacter. This is formatted in the following way:<br><br>
     * 
     * (this.name) - (this.hp)/(this.maxHp), Action points: (this.actionPoints), Ultimate Charge: (this.ultCharge)/(this.ultMax)
     * @return a string representation of the playercharacter
     */
    @Override
    public String toString() {
        return super.toString() + ", Ultimate Charge: " + getUltCharge() + "/" + getUltMax();
    }
}
