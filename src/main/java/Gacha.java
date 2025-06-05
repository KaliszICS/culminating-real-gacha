import java.util.Random;
public class Gacha{

    private double rates4Star;
    private double rates5Star;
    private PlayerCharacter[] fourStarPC;
    private PlayerCharacter[] fiveStarPC;
    private Weapon[] fourStarWeapon;
    private Weapon[] fiveStarWeapon;

    private int cost;
    private int repeatCompensation;

    public Gacha(PlayerCharacter[] playerCharacterList, Weapon[] weapons){
    
        rates4Star = 0.95;
        rates5Star = 1-rates4Star;

        fourStarPC = playerCharacterList[0];//0 is a temp, its meant to take part of the entire array
        fiveStarPC = playerCharacterList[0];
        fourStarWeapon = weapons[0];//0 is a temp, its meant to take part of the entire array
        fiveStarWeapon = weapons[0];

        cost = 160;
    }

    public Weapon[] pullWeapon(int timesToPull){
        Random random = new Random();
        Weapon[] pulledWeapon = new Weapon[timesToPull];
        for (int i = 0; i < timesToPull; i++){
            if(random.nextDouble() <= rates4Star){
                pulledWeapon[i] = this.fourStarWeapon;
            }
            else{
                pulledWeapon[i] = this.fiveStarWeapon;
            }
        }
        return pulledWeapon;
    }

        public PlayerCharacter[] pullCharacter(int timesToPull){
        Random random = new Random();
        PlayerCharacter[] pulledPC = new PlayerCharacter[timesToPull];
        for (int i = 0; i < timesToPull; i++){
            if(random.nextDouble() <= rates4Star){
                pulledPC[i] = this.fourStarPC;
            }
            else{
                pulledPC[i] = this.fiveStarPC;
            }
        }
        return pulledPC;
    }
}