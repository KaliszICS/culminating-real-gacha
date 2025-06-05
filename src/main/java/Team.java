import java.util.ArrayList;

public class Team {
    private ArrayList<PlayerCharacter> onTeam;
    
    public Team(ArrayList<PlayerCharacter> onTeam) {
        this.onTeam = onTeam;
    }

    public String displayPlayerCharacter() {
        String team = "Team:\n";
        for (PlayerCharacter pc : onTeam) {
            team += " - " + pc.toString() + "\n";
        }
        return team;
    }

    public ArrayList<PlayerCharacter> getOnTeam() {
        return onTeam;
    }

    public void changeTeam() {
        
    }

    private void addToTeam(PlayerCharacter character, int index) {
        getOnTeam().add(index, character);
    }

    private void removeFromTeam(int index) {
        getOnTeam().remove(index);
    }
}
