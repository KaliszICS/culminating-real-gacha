import java.util.Random;

/**
 * Class representing an enemy in battle, stores their difficulty and reward on death.
 * @author gacha
 * @version 1.0.1
 */
public class Enemy extends Entity {
    private int difficulty;
    private int reward;
    private int numTargets;

    /**
     * Creates a new entity with specified stats.
     * @param maxHp the max hp of the enemy
     * @param hp the current hp of the enemy
     * @param speed the speed stat of the enemy
     * @param attack the attack stat of the entity
     * @param numTargets the number of player characters the enemy can target
     * @param difficulty the difficulty value of the entity
     * @param reward the amount of currency the enemy awards on death
     */
    public Enemy(int maxHp, int speed, int attack, int numTargets, String name, int difficulty, int reward) {
        super(maxHp, speed, attack, name);
        this.difficulty = difficulty;
        this.numTargets = numTargets;
        this.reward = reward;
    }

    
    @Override
    public void attack(int attackType, int mainTarget, Entity[] enemies) {
        Random random = new Random();
        int result = random.nextInt(101);
        mainTarget = random.nextInt(5);
        if (result <= 70) {
            normalAttack(enemies[mainTarget]);
        } else {
            Entity[] targets = selectTarget(mainTarget, enemies);
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
    public void turnBegin() {
        System.out.println(getName() + "'s turn!");
    }


    /**
     * Returns the enemy's difficulty value.
     * @return the difficulty value of the enemy
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Changes the enemy's difficulty value
     * @param difficulty the new difficulty value of the enemy
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the amount of currency the enemy awards on death.
     * @return the amount of currency the enemy awards on death
     */
    public int getReward() {
        return reward;
    }

    /**
     * Changes the amount of currency the enemy awards on death.
     * @param reward the new amount of currency the enemy awards on death
     */
    public void setReward(int reward) {
        this.reward = reward;
    }

    /**
     * Returns the number of entities the enemy can target
     * @return the number of entities the enemy can target
     */
    public int getNumTargets() {
        return numTargets;
    }
}
