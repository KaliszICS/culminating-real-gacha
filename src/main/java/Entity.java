/**
 * General class representing entities in battle, used for hp, speed, and attack-related operations.
 * @author gacha
 * @version 1.1.0
 */
public abstract class Entity implements Comparable<Entity> {
    private int maxHp;
    private int hp;
    private int speed;
    private int actionPoints;
    private int attack;
    private int attackDefault;

    private String name;
    private int numTargetSkill;

    /**
     * Creates an entity object with specififed parameters.
     * @param maxHp the maxHp of the entity
     * @param hp the current hp of the entity
     * @param speed the speed stat of the entity
     * @param attack the attack stat of the entity
     * @param name the name of the entity
     * @param numTargetSkill the number of targets the entity's skill targets
     */
    public Entity(int maxHp, int speed, int attack, String name, int numTargetSkill) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.numTargetSkill = numTargetSkill;
        this.speed = speed;
        this.actionPoints = (int)(this.speed*1.25+100);//this value affects turn order, speed is how fast they get this back
        if (name.equals("Zero-Line")){
            this.actionPoints=0; //to ensure that zero-line goes last, as it is a marker of next round
        }
        this.attack = attack;
        this.name = name;
        this.attackDefault = attack;
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
    protected Entity[] selectTarget(int mainTarget, Entity[] enemies, int attackType) {
        if (attackType ==1){
            return new Entity[]{enemies[mainTarget]};
        }
        int leftIndex = Math.max(mainTarget - getnumTargetSkill() / 2, 0);
        int rightIndex = Math.min(mainTarget + getnumTargetSkill() / 2, enemies.length - 1);
        if (getnumTargetSkill() % 2 == 0) {
            leftIndex++;
        }
        Entity[] targets = new Entity[rightIndex - leftIndex + 1];
        for (int i = leftIndex; i <= rightIndex; i++) {
            targets[i - leftIndex] = enemies[i];
        }
        return targets;
    }
    
    /**
     * Performs the entity's basic attack on the specified target, decreasing their hp by the entity's attack stat.
     * @param target the target to be attacked
     */
    protected void normalAttack(Entity target) {
        System.out.println("Hit " + target.getName() + " for " + getAttack() + " damage!");
        target.setHp(target.getHp() - getAttack());
    }

    /**
     * Performs the entity's unique skill on specified targets. Skills are special attacks, and require spending a skill point to activate. There are 4 types of skills:<br><br>
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
     * Implement accordingly to handle skill logic for possible skill types of subclasses.
     * @param targets the targets of the skill
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
        Entity[] targets = selectTarget(mainTarget, enemies, attackType);
        if (attackType == 1) {
            normalAttack(targets[mainTarget]);
        } else {
            skill(targets);
        }
    }

    /**
     * Ends the turn for the entity by decreasing their action points by 100. Does not run on the Zero-Line entity (see Battle.takeTurn()).
     * @see Battle#takeTurn
     */
    public void turnEnd() {
        if (getName().equals("Zero-Line")){
            return;
        }
        int endTurnActionPointLoss = 100;
        setActionPoints(getActionPoints() - endTurnActionPointLoss);
    }

    /**
     * Begins the turn for the entity by getting parameters for Entity.attack().<br><br>
     * 
     * Implement accordingly to get parameters.
     * @param skillPointsAvailable the skill points available for the turn
     * @param targets the targets available for attack
     * @return the change in skillPoints for battle
     * @see Entity#attack
     */
    public abstract int turnBegin(int skillPointsAvailable, Entity[] targets);

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
     * Changes the number of action points the entity has.
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
     * Changes the current attack of the entity.
     * @param attack the new attack of the entity
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Returns the number of entities the entity can target.
     * @return the number of entities the entity can target
     */
    public int getnumTargetSkill() {
        return numTargetSkill;
    }

    /**
     * Returns the name of the entity.
     * @return the name of the entity
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the attack stat of the entity on initialization. Used for resets in case of attack stat changing.
     * @return the original attack stat of the entity
     */
    public int getAttackDefault() {
        return attackDefault;
    }

    /**
     * Returns a string representation of the entity. This string is formatted in the following way:<br><br>
     * 
     * (this.name) - (this.hp)/(this.maxHp), Action points: (this.actionPoints)
     * @return a string representing the entity
     */
    @Override
    public String toString() {
        if (getName().equals("Zero-Line")){
            return getName() + ": " + getActionPoints();
        }
        return getName() + " - " + getHp() + "/" + getMaxHp() + ", Action points: " + getActionPoints();
    }

    /**
     * Compares the current entity to another entity based on action points. This comparison is reversed, so an entity with more action points is "smaller" than an entity with less action points.<br><br>
     * 
     * If 2 entities have the same amount of action points, they are based on the lexicographical order of their names instead, though playerCharacters will be considered "smaller" than enemies.
     * @param o the entity being compared to
     * @return a number less than 0 if the current entity is "smaller", a number greater than 0 if the current entity is "larger", and 0 if the two entities are the same size
     */
    @Override
    public int compareTo(Entity o) {
        if (getActionPoints() == o.getActionPoints()) {
            if (this instanceof PlayerCharacter && o instanceof Enemy) {
                return -1;
            } else if (this instanceof Enemy && o instanceof PlayerCharacter) {
                return 1;
            }
            return getName().compareTo(o.getName());
        }
        return -Integer.compare(getActionPoints(), o.getActionPoints());
    }
}
