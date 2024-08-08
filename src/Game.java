import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Game {
    private Map map;
    private List<Player> players;
    private UI ui;
    private int gamephase;
    private int currentPlayerIndex;

    private Territory attackTerritory;
    private Territory defendTerritory;

    public Game(Map map){
        players = new ArrayList<>();
        this.map = map;
        initPlayers();
        distributeTerritories();
        currentPlayerIndex = 0;

    }
    private void initPlayers(){
        Player player = null;
        for(int i = 0; i < Config.PLAYER.length; i++){
            if(Config.PLAYER[i] != null && !Config.PLAYER[i].isEmpty())
                player = new Player(Config.PLAYER[i]);
            else{
                player = new Player("Player " + (i + 1));
            }
            players.add(player);
        }
    }
    private void distributeTerritories(){
        List<Territory> allTerritories = map.getTerritories();
        Collections.shuffle(allTerritories);

        int playerIndex = 0;
        for (Territory territory : allTerritories) {
            Player player = players.get(playerIndex);
            territory.setOwner(player);
            player.getTerritories().add(territory);
            playerIndex = (playerIndex + 1) % players.size();
        }
    }

    public void start(UI ui){
        this.ui = ui;
        gamephase = 1;
    }
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }
    public void nextTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public int getGamephase(){
        return gamephase;
    }

    public void moveArmy(Territory territoryStart, Territory territoryEnd, int armySize){
        int armyStart = territoryStart.getArmyCount() - armySize;
        int armyEnd = territoryEnd.getArmyCount() + armySize;
        territoryStart.setArmyCount(armyStart);
        territoryEnd.setArmyCount(armyEnd);
    }

    public void movePhase(){

    }
    public void reinforcementPhase(Territory territory){
        int reinforcements = calculateReinforcements(getCurrentPlayer());
        placeArmy(territory, reinforcements);
        gamephase = 2;
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
    public void placeArmy(Territory territory, int reinforcements){
        int newArmy = territory.getArmyCount() + reinforcements;
        territory.setArmyCount(newArmy);
    }

    public void setAttackTerritory(Territory territory){
        attackTerritory = territory;
        for(Territory neighbor : territory.getNeighbours()) {
            System.out.println("Neighbor: " + neighbor.getName());
        }
        checkReadyToAttack();
    }
    public void setDefendTerritory(Territory territory){
        defendTerritory = territory;
        checkReadyToAttack();
    }
    public void checkReadyToAttack(){
        //ui.openAttackDialog(attackTerritory, defendTerritory);
        if(defendTerritory != null && attackTerritory != null){
            ui.openAttackDialog(attackTerritory, defendTerritory);
            //fight();
        }
    }
    public void fight(){
        int attackArmy = attackTerritory.getArmyCount();
        Player attacker = getCurrentPlayer();
        int defendArmy = defendTerritory.getArmyCount();
        Player defender = defendTerritory.getOwner();
    }
}
