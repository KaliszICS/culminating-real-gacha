
class Item {

    protected String desc;
    protected int cost;
    protected boolean owned;

    /**
     * Creates an item object that has a description, cost, and checks whether the player has the item already
     * @param desc
     * @param cost
     * @param owned
     */
    public Item(String desc, int cost, boolean owned) {
        this.desc = desc;
        this.cost = cost;
        this.owned = owned;
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

    /**
     *  Getter Method
     * @param item the item parameter
     * @return true if the player owns the item, false otherwise
     */
    public boolean getOwned(Item item) {
        return this.owned;
    }
}