import java.util.Random;

public class Shop {

    protected Item[] itemsOnSale;

    //This is where the items are created
    Item bazooka = 
    Item potion = 
    

    this.items = new Item[] {bazooka, potion};


    protected Item[] items = new Item[] {
        new Item("boosts team damage", 25),
        new Item("heals the team", 15),
        new Item("a", 0)
    };
    
    public Shop(Item[] items, Item[] itemsOnSale, Random random, int shopCurrency) {

        random = new Random();

        this.itemsOnSale = new Item[3];
        for (int i=0; i<this.itemsOnSale.length; i++) {
            this.itemsOnSale[i]=this.items[random.nextInt(10)];
        }
    }

    public void refresh(Random random) {
        
        random = new Random();
        this.itemsOnSale = new Item[3];
        
        for (int i=0; i<this.itemsOnSale.length; i++) {
            this.itemsOnSale[i]=this.items[random.nextInt(10)];
        }        
    }

    public void buy(Item item, int shopCurrency) {
    }

    //this is probably not void so change that future me
    public void search(Item item) {
    }
}