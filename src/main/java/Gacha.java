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
    
        this.rates4Star = 0.95;
        this.rates5Star = 1-this.rates4Star;
        int index4StarList = 0;
        int index5StarList = 0;
        
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