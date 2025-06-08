/**
 * 
 */
class Item {

    //Instance Variables
    protected String desc;
    protected int cost;
    protected String name;
    protected String effect;

    /**
     * Creates an item object that has a description, cost, and checks whether the player has the item already
     * @param name The name of the item
     * @param cost The cost of the item
     * @param desc The description of the item
     * @param effect The item effect
     */
    public Item(String name, int cost, String desc, String effect) {
        this.name = name;
        this.cost = cost;
        this.desc = desc;
        this.effect = effect;
    }

    /**
     * Someone do this idk what team does - aarooran
     * @param team
     */
    public void apply(Team team) {
    }

    /**
     * Getter Method
     * @return the item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter Method
     * @return the item cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Getter Method
     * @return the item description
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Getter Method
     * @return the item effect
     */
    public String getEffect() {
        return this.effect;
    }
}