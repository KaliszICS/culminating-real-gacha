import java.util.Random;
import java.util.ArrayList;
/**
 * testing agin again again again
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
    protected ArrayList<Item> itemsOnSale;
    
    /**
     * Constructor that creates a shop and initializes the itemsOnSale ArrayList as a new ArrayList of type Item
     */
    public Shop() {
        this.itemsOnSale = new ArrayList<Item>();
    }

    /**
     * 
     * @param account the player's account, so that the methd can access their shopCurrency
     * @return A String that mentions whether the player was able to buy a shop resfresh or not
     */
    public void refresh(Account account) {
        
        //If they have enough currency, they can purchase a refresh
        if (account.getShopCurrency() >= 50) {

            account.setShopCurrency(account.getShopCurrency()-50);
            
            //Refreshes the items that are on sale
            Random random = new Random();
            this.itemsOnSale.clear();
            this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
            this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);
            this.itemsOnSale.add(this.ITEMS[random.nextInt(10)]);

            System.out.println( "The shop has been refreshed!");
            System.out.println(getItemsOnSale());
        }

        //If they don't have enough currency, they can't purchase a refresh
        else {
            System.out.println("You do not have enough shop currency to purchase an item on sale refresh");
            System.out.println(getItemsOnSale());
        }
    }

    /**
     * 
     * @param item the item the player wants to buy
     * @param account the player's account that the item should be applied to
     * @return A String message that mentions whether the item was able to be bought or not
     */
    public String buy(Item item, Account account) {

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

        //If item is valid, remove from itemsOnSale, take money from shopCurrency, give to the account's team, and give output message
        if (validItem==true) {
            this.itemsOnSale.remove(validItemIndex);
            account.setShopCurrency(account.getShopCurrency()-item.getCost());
            item.apply(account.getTeam());
            return "You have succesfully bought " + item.getName() + "!";
        }

        //If item not valid, give output message
        return "This item is currently not on sale. Buy a shop refresh to see if the preferred item comes on sale!";
    }

    /**
     * Getter method
     * @return An ArrayList<Item> of the itemsOnSale
     */
    public ArrayList<Item> getItemsOnSale() {
        return this.itemsOnSale;
    }
}