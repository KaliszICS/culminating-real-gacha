import java.util.ArrayList;

public class Battle {
    private int teammatesAlive;
    private int enemiesAlive;
    private ArrayList<Entity> entities;
    private int skillPoints;
    private int waveNum;
    private int battleReward = 0;
    public final int SKILLPOINT_MAX = 5;

    public Battle(PlayerCharacter[] team) {

        this.teammatesAlive =0;
        this.enemiesAlive = 0;
        this.entities = new ArrayList<>();
        for (int i = 0; i<team.length; i++){
            if (team[i]!=null){
                this.entities.add(team[i]);
                team[i].weaponEffect();
                setTeammatesAlive(getTeammatesAlive()+1);
            }
        }
        getEntities().add(new Enemy(1000000000, 0, 0, 0, "Zero-Line"));

        this.skillPoints = 3; //start with 3, max 5
        this.waveNum = 0;
        this.battleReward = 0;
    }

    public void startBattle() {
        while (getTeammatesAlive() > 2){
            if (getEnemiesAlive()==0){     
                nextBattle();     
            }   
            takeTurn(getEntities().get(0));
        }
            endBattle();
    }

    public void endBattle() {
        System.out.println("Game over!");
        System.out.println("Reward payout: " + getBattleReward());
        // at this point home should add payout to account gacha currency/shop currency
    }

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
        startBattle();
    }

    private void takeTurn(Entity entity) {
        if (entity.getName().equals("Zero-Line")){
            for (int i = 1; i<getEntities().size(); i++){
                getEntities().get(i).setActionPoints(getEntities().get(i).getActionPoints()+getEntities().get(i).getSpeed());
            }
        }
        reOrganizeTurnOrder();  
        displayTurnOrder();
        System.out.println();
        Entity[] entityArr = new Entity[]{};
        entityArr = (Entity[]) getEntities().toArray(entityArr);
        setSkillPoints(getSkillPoints() - entity.turnBegin(getSkillPoints(), entityArr));
        if (getSkillPoints()>5){
            setSkillPoints(5);
        }
        entity.turnEnd();
        for (Entity e : entityArr) {
            if (e.getHp() <= 0) {
                if (e instanceof PlayerCharacter) {
                    setTeammatesAlive(getTeammatesAlive() - 1);
                } else {
                    int baseRewardYield = 10;
                    setEnemiesAlive(getEnemiesAlive() - 1);
                    setBattleReward(getBattleReward() + baseRewardYield * getWaveNum());
                }
                getEntities().remove(e);
            }
        }
    }

    private void displayTurnOrder() {
        System.out.println("Turn order:");
        for (Entity e : getEntities()) {
            System.out.println(e.toString());
        }
    }

    private void reOrganizeTurnOrder() {
        for (int i = 1; i < getEntities().size(); i++) {
            // if (getEntities().get(i).isActionPointChanged()){
            int start = 0;
            int end = i - 1;
            int middle = 0;
            int compare = 0;
            boolean found = false;
            while (start <= end) {
                compare = getEntities().get(middle).compareTo(getEntities().get(i));
                middle = (end + start) / 2;
                if (compare < 0) {
                    start = middle + 1;
                } else if (compare > 0) {
                    end = middle - 1;
                } else {
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

    public int getTeammatesAlive() {
        return this.teammatesAlive;
    }

    public void setTeammatesAlive(int teammatesAlive) {
        this.teammatesAlive = teammatesAlive;
    }

    public int getEnemiesAlive() {
        return this.enemiesAlive;
    }

    public void setEnemiesAlive(int enemiesAlive) {
        this.enemiesAlive = enemiesAlive;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public int getWaveNum() {
        return waveNum;
    }

    public void setWaveNum(int waveNum) {
        this.waveNum = waveNum;
    }

    public int getBattleReward() {
        return battleReward;
    }

    public void setBattleReward(int battleReward) {
        this.battleReward = battleReward;
    }
}
