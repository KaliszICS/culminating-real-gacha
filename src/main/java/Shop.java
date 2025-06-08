import java.util.Random;
import java.util.ArrayList;

/**
 * 
 */
public class Shop {

    //Array of Items (Constant)
    public Item[] ITEMS = new Item[] {
        new Item("Bazooka", 25, "boosts team damage", "Ka-boom"),
        new Item("Health Elixir", 15, "boosts team health", "Hallelujah"),
    };

    //ArrayList of items that are on sale; will show 3 at one time for the player to choose from, unless they refresh to see 3 different ones
    protected ArrayList<Item> itemsOnSale;
    
    /**
     * Constructor
     */
    public Shop() {
        this.itemsOnSale = new ArrayList<Item>();
    }

    /**
     * 
     */
    public void refresh(Account account) {
        
        if (account.getShopCurrency() >= 15) {
            
        }

        Random random = new Random();

        this.itemsOnSale.clear();
        this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
        this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
        this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
    }

    /**
     * 
     * @param item
     * @param account
     */
    public void buy(Item item, Account account) {
    }

    /**
     * 
     * @param item
     */
    public void search(Item item) {
    }

    /**
     * Getter method
     * @return the itemsOnSale ArrayList
     */
    public ArrayList<Item> getItemsOnSale() {
        return this.itemsOnSale;
    }
}