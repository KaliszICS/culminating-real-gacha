class Item {

    protected String desc;
    protected int cost;
    protected String name;
    protected String effect;

    /**
     * Creates an item object that has a description, cost, and checks whether the player has the item already
     * @param desc The description of the item
     * @param cost The cost of the item
     * @param name The name of the item
     * @param effect The 
     */
    public Item(String desc, int cost, String ) {
        this.desc = desc;
        this.cost = cost;
    }

    /**
     * Someone do this idk what team does - aarooran
     * @param team
     */
    public void apply(Team team) {
    }

    /**
     * Getter Method
     * @param item item parameter
     * @return the item description
     */
    public String getDesc(Item item) {
        return this.desc;
    }

    /**
     * Getter Method
     * @param item item parameter
     * @return the item cost
     */
    public int getCost(Item item) {
        return this.cost;
    }
}