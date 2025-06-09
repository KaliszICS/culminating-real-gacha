import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    private int teammatesAlive;
    private int enemiesAlive;
    private ArrayList<Entity> entities;
    private int skillPoints;
    private int waveNum;
    private int battleReward = 0;
    public final int SKILLPOINT_MAX = 5;

    public Battle(ArrayList<PlayerCharacter> team) {
        this.teammatesAlive = team.size();
        this.enemiesAlive = 0;
        this.entities = new ArrayList<>();
        this.entities.addAll(team);
        this.skillPoints = 3; //start with 3, max 5
        this.waveNum = 0;
        this.battleReward = 0;
    }

    public void startBattle() {
        while (getEnemiesAlive() > 0 || getTeammatesAlive() > 0) {
            reOrganizeTurnOrder();
            takeTurn(getEntities().get(0));
        }
        if (getEnemiesAlive() == 0) {
            nextBattle();
        } else {
            endBattle();
        }
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
            getEntities().add(new Enemy(200 * getWaveNum(), 200 * getWaveNum(), 100 * getWaveNum(), 4, enemyName));
        }
        startBattle();
    }

    private void takeTurn(Entity entity) {
        entity.turnBegin();
        Entity[] targets;
        if (entity instanceof PlayerCharacter) {
            displayTurnOrder();
            targets = new Entity[getEnemiesAlive()];
            int foundCount = 0;
            for (Entity e : getEntities()) {
                if (e instanceof Enemy) {
                    targets[foundCount] = e;
                    foundCount++;
                }
            }
            Scanner scanner = new Scanner(System.in);
            boolean choosing = true;
            while (choosing) {
                System.out.println("What kind of attack would you like to do?");
                System.out.println("1 - Normal Attack");
                System.out.println("2 - Skill");
                System.out.println("Skill points available: " + getSkillPoints());
                System.out.print("Enter your choice: ");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice == 2 && getSkillPoints() == 0) {
                        System.out.println("Out of skill points, can't use skill.");
                    } else if (choice > 2 || choice < 1) {
                        System.out.println("Invalid number entered.");
                    } else {
                        System.out.println("Please choose your main target (target will be in the center of aoe): ");
                        for (int i = 0; i < targets.length; i++) {
                            System.out.println((i + 1) + " - " + targets[i].toString());
                        }
                        if (scanner.hasNextInt()) {
                            int mainTarget = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (mainTarget >= 0 && mainTarget < getEnemiesAlive()) {
                                entity.attack(choice, mainTarget, targets);
                                if (choice == 1) {
                                    setSkillPoints(Math.min(getSkillPoints() + 1, SKILLPOINT_MAX));
                                } else {
                                    setSkillPoints(Math.max(getSkillPoints() - 1, 0));
                                }
                                choosing = false;
                            }
                        }
                    }
                }
            }
            scanner.close();
        } else {
            targets = new Entity[getTeammatesAlive()];
            int foundCount = 0;
            for (Entity e : getEntities()) {
                if (e instanceof PlayerCharacter) {
                    targets[foundCount] = e;
                    foundCount++;
                }
            }
            Random random = new Random();
            int maxPercent = 100;
            int choice = random.nextInt(maxPercent);
            int mainTarget = random.nextInt(getTeammatesAlive());
            int chanceForNormalAttack = 70;
            if (choice < chanceForNormalAttack) {
                int normalAttack = 1;
                entity.attack(normalAttack, mainTarget, targets);
            } else {
                int skill = 2;
                entity.attack(skill, mainTarget, targets);
            }
        }
        for (int i = 0; i < targets.length; i++) {
            if (targets[i].getHp() <= 0) {
                getEntities().remove(targets[i]);
                if (entity instanceof PlayerCharacter) {
                    setTeammatesAlive(getEnemiesAlive() - 1);
                } else {
                    int baseRewardYield = 10;
                    setEnemiesAlive(getTeammatesAlive() - 1);
                    setBattleReward(getBattleReward() + baseRewardYield * getWaveNum());
                }
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
            int start = 0;
            int end = i - 1;
            int middle = (end + start) / 2;
            int compare = 0; 
            while (start <= end) {
                compare = getEntities().get(middle).compareTo(getEntities().get(i));
                if (compare < 0) {
                    start = middle + 1;
                    middle = (end + start) / 2;
                } else if (compare > 0) {
                    end = middle - 1;
                    middle = (end + start) / 2;
                } else {
                    start = end + 1;
                }
            }
            Entity temp = getEntities().get(i);
            getEntities().remove(i);
            if (compare < 0) {
                getEntities().add(middle + 1, temp);
            } else {
                getEntities().add(middle, temp);
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
