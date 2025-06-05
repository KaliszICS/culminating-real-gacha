/**
 * General class representing entities in battle, used for hp, speed, and attack-related operations.
 * @author gacha
 * @version 1.0.0
 */
public abstract class Entity {
    private int maxHp;
    private int hp;
    private int speed;
    private int actionPoints;
    private int attack;
    private int numTargets;

    /**
     * Creates an entity object with specififed maxHp, hp, speed, actionPoint, attack, and number of targetted enemies.
     * @param maxHp the maxHp of the entity
     * @param hp the current hp of the entity
     * @param speed the speed stat of the entity
     * @param actionPoint the action points the entity has
     * @param attack the attack stat of the entity
     * @param numTargets the number of enemies the entity can target
     */
    public Entity(int maxHp, int hp, int speed, int actionPoints, int attack, int numTargets) {
        this.maxHp = maxHp;
        this.hp = hp;
        this.speed = speed;
        this.actionPoints = actionPoints;
        this.attack = attack;
        this.numTargets = numTargets;
    }


    /**
     * Selects a group of entities to act as targets for an attack.<br><br>
     * 
     * If the user chooses the main target in a way where there aren't enough enemies to target, the group will be cut off<br><br>
     * 
     * (i.e. the character targets 7 enemies but chooses 2nd enemy as target, group contains 5 enemies as 2 are cut off from the left).<br><br>
     * @param mainTarget the entity in the center of the group
     * @param enemies the entities to choose from
     * @return an array of entities acting as targets
     */
    protected Entity[] selectTarget(int mainTarget, Entity[] enemies) {
        int leftIndex = Math.max(mainTarget - getNumTargets() / 2, 0);
        int rightIndex = Math.min(mainTarget + getNumTargets() / 2, enemies.length - 1);
        if (getNumTargets() % 2 == 0) {
            leftIndex++;
        }
        Entity[] targets = new Entity[rightIndex - leftIndex + 1];
        for (int i = leftIndex; i <= rightIndex; i++) {
            targets[i - leftIndex] = enemies[i];
        }
        return targets;
    }
    
    /**
     * Performs the entity's basic attack on specified targets.
     * @param targets the targets to be attacked
     */
    protected void normalAttack(Entity[] targets) {
        for (int i = 0; i < targets.length; i++) {
            targets[i].setHp(targets[i].getHp() - getAttack());
        }
    }

    /**
     * Performs the entity's unique skill on specified targets.
     * @param targets the targets to be attacked
     */
    protected abstract void skill(Entity[] targets);

    /**
     * Performs an attack on enemies. First chooses targets then performs the attack.<br><br>
     * 
     * If attackType is 1, then a normal attack is performed. Otherwise the character specific skill is performed.
     * @param attackType the attack to be performed, 1 for a normal attack and 2 for the character specific skill
     * @param mainTarget the main target of the attack, in the center of the group of enemies being targetted
     * @param enemies the enemies to choose from as an array
     */
    public void attack(int attackType, int mainTarget, Entity[] enemies) {
        Entity[] targets = selectTarget(mainTarget, enemies);
        if (attackType == 1) {
            normalAttack(targets);
        } else {
            skill(targets);
        }
    }

    /**
     * Ends the turn for the character.
     */
    public void turnEnd() {

    }

    /**
     * Begins the turn for the character.
     */
    public void turnBegin() {

    }

    /**
     * Returns the max hp of the entity.
     * @return the max hp of the entity
     */
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * Changes the max hp of the entity.
     * @param maxHp the new max hp of the entity
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }


    /**
     * Returns the current hp of the entity.
     * @return the current hp of the entity
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Changes the current hp of the entity.
     * @param hp the new hp of the entity
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Returns the current speed of the entity.
     * @return the current speed of the entity
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Changes the current speed of the entity.
     * @param speed the new speed of the entity
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Returns the number of action points the entity has.
     * @return the number of action points the entity has
     */
    public int getActionPoints() {
        return this.actionPoints;
    }

    /**
     * Changes the number of action points the entity has
     * @param actionPoints the new number of action points the entity has
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    /**
     * Returns the current attack of the entity.
     * @return the current attack of the entity
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Changes the current attack of the entity
     * @param attack the new attack of the entity
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Returns the number of entities the entity can target
     * @return the number of entities the entity can target
     */
    public int getNumTargets() {
        return numTargets;
    }

    /**
     * Changes the number of entities the entity can target
     * @param numTargets the number of entities the entity can target
     */
    public void setNumTargets(int numTargets) {
        this.numTargets = numTargets;
    }
}
