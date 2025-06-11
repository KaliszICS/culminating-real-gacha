import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing a gacha pool for both character and weapon gacha
 * stores arrays of weapon and PlayerCharacter, seperated by rarity (4 star is seperate from 5 star)
 * java.util.Random is used to determine what is pulled
 * @version 1.0.0
 * @author gacha
 */
public class Gacha{

    private double rates4Star;
    private PlayerCharacter[] fourStarPC;
    private PlayerCharacter[] fiveStarPC;
    private Weapon[] fourStarWeapon;
    private Weapon[] fiveStarWeapon;

    public final int COST = 160; //160 gacha currency is taken from account for each pull
    public final int REPEAT_COMPENSATION = 100; //the shop currency given for each repetitive character obtained


    /**
     * creates a gacha pool that allows the arrays given in parameter to be pulled
     * @param playerCharacterList the list of PlayerCharacters that can be pulled
     * @param weapons the list of Weapons that can be pulled
     */
    public Gacha(PlayerCharacter[] playerCharacterList, Weapon[] weapons){

        this.rates4Star = 0.85;//85% chance to get a weapon of 4 star - > 15% for 5 star

        ArrayList<PlayerCharacter> fourStarCharacter = new ArrayList<>();
        ArrayList<PlayerCharacter> fiveStarCharacter = new ArrayList<>();
        ArrayList<Weapon> fourStarWp = new ArrayList<>();
        ArrayList<Weapon> fiveStarWp = new ArrayList<>();
        
        for (int i = 0; i<playerCharacterList.length; i++){//add a charater to their respective rarity's arraylist
            if (playerCharacterList[i].getRarity()==4){
                fourStarCharacter.add(playerCharacterList[i]);
            }
            else{
                fiveStarCharacter.add(playerCharacterList[i]);
            }
        }
        
        for (int i = 0; i<weapons.length; i++){//add a weapon to their respective rarity's arraylist
            if (weapons[i].getRarity()==4){
                fourStarWp.add(weapons[i]);
            }
            else{
                fiveStarWp.add(weapons[i]);
            }
        }

        //converts the arraylist into their repsective instance variable array
        fourStarPC = new PlayerCharacter[fourStarCharacter.size()];
        fourStarPC = fourStarCharacter.toArray(fourStarPC);
        fiveStarPC = new PlayerCharacter[fiveStarCharacter.size()];
        fiveStarPC = fiveStarCharacter.toArray(fiveStarPC);

        fourStarWeapon = new Weapon[fourStarWp.size()];
        fourStarWeapon = fourStarWp.toArray(fourStarWeapon);
        fiveStarWeapon = new Weapon[fiveStarWp.size()];
        fiveStarWeapon = fiveStarWp.toArray(fiveStarWeapon);

    }

    /**
     * returns the array of pulled weapons
     * @param numOfPulls the number of times the weapon pool is pulled from
     * @returnn the array pulled weapons
     */
    public Weapon[] pullWeapon(int numOfPulls){
        Random random = new Random();
        Weapon[] pulledWeapon = new Weapon[numOfPulls];

        for (int i = 0; i < numOfPulls; i++){
            if(random.nextDouble() <= rates4Star){//pulls from the 4 star list if the random given is under the four star rate
                pulledWeapon[i] = this.fourStarWeapon[random.nextInt(fourStarWeapon.length)];
                System.out.println(pulledWeapon[i].detailedToString());
                System.out.println();
            }
            else{//pulls from the 5 star list
                pulledWeapon[i] = this.fiveStarWeapon[random.nextInt(fiveStarWeapon.length)];
                System.out.println(pulledWeapon[i].detailedToString());
                System.out.println();
            }
        }
        return pulledWeapon;
    }

    /**
     * returns the array of pulled PlayerCharacters
     * @param numOfPulls the number of times the character pool is pulled from
     * @returnn the array pulled PlayerCharacters
     */
    public PlayerCharacter[] pullCharacter(int numOfPulls){
        Random random = new Random();
        PlayerCharacter[] pulledPC = new PlayerCharacter[numOfPulls];
        for (int i = 0; i < numOfPulls; i++){
            if(random.nextDouble() <= rates4Star){//pulls from the 4 star list if the random given is under the four star rate
                pulledPC[i] = this.fourStarPC[random.nextInt(fourStarPC.length)];
                System.out.println(pulledPC[i].detailedToString());
                System.out.println();
            }
            else{//pulls from the 5 star list
                pulledPC[i] = this.fiveStarPC[random.nextInt(fiveStarPC.length)];
                System.out.println(pulledPC[i].detailedToString());
                System.out.println();
            }
        }
        return pulledPC;
    }
}