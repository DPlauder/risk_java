import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Territory> territories;
    private List<Card> cards;

    public Player(String name){
        this.name = name;
        this.territories = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public List<Territory> getTerritories(){
        return territories;
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
}
