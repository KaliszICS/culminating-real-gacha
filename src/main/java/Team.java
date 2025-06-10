public class Team {
    private PlayerCharacter[] onTeam;
    private int numCharactersOnTeam;
    
    public Team() {
        this.onTeam = new PlayerCharacter[4];
        this.numCharactersOnTeam = 0;
    }

    public String displayPlayerCharacter() {
        String team = "Team:\n";
        for (int i = 0; i < onTeam.length; i++) {
            team += (i) + " - ";
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
                i=getOnTeam().length;
            }
        }
        getOnTeam()[openSpace] = character;
        setNumCharactersOnTeam(getNumCharactersOnTeam()+1);
    }

    public void removeFromTeam(int index) {
        if (getOnTeam()[index].getWeaponEquipped()!=null){
            getOnTeam()[index].getWeaponEquipped().setEquippedTo(null);
        }
        getOnTeam()[index].setWeaponEquipped(null);
        getOnTeam()[index] = null;
        setNumCharactersOnTeam(getNumCharactersOnTeam()-1);
    }

    public PlayerCharacter[] getOnTeam() {
        return onTeam;
    }

    public void setOnTeam(PlayerCharacter[] onTeam) {
        this.onTeam = onTeam;
    }

    public int getNumCharactersOnTeam() {
        return numCharactersOnTeam;
    }

    public void setNumCharactersOnTeam(int numCharactersOnTeam) {
        this.numCharactersOnTeam = numCharactersOnTeam;
    }
}
