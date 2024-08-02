import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<List<Territory>> territories;

    public Map(){
        this.territories = new ArrayList<>();
        initTerritories();
    }
    public void initTerritories(){
        String continent = null;
        for(int i = 1; i <= 4; i++){
            List<Territory> tempList = new ArrayList<>();
            for(int j = 1; j <= 6; j++){
                if(i == 1){
                    continent = "Red";
                }
                if(i == 2){
                    continent = "Yellow";
                }
                if(i == 3){
                    continent = "Blue";
                }
                if(i == 4){
                    continent = "Green";
                }
                Territory territory = new Territory(continent + " territory " + j  , continent);
                tempList.add(territory);
            }
            territories.add(tempList);
        }
    }
    public List<List<Territory>> getTerritories(){
        return territories;
    }
}
