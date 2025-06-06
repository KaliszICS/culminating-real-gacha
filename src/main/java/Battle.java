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

    public void startBattle(ArrayList<Entity> entities) {
        //TODO: why an entities parameter when entities field exists
    }

    public boolean endBattle() {
        //TODO: why boolean
        return false;
    }

    public boolean nextBattle() {
        //TODO: same as above
        return false;
    }

    private void takeTurn(Entity entity) {
        //TODO: how do we know which entity is currently gonna go in the turn order is that taken care of elsewhere
    }

    private void displayTurnOrder(ArrayList<Entity> entities) {
        //TODO: again why entities parameter and also why void i really feel like it should be string
    }

    private void reOrganizeTurnOrder(Entity[] changedEntities) {
        //TODO: i feel like this entire method can kinda just be replaced with a sort at the end of taketurn
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
