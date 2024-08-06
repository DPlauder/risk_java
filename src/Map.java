import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<List<Territory>> territories;
    private List<Continent> continents;

    public Map(){
        this.territories = new ArrayList<>();
        this.continents = new ArrayList<>();
        initContinents();
        initTerritories();
        MapTypes.initializeMap();
        setUpNeighbors();
    }
    public void initContinents(){
        for (String usedContinent : Config.CONTINENTS) {
            continents.add(new Continent(usedContinent));
        }
    }
    public void initTerritories(){
        for (Continent continent : continents) {
            List<Territory> tempList = new ArrayList<>();
            for (int j = 1; j <= 6; j++) {
                Territory territory = new Territory(continent.getName() + " territory " + j, continent.getName());
                tempList.add(territory);
            }
            territories.add(tempList);
            continent.addTerritories(tempList);
        }
    }
    public void setUpNeighbors(){
        for (List<Territory> continentTerritories : territories) {
            for (Territory territory : continentTerritories) {
                List<String> neighborNames = MapTypes.getNeighbors(territory.getName());
                List<Territory> neighbors = new ArrayList<>();
                for (String neighborName : neighborNames) {
                    Territory neighbor = getTerritory(neighborName);
                    if (neighbor != null) {
                        neighbors.add(neighbor);
                    }
                }
                territory.setNeighbours(neighbors);
            }
        }
    }
    public List<List<Territory>> getTerritories(){
        return territories;
    }

    public Territory getTerritory(String territoryName) {
        for (List<Territory> continentTerritories : territories) {
            for (Territory territory : continentTerritories) {
                if (territory.getName().equals(territoryName)) {
                    return territory;
                }
            }
        }
        return null;
    }
}
