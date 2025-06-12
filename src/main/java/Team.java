/**
 * Class representing a team of PlayerCharacters, stores the array of PlayerCharacters, to a max of 4 on team
 * @version 1.0.0
 * @author gacha
 */
public class Team {
    private PlayerCharacter[] onTeam;
    private int numCharactersOnTeam;
    
    /**
     * creates an empty team. 
     */
    public Team() {
        this.onTeam = new PlayerCharacter[4];//4 is the max amount of PlayerCharacters allowed on team
        this.numCharactersOnTeam = 0;
    }
    
    /**
     * adds a PlayerCharacter to the first empty space on the team, increase the number of characters on team by 1
     */
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

    /**
     * Remove a character from team using the given parameter as index of the character on team
     * Also unequips the weapon from the character
     * @param index the index of the character to remove from team
     */
    public void removeFromTeam(int index) {
        if (getOnTeam()[index].getWeaponEquipped()!=null){
            getOnTeam()[index].getWeaponEquipped().setEquippedTo(null);
        }
        getOnTeam()[index].setWeaponEquipped(null);
        getOnTeam()[index] = null;
        setNumCharactersOnTeam(getNumCharactersOnTeam()-1);
    }

    /**
     * returns the array of PlayerCharacters on the team
     * @return the array of PlayerCharacters on the team
     */
    public PlayerCharacter[] getOnTeam() {
        return onTeam;
    }

    /**
     * returns the number of characters currently on team
     * @return the number of characters currently on team
     */
    public int getNumCharactersOnTeam() {
        return numCharactersOnTeam;
    }

    /**
     * changes the number of characters currently on team to the given parameter
     */
    public void setNumCharactersOnTeam(int numCharactersOnTeam) {
        this.numCharactersOnTeam = numCharactersOnTeam;
    }

    /**
     * Returns a string representation of the entire team. The string will be formatted as follows:<br><br>
     * Team: <br>
     * 0 - (current character on index 0 of team)<br>
     * 1 - (current character on index 1 of team)<br>
     * 2 - (current character on index 2 of team)<br>
     * 3 - (current character on index 3 of team)<br>
     * for an index with no character, it will be index - EMPTY
     * i.e. 0 - EMPTY
     * @return string representation of the team
     */
    @Override
    public String toString() {
        String team = "\nTeam:\n";
        for (int i = 0; i < onTeam.length; i++) {
            
            team += (i) + " - ";
            if (getOnTeam()[i] == null) {
                team += "EMPTY\n";
            }
            
            else {
                team += getOnTeam()[i].toString() + "\n";
            }
        }
        
        return team;
    }
}
