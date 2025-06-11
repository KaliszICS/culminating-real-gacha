import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing an enemy in battle.
 * @author gacha
 * @version 1.0.0
 */
public class Enemy extends Entity {
    /**
     * Creates a new entity with specified stats.
     * @param maxHp the max hp of the enemy
     * @param hp the current hp of the enemy
     * @param speed the speed stat of the enemy
     * @param attack the attack stat of the entity
     * @param numTargets the number of player characters the enemy can target
     */
    public Enemy(int maxHp, int speed, int attack, int numTargets, String name) {
        super(maxHp, speed, attack, name, numTargets);
    }

    /**
     * Enemy implementation of selectTarget. Adds every PlayerCharacter object in enemies into an Entity array and passes it into Entity implementation.
     * @see Entity#selectTarget
     */
    @Override
    protected Entity[] selectTarget(int mainTarget, Entity[] enemies, int attackType) {
        ArrayList<Entity> targetable = new ArrayList<>();
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] instanceof PlayerCharacter) {
                targetable.add(enemies[i]);
            }
        }
        Entity[] tempArr = new Entity[]{};
        return super.selectTarget(mainTarget, targetable.toArray(tempArr), 1);
    }
    
    /**
     * Enemy implementation of attack. Runs selectTarget with specified parameters, then runs normalAttack if attackType is 1 or skill if attackType is 2.
     * @see Entity#attack
     * @see Enemy#selectTarget
     * @see Entity#normalAttack
     * @see Enemy#skill
     */
    @Override
    public void attack(int attackType, int mainTarget, Entity[] enemies) {
        if (getName().equals("Zero-Line")){
            return;
        }
        if (attackType == 1) {
            Entity[] targets = selectTarget(mainTarget, enemies, 1);
            normalAttack(targets[mainTarget]);
        } else {
            Entity[] targets = selectTarget(mainTarget, enemies, 2);
            skill(targets);
        }
    }

    /**
     * Enemy implementation of skill. Enemies can only have Damage-type skills, so normalAttack is run on every Entity in targets.
     * @see Enemy#skill
     */
    @Override
    protected void skill(Entity[] targets) {
        for (int i = 0; i < targets.length; i++) {
            normalAttack(targets[i]);
        }
    }

    /**
     * Enemy implementation of turnBegin. Chooses randomly between normalAttack and skill, with a 70% chance for normal attack, then randomly chooses a mainTarget for the attack.
     * @see Entity#turnBegin
     */
    @Override
    public int turnBegin(int skillPointsAvailable, Entity[] targets) {
        if (getName().equals("Zero-Line")){
            return 0;
        }
        System.out.println(getName() + "'s turn!");
        Random random = new Random();
        int maxPercent = 100;
        int choice = random.nextInt(maxPercent);
        int targetable = selectTarget(0, targets, -1).length;
        int mainTarget = random.nextInt(targetable);
        int chanceForNormalAttack = 70;
        if (choice < chanceForNormalAttack) {
            int normalAttack = 1;
            attack(normalAttack, mainTarget, targets);
        } else {
            int skill = 2;
            attack(skill, mainTarget, targets);
        }
        return 0;
    }
}
