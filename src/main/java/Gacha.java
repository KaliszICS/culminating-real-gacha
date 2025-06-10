import java.util.Random;
import java.util.ArrayList;

public class Gacha{

    private double rates4Star;
    private PlayerCharacter[] fourStarPC;
    private PlayerCharacter[] fiveStarPC;
    private Weapon[] fourStarWeapon;
    private Weapon[] fiveStarWeapon;

    public final int COST = 160;
    public final int REPEAT_COMPENSATION = 100;

    public Gacha(PlayerCharacter[] playerCharacterList, Weapon[] weapons){
    
        this.rates4Star = 0.85;//85% chance to get a 4-star - > 15% for 5-star

        ArrayList<PlayerCharacter> fourStarCharacter = new ArrayList<>();
        ArrayList<PlayerCharacter> fiveStarCharacter = new ArrayList<>();
        ArrayList<Weapon> fourStarWp = new ArrayList<>();
        ArrayList<Weapon> fiveStarWp = new ArrayList<>();
        
        for (int i = 0; i<playerCharacterList.length; i++){
            if (playerCharacterList[i].getRarity()==4){
                fourStarCharacter.add( playerCharacterList[i]);
            }
            else{
                fiveStarCharacter.add( playerCharacterList[i]);
            }
        }
        
        for (int i = 0; i<weapons.length; i++){
            if (weapons[i].getRarity()==4){
                fourStarWp.add(weapons[i]);
            }
            else{
                fiveStarWp.add(weapons[i]);
            }
        }

        fourStarPC = new PlayerCharacter[fourStarCharacter.size()];
        fourStarPC = fourStarCharacter.toArray(fourStarPC);
        fiveStarPC = new PlayerCharacter[fiveStarCharacter.size()];
        fiveStarPC = fiveStarCharacter.toArray(fiveStarPC);

        fourStarWeapon = new Weapon[fourStarWp.size()];
        fourStarWeapon = fourStarWp.toArray(fourStarWeapon);
        fiveStarWeapon = new Weapon[fiveStarWp.size()];
        fiveStarWeapon = fiveStarWp.toArray(fiveStarWeapon);

    }

    public Weapon[] pullWeapon(int numOfPulls){
        Random random = new Random();
        Weapon[] pulledWeapon = new Weapon[numOfPulls];
        for (int i = 0; i < numOfPulls; i++){
            if(random.nextDouble() <= rates4Star){
                pulledWeapon[i] = this.fourStarWeapon[random.nextInt(fourStarWeapon.length)];
                System.out.println(pulledWeapon[i].detailedToString());
            }
            else{
                pulledWeapon[i] = this.fiveStarWeapon[random.nextInt(fiveStarWeapon.length)];
                System.out.println(pulledWeapon[i].detailedToString());
            }
        }
        return pulledWeapon;
    }

    public PlayerCharacter[] pullCharacter(int numOfPulls){
        Random random = new Random();
        PlayerCharacter[] pulledPC = new PlayerCharacter[numOfPulls];
        for (int i = 0; i < numOfPulls; i++){
            if(random.nextDouble() <= rates4Star){
                pulledPC[i] = this.fourStarPC[random.nextInt(fourStarPC.length)];
                System.out.println(pulledPC[i].detailedToString());
            }
            else{
                pulledPC[i] = this.fiveStarPC[random.nextInt(fiveStarPC.length)];
                System.out.println(pulledPC[i].detailedToString());
            }
        }
        return pulledPC;
    }
}