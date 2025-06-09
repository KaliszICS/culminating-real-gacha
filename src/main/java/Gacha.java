import java.util.Random;
public class Gacha{

    private double rates4Star;
    private double rates5Star;
    private PlayerCharacter[] fourStarPC;
    private PlayerCharacter[] fiveStarPC;
    private Weapon[] fourStarWeapon;
    private Weapon[] fiveStarWeapon;

    public final int COST = 160;
    public final int REPEAT_COMPENSATION = 100;

    public Gacha(PlayerCharacter[] playerCharacterList, Weapon[] weapons){
    
        this.rates4Star = 0.95;//95% chance to get a 4-star
        this.rates5Star = 1-this.rates4Star;
        int index4StarList = 0;
        int index5StarList = 0;

        fourStarPC = new PlayerCharacter[playerCharacterList.length];
        fiveStarPC= new PlayerCharacter[playerCharacterList.length];
        fourStarWeapon = new Weapon[weapons.length];
        fiveStarWeapon = new Weapon[weapons.length];
        
        for (int i = 0; i<playerCharacterList.length; i++){
            if (playerCharacterList[i].getRarity()==4){
                this.fourStarPC[index4StarList] = playerCharacterList[i];
                index4StarList++;
            }
            else{
                this.fiveStarPC[index5StarList] = playerCharacterList[i];
                index5StarList++;
            }
        }
        
        index4StarList = 0;
        index5StarList = 0;
        
        for (int i = 0; i<weapons.length; i++){
            if (weapons[i].getRarity()==4){
                fourStarWeapon[index4StarList] = weapons[i];
                index4StarList++;
            }
            else{
                fiveStarWeapon[index5StarList] = weapons[i];
                index5StarList++;
            }
        }
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