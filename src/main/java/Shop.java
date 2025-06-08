import java.util.Random;
import java.util.ArrayList;

/**
 * 
 */
public class Shop {

    //Array of Items (Constant)
    public Item[] ITEMS = new Item[] {
        new Item("Bazooka", 25, "Boosts team damage", "Hallelujah", 5),
        new Item("Health Elixir", 15, "Boosts team health", "Health", 15),
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
     * @param account
     * @return
     */
    public String refresh(Account account) {
        
        //If they have enough currency, they can purchase a refresh
        if (account.getShopCurrency() >= 15) {

            account.setShopCurrency(account.getShopCurrency()-15);
            
            //Refreshes the items that are on sale
            Random random = new Random();
            this.itemsOnSale.clear();
            this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
            this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
            this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);

            return "The shop has been refreshed!";
        }

        //If they don't have enough currency, they can't purcahse a refresh
        else {
            return "You do not have enough shopCurrency to purchase an itemOnSale refresh";
        }
    }

    /**
     * 
     * @param item
     * @param account
     */
    public void buy(Item item, Account account) {

        //Make sure that the item is currently on sale
        boolean validItem = false;
        int validItemIndex = -1;

        for (int i = 0; i < itemsOnSale.size(); i++) {
            if (this.itemsOnSale.get(i)==item) {
                validItem = true;
                validItemIndex = i;
                i = this.itemsOnSale.size();
            }
        }

        //If item is valid, remove from itemsOnSale, take money from shopCurrenfcy and give to the account's team
        if (validItem==true) {
            this.itemsOnSale.remove(validItemIndex);
            account.setShopCurrency(account.getShopCurrency()-item.getCost());
            item.apply(account.getTeam());
        }
    }

    /**
     * Getter method
     * @return An ArrayList<Item> of the itemsOnSale
     */
    public ArrayList<Item> getItemsOnSale() {
        return this.itemsOnSale;
    }
}