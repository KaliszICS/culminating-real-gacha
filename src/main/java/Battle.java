import java.util.ArrayList;

public class Battle {
    private ArrayList<PlayerCharacter> team;
    private ArrayList<Enemy> enemies;
    private ArrayList<Entity> entities;
    private int skillPoints;
    public final int SKILLPOINT_MAX = 5;

    public Battle(ArrayList<PlayerCharacter> team, ArrayList<Enemy> enemies) {
        this.team = team;
        this.enemies = enemies;
        this.entities = new ArrayList<>();
        this.entities.addAll(team);
        this.entities.addAll(enemies);
        this.skillPoints = 3; //start with 3, max 5
    }

    public void startBattle() {
    }

    public void endBattle() {
    }

    public void nextBattle() {
       
    }

    private void takeTurn(Entity entity) {
        entity.turnBegin();
        //note: how about trying to run attack in turn begin, so turnBegin now gets an entity[] parameter, then it asks for attack type, then it does select target
        //if attack type is heal we can find instanceOf playerCharacter
        int userChoiceAttackType = 0;//scanner to get normal or skill
        //depending on the attack type chosen
        int userChoiceMainTarget = 0;//scanner to get main target
        // battle should check if skill effect is heal or pull, so target will consist of playercharacters if true
        //entity.attack(userChoiceAttackType, userChoiceMainTarget, )

    }

    private void displayTurnOrder() {
        
    }

    private void reOrganizeTurnOrder() {

    }

    public ArrayList<PlayerCharacter> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<PlayerCharacter> team) {
        this.team = team;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }
}
