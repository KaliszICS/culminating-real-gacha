public class Team {
    private PlayerCharacter[] onTeam;
    private int charactersOnTeam;
    
    public Team() {
        this.onTeam = new PlayerCharacter[4];
        this.charactersOnTeam = 0;
    }

    public String displayPlayerCharacter() {
        String team = "Team:\n";
        for (int i = 0; i < onTeam.length; i++) {
            team += (i + 1) + " - ";
            if (getOnTeam()[i] == null) {
                team += "EMPTY\n";
            } else {
                team += getOnTeam()[i].toString() + "\n";
            }
        }
        return team;
    }

    public void addToTeam(PlayerCharacter character) {
        int openSpace = 0;
        for (int i = 0; i<getOnTeam().length; i++){
            if (getOnTeam()[i]==null){
                openSpace = i;
            }
        }
        getOnTeam()[openSpace] = character;
        setCharactersOnTeam(getCharactersOnTeam()+1);
    }

    public void removeFromTeam(int index) {
        getOnTeam()[index] = null;
        setCharactersOnTeam(getCharactersOnTeam()-1);
    }

    public PlayerCharacter[] getOnTeam() {
        return onTeam;
    }

    public void setOnTeam(PlayerCharacter[] onTeam) {
        this.onTeam = onTeam;
    }

    public int getCharactersOnTeam() {
        return charactersOnTeam;
    }

    public void setCharactersOnTeam(int charactersOnTeam) {
        this.charactersOnTeam = charactersOnTeam;
    }
}
