import java.util.ArrayList;
import java.util.List;

public class Game {
    private Map map;
    private List<Player> players;

    public Game(Map map){
        players = new ArrayList<>();
        this.map = map;
        initPlayers();
    }

    private void initPlayers(){
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        players.add(player1);
        players.add(player2);
    }

    public void start(){
        for (List<Territory> continentList : map.getTerritories()) {
            if (!continentList.isEmpty()) {
                String continent = continentList.get(0).getContinent();
                System.out.println("Continent: " + continent);
                for (Territory territory : continentList) {
                    System.out.println(territory.getName() + " with " + territory.getArmyCount() + " armies.");
                }
            }
        }
    }

    public int calculateReinforcements(Player player) {
        int baseReinforcements = 3;
        int reinforcements = (int) Math.floor(player.getTerritories().size() / 3);
        if (player.getCards().size() >= 3) {
            reinforcements += 5;
            player.deleteThreeCards();
        }
        return baseReinforcements + reinforcements;
    }
}
