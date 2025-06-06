import java.util.ArrayList;

public class Battle {
    private ArrayList<PlayerCharacter> team;
    private ArrayList<Enemy> enemies;
    private ArrayList<Entity> entities;
    private int skillPoints;

    public Battle(ArrayList<PlayerCharacter> team, ArrayList<Enemy> enemies, int skillPoints) {
        this.team = team;
        this.enemies = enemies;
        this.entities = new ArrayList<>();
        this.entities.addAll(team);
        this.entities.addAll(enemies);
        this.skillPoints = skillPoints;
    }

    public void startBattle() {
    }

    public void endBattle() {
    }

    public void nextBattle() {
       
    }

    private void takeTurn(Entity entity) {

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
