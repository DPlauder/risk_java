import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Territory> territories;
    private List<Card> cards;
    private String color;

    public Player(String name, String color){
        this.name = name;
        this.territories = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.color = color;
    }

    public String getName(){
        return name;
    }
    public List<Territory> getTerritories(){
        return territories;
    }
    public void addTerritory(Territory territory){
        territories.add(territory);
    }
    public void deleteTerritory(Territory territory){
        territories.remove(territory);
    }
    public List<Card> getCards(){
        return cards;
    }
    public void addCard(){
        this.cards.add(new Card());
    }
    public void deleteThreeCards(){
        this.cards.subList(0,3).clear();
    }
    public String getColor(){
        return color;
    }
}
