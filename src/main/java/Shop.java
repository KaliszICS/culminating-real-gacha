import java.util.Random;
import java.util.ArrayList;

public class Shop {

    //Array of Items (Constant)
    public Item[] ITEMS = new Item[] {
        new Item("Bazooka", 25, "boosts team damage", "Ka-boom"),
        new Item("Health Elixir", 15, "boosts team helath", "Hallelujah"),
    };

    //ArrayList of items that are on sale; will shoe 3 at one time for the player to choose from, unless they refresh to see 3 different ones
    protected ArrayList<Item> itemsOnSale;
    
    public Shop() {
        
        this.itemsOnSale = new ArrayList<Item>();
    }

    public void refresh() {
        
        Random random = new Random();

        this.itemsOnSale.clear();
        this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
        this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
        this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
    }

    public void buy(Item item, int shopCurrency) {
    }

    //this is probably not void so change that future me
    public void search(Item item) {
    }
}