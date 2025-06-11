/**
 * 
 */
class Item {

    //Instance Variables
    protected String name;
    protected int cost;
    protected String desc;
    protected String effectType;
    protected int effectPotency;

    /**
     * Constructor that creates an item object that has a name, cost, description, effect type and effect potency
     * @param name The name of the item
     * @param cost The cost of the item
     * @param desc The description of the item
     * @param effectType The item effect type
     * @param effectPotency The item effect potency
     */
    public Item(String name, int cost, String desc, String effectType, int effectPotency) {
        this.name = name;
        this.cost = cost;
        this.desc = desc;
        this.effectType = effectType;
        this.effectPotency = effectPotency;
    }

    /**
     * 
     * @param team
     */
    public void apply(Team team) {

        //Switch goes through to find the matching item effect type, then applies the affect potency to the each player in the team in regards to the effect type
        switch (getEffectType()) {

            case "Speed":
                //Have a counter that checks to see if there are 0 characters on team, in which a message will tell the player accordingly
                int speedCounter = 4;

                for (int i=0; i<team.getOnTeam().length; i++) {

                    if (team.getOnTeam()[i]!=null) {
                        team.getOnTeam()[i].setSpeed(team.getOnTeam()[i].getSpeed() + (getEffectPotency()*5));
                        speedCounter--;
                    }
                }

                if (speedCounter==4) {
                    System.out.println("\nYou do not have any characters on your team, have a minimum of one character on your team before buying an item.\n");
                }
                
                break;

            case "CritChance":
                //Have a counter that checks to see if there are 0 characters on team, in which a message will tell the player accordingly
                int critChanceCounter = 4;

                for (int i=0; i<team.getOnTeam().length; i++) {

                    if (team.getOnTeam()[i]!=null) {
                        team.getOnTeam()[i].setCritChance(team.getOnTeam()[i].getCritChance() + (getEffectPotency()*5));
                        critChanceCounter--;
                    }
                }

                if (critChanceCounter==4) {
                    System.out.println("You do not have any characters on your team, have a minimum of one character on your team before buying an item");
                }

                break;

            case "CritDamage":
                //Have a counter that checks to see if there are 0 characters on team, in which a message will tell the player accordingly
                int critDamageCounter = 4;

                for (int i=0; i<team.getOnTeam().length; i++) {

                    if (team.getOnTeam()[i]!=null) {
                        team.getOnTeam()[i].setCritDamage(team.getOnTeam()[i].getCritDamage() + (getEffectPotency()*5));
                        critDamageCounter--;
                    }
                }

                if (critDamageCounter==4) {
                    System.out.println("You do not have any characters on your team, have a minimum of one character on your team before buying an item");
                }
                
                break;

            case "Health":
                //Have a counter that checks to see if there are 0 characters on team, in which a message will tell the player accordingly
                int healthCounter = 4;

                for (int i=0; i<team.getOnTeam().length; i++) {

                    if (team.getOnTeam()[i]!=null) {
                        team.getOnTeam()[i].setMaxHp(team.getOnTeam()[i].getMaxHp() + (getEffectPotency()*5));
                        healthCounter--;
                    }
                }

                if (healthCounter==4) {
                    System.out.println("You do not have any characters on your team, have a minimum of one character on your team before buying an item");
                }

                break;
        }
    }

    /**
     * Getter Method
     * @return String of the item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter Method
     * @return int of the item cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Getter Method
     * @return String of the item description
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Getter Method
     * @return String of the item effect type
     */
    public String getEffectType() {
        return this.effectType;
    }

    /**
     * Getter Method
     * @return int of the item effect potency
     */
    public int getEffectPotency() {
        return this.effectPotency;
    }
}