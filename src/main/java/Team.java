public class Team {
    private PlayerCharacter[] onTeam;
    
    public Team() {
        this.onTeam = new PlayerCharacter[4];
    }

    public String displayPlayerCharacter() {
        String team = "Team:\n";
        for (PlayerCharacter pc : onTeam) {
            if (pc == null) {
                team += " - EMPTY\n";
            } else {
                team += " - " + pc.toString() + "\n";
            }
        }
        return team;
    }

    public void addToTeam(PlayerCharacter character, int index) {
        getOnTeam()[index] = character;
    }

    public void removeFromTeam(int index) {
        getOnTeam()[index] = null;
    }

    public PlayerCharacter[] getOnTeam() {
        return onTeam;
    }
}
