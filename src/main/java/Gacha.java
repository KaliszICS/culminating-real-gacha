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
    
        rates4Star = 0.95;
        rates5Star = 1-rates4Star;
        int index = 0;
        while(playerCharacterList[index].getRarity()==4){
            fourStarPC[index] = playerCharacterList[index];
            index++;
        }
        for (int i = 0; i<playerCharacterList.length; i++){
            fiveStarPC[i] = playerCharacterList[index];
            index++;
        }

        while(playerCharacterList[index].getRarity()==4){
            fourStarPC[index] = playerCharacterList[index];
            index++;
        }
        for (int i = 0; i<playerCharacterList.length; i++){
            fiveStarPC[i] = playerCharacterList[index];
            index++;
        }
        
        while(weapons[index].getRarity()==4){
            fourStarWeapon[index] = weapons[index];
            index++;
        }
        for (int i = 0; i<weapons.length; i++){
            fiveStarWeapon[i] = weapons[index];
            index++;
        }
    }

    public Weapon[] pullWeapon(int timesToPull){
        Random random = new Random();
        Weapon[] pulledWeapon = new Weapon[timesToPull];
        for (int i = 0; i < timesToPull; i++){
            if(random.nextDouble() <= rates4Star){
                pulledWeapon[i] = this.fourStarWeapon[random.nextInt(fourStarWeapon.length)];
            }
            else{
                pulledWeapon[i] = this.fiveStarWeapon[random.nextInt(fiveStarWeapon.length)];
            }
        }
        return pulledWeapon;
    }

    public PlayerCharacter[] pullCharacter(int timesToPull){
        Random random = new Random();
        PlayerCharacter[] pulledPC = new PlayerCharacter[timesToPull];
        for (int i = 0; i < timesToPull; i++){
            if(random.nextDouble() <= rates4Star){
                pulledPC[i] = this.fourStarPC[random.nextInt(fourStarPC.length)];
            }
            else{
                pulledPC[i] = this.fiveStarPC[random.nextInt(fiveStarPC.length)];
            }
        }
        return pulledPC;
    }
}