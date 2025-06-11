import java.util.ArrayList;
/**
 * Class representing a battlefield
 * Stores number of enemeis and teammates alive, the entities in battle, skillpoint, current wave number, and battle reward
 * @version 1.0.0
 * @author gacha
 */
public class Battle {

    private int teammatesAlive;
    private PlayerCharacter[] team;
    private int enemiesAlive;
    private ArrayList<Entity> entities;
    private int skillPoints;
    private int waveNum;
    private int battleReward = 0;

    public final int SKILLPOINT_MAX = 5;

    /**
     * creates a battle with the team given
     * @param team the team that will be in battle
     */
    public Battle(PlayerCharacter[] team) {
        this.team = team;
        this.teammatesAlive =0;
        this.enemiesAlive = 0;
        this.entities = new ArrayList<>();

        for (int i = 0; i<team.length; i++){
            if (team[i]!=null){//if character on team have a weapon, their special effects and modifiers are applied here
                this.entities.add(team[i]);
                team[i].weaponEffect();
                team[i].setAttack(team[i].getAttack() + team[i].getWeaponEquipped().getAttackMod());
                team[i].setMaxHp(team[i].getWeaponEquipped().getHpMod()+team[i].getMaxHpDefault());
                setTeammatesAlive(getTeammatesAlive()+1);
            }
        }
        //add an entity to combat called "Zero-Line", which, during its turn, does not attack but adds every entity's speed to their action point
        //the Zero-Line makes it so that only entities with >=0 action point can have their turn
        getEntities().add(new Enemy(1000000000, 0, 0, 0, "Zero-Line"));

        this.skillPoints = 3; //start with 3, max 5, used each time a PlayerCharacter uses their skill
        this.waveNum = 0;
        this.battleReward = 0;
    }

    /**
     * starts the battle
     */
    public void startBattle() {
        while (getTeammatesAlive() > 0 && getEnemiesAlive() > 0) { 
            reOrganizeTurnOrder();
            takeTurn(getEntities().get(0));//the entity on top of the turn order takes their turn                
        }
        if (getTeammatesAlive() <= 0) {//if the entire team died, battle ends
            endBattle();
            
        } else {
            nextBattle();
        }
    }

    /**
     * ends the battle and resets the team's attack and max hp to before weapons are applied
     */
    public void endBattle() {
        for (int i = 0; i<team.length; i++){
            if (team[i]!=null){//if character on team have a weapon, their special effects and modifiers are applied here
                team[i].setAttack(team[i].getAttackDefault());
                team[i].setMaxHp(team[i].getMaxHpDefault());
            }
        }
        System.out.println("Game over!");
        System.out.println("Reward payout: " + getBattleReward());
        // at this point home should add payout to account gacha currency/shop currency
    }

    /**
     * adds new enemies to the ArrayList<Entities> with respect to the current wave number, after the previous wave is done
     */
    public void nextBattle() {
        int startEnemies = 3;
        int startThreshold = 3;
        String startName = "Slime";
        int middleEnemies = 5;
        int middleThreshold = 6;
        String middleName = "Bandit";
        int deepEnemies = 7;
        String deepEnemy = "Golem";
        setWaveNum(getWaveNum() + 1);
        String enemyName = "";
        System.out.println("Wave " + getWaveNum());
        if (getWaveNum() <= startThreshold) {
            setEnemiesAlive(startEnemies);
            enemyName = startName;
        } else if (getWaveNum() <= middleThreshold) {
            setEnemiesAlive(middleEnemies);
            enemyName = middleName;
        } else {
            setEnemiesAlive(deepEnemies);
            enemyName = deepEnemy;
        }
        for (int i = 0; i < getEnemiesAlive(); i++) {
            getEntities().add(new Enemy(3000 * getWaveNum(), 80 + 10*getWaveNum(), 100 * getWaveNum(), 4, enemyName + (i + 1)));
        }
        startBattle();//starts battle again with new enemies
    }

    /**
     * lets the entity given by parameter take turn
     * @param entity the entity that takes their turn
     */
    private void takeTurn(Entity entity) {  
        displayTurnOrder();//shows currently who's alive and who's coming up
        System.out.println();

        if (entity.getName().equals("Zero-Line")){//if the current entity is the Zero-line, it adds the speed of every entity in battle to their action point
            for (int i = 1; i<getEntities().size(); i++){
                getEntities().get(i).setActionPoints(getEntities().get(i).getActionPoints()+getEntities().get(i).getSpeed());
            }
            return;
        }

        Entity[] entityArr = new Entity[]{};
        entityArr = (Entity[]) getEntities().toArray(entityArr);//converts the arraylist of entities to an array to be used in entity.turnBegin
        setSkillPoints(getSkillPoints() - entity.turnBegin(getSkillPoints(), entityArr));//entity.turnBegin returns the skill point they used
        if (getSkillPoints()>5){//skillPoint capped at 5
            setSkillPoints(5);
        }

        entity.turnEnd();
        for (Entity e : entityArr) {
            if (e.getHp() <= 0) {//checks who died and remove them from battle
                if (e instanceof PlayerCharacter) {
                    setTeammatesAlive(getTeammatesAlive() - 1);
                } else {
                    int baseRewardYield = 50;//they get 50 gacha currencies per enemy defeated times the current wave num (difficulty of enemy)
                    setEnemiesAlive(getEnemiesAlive() - 1);
                    setBattleReward(getBattleReward() + baseRewardYield * getWaveNum());
                }
                getEntities().remove(e);
            }
        }
    }

    /**
     * prints a string of the current turn order in the following format: <br><br>
     * Turn order: <br>
     * (first entity.toString())<br>
     * (second entity.toString())<br>
     * ect.
     */
    private void displayTurnOrder() {
        System.out.println("Turn order:");
        for (Entity e : getEntities()) {
            System.out.println(e.toString());
        }
    }

    /**
     * uses binary insertion sort to reOrganize the turn order from highest aciton point to lowest
     */
    private void reOrganizeTurnOrder() {
        for (int i = 1; i < getEntities().size(); i++) {
            int start = 0;
            int end = i - 1;
            int middle = 0;
            int compare = 0;
            boolean found = false;
            while (start <= end) {
                middle = (end + start) / 2;
                compare = getEntities().get(middle).compareTo(getEntities().get(i));//negative if middle faster
                if (compare < 0) {
                    start = middle + 1;
                } 
                else if (compare > 0) {
                    end = middle - 1;
                } 
                else {
                    start = end + 1;
                    found = true;
                }
            }
            Entity temp = getEntities().get(i);
            getEntities().remove(i);
            if (found) {
                getEntities().add(middle, temp);
            } else {
                getEntities().add(start, temp);
            }
        }
    }

    /**
     * returns the number of teammates currently alive
     * @return the number of teammates currently alive
     */
    public int getTeammatesAlive() {
        return this.teammatesAlive;
    }

    /**
     * changes the number of teammates currently alive to given param
     * @param teammatesAlive the new number of teammates alive
     */
    public void setTeammatesAlive(int teammatesAlive) {
        this.teammatesAlive = teammatesAlive;
    }

    /**
     * returns the number of enemies currently alive
     * @return the number of enemies currently alive
     */
    public int getEnemiesAlive() {
        return this.enemiesAlive;
    }

    /**
     * changes the number of enemies currently alive to given param
     * @param enemiesAlive the number of enemies currently alive
     */
    public void setEnemiesAlive(int enemiesAlive) {
        this.enemiesAlive = enemiesAlive;
    }

    /**
     * returns the arraylist of entities in battle
     * @return the arraylist of entities in battle
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }
    
    /**
     * changes the arraylist of entities in battle to given arraylist
     * @param entities the arraylist of entities in battle
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    /**
     * returns the number of skillpoint that the team can use for skill
     * @return the number of skillpoint that the team can use for skill
     */
    public int getSkillPoints() {
        return skillPoints;
    }

    /**
     * changes the number of skillpoint that the team can use for skill to given param
     * @param skillPoints the number of skillpoint that the team can use for skill
     */
    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    /**
     * returns the current wave number
     * @return the current wave number
     */
    public int getWaveNum() {
        return waveNum;
    }

    /**
     * changes the current wave number to the parameter given
     * @param waveNum the new wave number
     */
    public void setWaveNum(int waveNum) {
        this.waveNum = waveNum;
    }

    /**
     * returns the battle reward obtained from defeating enemies
     * @return the battle reward obtained from defeating enemies
     */
    public int getBattleReward() {
        return battleReward;
    }

    /**
     * changes the battle reward to given parameter
     * @param battleReward the new battle reward
     */
    public void setBattleReward(int battleReward) {
        this.battleReward = battleReward;
    }
}
