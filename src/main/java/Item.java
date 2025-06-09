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
     * Creates an item object that has a description, cost, and checks whether the player has the item already
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
     * Someone do this idk what team does - aarooran - i lied ill do this eventually
     * @param team
     */
    public void apply(Team team) {

        switch (getEffectType()) {

            case "":
                break; //does not have special effect

            case "Speed":
                for (int i=0; i<team.getOnTeam().length; i++) {
                    team.getOnTeam()[i].setSpeed(team.getOnTeam()[i].getSpeed() + (getEffectPotency()*5));
                }
                break;

            case "CritChance": 
                for (int i=0; i<team.getOnTeam().length; i++) {
                    team.getOnTeam()[i].setCritChance(team.getOnTeam()[i].getCritChance() + (getEffectPotency()*5));
                }
                break;

            case "CritDamage":
                for (int i=0; i<team.getOnTeam().length; i++) {
                    team.getOnTeam()[i].setCritDamage(team.getOnTeam()[i].getCritDamage() + (getEffectPotency()*5));
                }
                break;

            case "Health":
                for (int i=0; i<team.getOnTeam().length; i++) {
                    team.getOnTeam()[i].setMaxHp(team.getOnTeam()[i].getMaxHp() + (getEffectPotency()*5));
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