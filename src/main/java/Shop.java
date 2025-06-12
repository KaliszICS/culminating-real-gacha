import java.util.Random;
import java.util.ArrayList;

/**
 * A shop class that has 10 items in a constant array that can be bought, randomized into an ArrayList of items on sale, with a refresh method for refreshing the items on sale in the ArrayList, and a buy method that purchases the item from the shop
 * @author Gacha
 * @version 1.0.0
 */
public class Shop {

    //Array of Items (Constant)
    public Item[] ITEMS = new Item[] {
        new Item("Bazooka", 25, "Increase team CritDamage with this weapon of mass destruction!", "CritDamage", 2),
        new Item("Health Elixir", 15, "Increase team Max health with this magical elixir from Mc. Donalds!", "Health", 1),
        new Item("Wings", 15, "Increase team speed by having wing surgery on all team characters!", "Speed", 1),
        new Item("Mr. Kalisz", 35, "Increase team CritChance with a teacher to help educate and strategize!", "CritChance", 3),
        new Item("Macrophage", 25, "Increase team CritDamage with this life-sized immune cell!", "CritDamage", 2),
        new Item("Mom's Food", 35, "Increase team Max health with your team character's moms!", "Health", 3),
        new Item("Sleep Paralysis Demon", 25, "Increase team speed by having threatening them with their sleep paralysis demons!", "Speed", 2),
        new Item("Rubber Duck Army", 15, "Increase team CritChance with rubber ducks to boost team moral!", "CritChance", 1),
    };

    //ArrayList of items that are on sale; will show 3 at one time for the player to choose from, unless they refresh to see 3 randomly selected ones from the ITEMS array constant
    private ArrayList<Item> itemsOnSale;
    
    /**
     * Constructor that creates a shop and initializes the itemsOnSale ArrayList as a new ArrayList of type Item
     */
    public Shop() {
        this.itemsOnSale = new ArrayList<Item>();
    }

    /**
     * A refresh method that takes in an account parameter to refresh the items in the items on sale ArrayList; if the player does not have enough shop currency an error message plays, otherwise, it removes the currency from their account and randomizes 3 new items
     * @param account the player's account, so that the method can access their shopCurrency
     * @return A String that mentions whether the player was able to buy a shop resfresh or not
     */
    public void refresh(Account account) {
        
        //If they have enough currency, they can purchase a refresh
        if (account.getShopCurrency() >= 150) {

            account.setShopCurrency(account.getShopCurrency()-150);
            
            //Refreshes the items that are on sale
            Random random = new Random();
            this.itemsOnSale.clear();
            this.itemsOnSale.add(this.ITEMS[random.nextInt(ITEMS.length)]);
            this.itemsOnSale.add(this.ITEMS[random.nextInt(ITEMS.length)]);
            this.itemsOnSale.add(this.ITEMS[random.nextInt(ITEMS.length)]);

            System.out.println( "\nThe shop has been refreshed! The current items on sale are:\n");
            
            for (int i=0; i<this.itemsOnSale.size(); i++) {
                System.out.println(getItemsOnSale().get(i).getName() + ",  " + getItemsOnSale().get(i).getCost() + " shop currency\n"
                + getItemsOnSale().get(i).getEffectType() + ", Potency Level: " + getItemsOnSale().get(i).getEffectPotency() + "\n"
                + getItemsOnSale().get(i).getDesc() + "\n");
            }
        }

        //If they don't have enough currency, they can't purchase a refresh
        else {
            System.out.println("\nYou do not have enough shop currency to purchase an item on sale refresh.\n");
        }
    }

    /**
     * A buy method that takes in an item and an account to purchase the item from the shop and apply to the characters; it will take shop currency from the account and use the item apply method, and remove the item from the items on sale ArrayList
     * @param item the item the player wants to buy
     * @param account the player's account that the item should be applied to
     * @return A String message that mentions whether the item was able to be bought or not
     */

    public void buy(Item item, Account account) {
        //If item is valid, remove from itemsOnSale, take money from shopCurrency, and give to the account's team
        int nullTeamCounter = 4;

        for (int i=0; i<account.getTeam().getOnTeam().length; i++) {
            if (account.getTeam().getOnTeam()[i]!=null) {
                nullTeamCounter--;
            }
        }

        if (nullTeamCounter!=4) {
            this.itemsOnSale.remove(item);
            account.setShopCurrency(account.getShopCurrency()-item.getCost());
            System.out.println("\nYou have succesfully bought " + item.getName() + "!\n");
        }
        item.apply(account.getTeam());
    }

    /**
     * Getter method
     * @return An ArrayList<Item> of the items on Sale
     */
    public ArrayList<Item> getItemsOnSale() {
        return this.itemsOnSale;
    }
}