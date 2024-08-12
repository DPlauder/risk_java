package Model;

import java.util.ArrayList;
import java.util.List;

public class Continent {
    final private String name;

    private List<Territory> territories;

    public Continent(String name){
        this.name = name;
        territories = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public List<Territory> getTerritories(){
        return  territories;
    }
    public void addTerritories(List<Territory> territoriesList){
        territories = territoriesList;
    }

}
