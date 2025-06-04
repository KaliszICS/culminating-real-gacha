class Shop {

    protected Item[] items;
    protected Item[] itemsOnSale;
    
    public Shop(String desc, int cost, Item[] items, Item[] itemsOnSale) {
        //super(desc, cost);
        this.items = items;
        this.itemsOnSale = new Item[3];

        Item sword = new Item("slashs players", 15);
    }

    public void refresh() {
    }

    public void buy(Item item) {
    }

    public void exit() {
    }
}