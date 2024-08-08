import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Territory> territories;
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
                territories.add(territory);
                tempList.add(territory);
            }
            continent.addTerritories(tempList);
        }
    }

    public void setUpNeighbors(){
        for (Territory territory : territories) {
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

    public List<Territory> getTerritories(){
        return territories;
    }

    public Territory getTerritory(String territoryName) {
        for (Territory territory : territories) {
            if (territory.getName().equals(territoryName)) {
                return territory;
            }
        }
        return null;
    }
}
