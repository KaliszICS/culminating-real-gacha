import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing an enemy in battle, stores their difficulty and reward on death.
 * @author gacha
 * @version 1.0.1
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
     * 
     */
    @Override
    protected void skill(Entity[] targets) {
        for (int i = 0; i < targets.length; i++) {
            normalAttack(targets[i]);
        }
    }

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
