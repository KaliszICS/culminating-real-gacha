import java.util.Random;

public class Shop {

    protected Item[] items;
    protected Item[] itemsOnSale;
    protected Random random;
    
    public Shop(String desc, int cost, Item[] items, Item[] itemsOnSale, Random random) {

        random = new Random();
        Item bazooka = new Item("boosts team damage", 25, false);
        Item potion = new Item("heals the team", 15, false);

        this.items = new Item[] {bazooka, potion};
        this.itemsOnSale = new Item[3];
        for (int i=0; i<this.itemsOnSale.length; i++) {
            this.itemsOnSale[i]=this.items[random.nextInt(10)];
        }
    }

    public void refresh() {
    }

    public void buy(Item item, int shopCurrency) {
        
    }

    public void exit() {
    }
}