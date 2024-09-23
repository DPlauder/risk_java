package Control;

import View.UI;
import Model.*;
import Config.Config;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Game {
    private final Map map;
    private List<Player> players;
    private UI ui;
    private int gamephase;
    private int currentPlayerIndex;

    private Territory attackTerritory;
    private Territory defendTerritory;
    private int attackArmy;
    private int defendArmy;
    private boolean attackSuccess;

    private Territory moveFromTerritory;
    private Territory moveToTerritory;

    public Game(Map map){
        this.map = map;

    }
    //TODO GAME INIT
    public void newGame(){
        initPlayers();
        distributeTerritories();
        ui.newGameUi();
        currentPlayerIndex = 0;
        start();
    }
    private void initPlayers(){
        Player player = null;
        players = new ArrayList<>();
        for(int i = 0; i < Config.PLAYER.length; i++){
            if(Config.PLAYER[i] != null && !Config.PLAYER[i].isEmpty())
                player = new Player(Config.PLAYER[i], Config.PLAYERCOLORS[i]);
            else{
                player = new Player("Player " + (i + 1), Config.PLAYERCOLORS[i]);
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
            territory.setArmyCount(3);
            player.getTerritories().add(territory);
            playerIndex = (playerIndex + 1) % players.size();
        }
    }
    public void setUi(UI ui){
        this.ui = ui;
    }
    public void start(){
        gamephase = 1;
        ui.updateUI();
    }
    //TODO GETTER SETTER

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }
    public void nextTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        gamephase = 1;
        attackSuccess = false;
        ui.updateUI();
    }

    public int getGamephase(){
        return gamephase;
    }
    public Territory getAttackTerritory(){
        return attackTerritory;
    }
    public Territory getDefendTerritory() {
        return defendTerritory;
    }

    public int getAttackArmy() {
        return attackArmy;
    }

    public int getDefendArmy() {
        return defendArmy;
    }

    public Territory getMoveToTerritory() {
        return moveToTerritory;
    }

    public Territory getMoveFromTerritory() {
        return moveFromTerritory;
    }

    public List<Player> getPlayers() {
        return players;
    }
    //TODO Reinforcementphase
    public void reinforcementPhase(Territory territory){
        //DEV
        if(territory == null){

        }
        //DEV
        int reinforcements = calculateReinforcements(getCurrentPlayer());
        placeArmy(territory, reinforcements);
        ui.showNextPhaseBtn();
        gamephase = 2;
        ui.updateUI();
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

    //TODO AttackPhase
    public void setAttackTerritory(Territory territory){
        attackTerritory = territory;
        for(Territory neighbor : territory.getNeighbours()) {
            System.out.println("Neighbor: " + neighbor.getName());
        }
        checkReadyToAttack();
    }
    //
    public void resetAttackTerritory(){
        attackTerritory = null;
        attackArmy = 0;
    }

    public void isAttackTerritoryNeighbour(Territory chosenDefendTerritory){
        if(attackTerritory != null){
            for(Territory neighbour : attackTerritory.getNeighbours()){
                if(neighbour == chosenDefendTerritory){
                    setDefendTerritory(chosenDefendTerritory);
                }
            }
        }
    }
    public List<Territory> getAttackableNeighbours(Territory territory) {
        if (territory == null) {
            return Collections.emptyList();
        }

        List<Territory> attackableNeighbours = new ArrayList<>();
        for (Territory neighbour : territory.getNeighbours()) {
            if (neighbour.getOwner() != getCurrentPlayer()) {
                attackableNeighbours.add(neighbour);
            }
        }
        return attackableNeighbours;
    }

    public void setDefendTerritory(Territory territory){
        defendTerritory = territory;
        checkReadyToAttack();
    }
    public void resetDefendTerritory(){
        defendTerritory = null;
        defendArmy = 0;
    }
    public void checkReadyToAttack(){
        if(defendTerritory != null && attackTerritory != null && attackTerritory.getArmyCount() > 1){
            ui.openAttackDialog(this);
        }
    }
    public void fight(int attackArmyCount){
        int startAttackArmy = attackArmyCount;
        attackArmy = attackArmyCount;
        Player attacker = getCurrentPlayer();
        defendArmy = defendTerritory.getArmyCount();
        Player defender = defendTerritory.getOwner();

        int attackDice = Math.min(attackArmy, 3);
        int defendDice = Math.min(defendArmy, 2);

        List<Integer> attackRolls = new ArrayList<>();
        List<Integer> defendRolls = new ArrayList<>();

        for (int i = 0; i < attackDice; i++) {
            attackRolls.add(Dice.rollDice());
        }
        for (int i = 0; i < defendDice; i++) {
            defendRolls.add(Dice.rollDice());
        }
        Collections.sort(attackRolls, Collections.reverseOrder());
        Collections.sort(defendRolls, Collections.reverseOrder());



        int battles = Math.min(attackRolls.size(), defendRolls.size());
        int attackLosses = 0;
        int defendLosses = 0;

        for (int i = 0; i < battles; i++) {
            if (attackRolls.get(i) > defendRolls.get(i)) {
                defendLosses++;
            } else {
                attackLosses++;
            }
        }
        attackArmy -= attackLosses;
        defendArmy -= defendLosses;


        if(defendArmy == 0){
            System.out.println("defender lost");
            defendTerritory.setOwner(attacker);
            defendTerritory.setArmyCount(attackArmy);
            attackTerritory.setArmyCount(attackTerritory.getArmyCount() - startAttackArmy);
            attacker.addTerritory(defendTerritory);
            defender.deleteTerritory(defendTerritory);
            attackSuccess = true;
            endAttackPhase();
        }
        else if(attackArmy == 0){
            System.out.println("attacker lost");
            attackTerritory.setArmyCount(attackTerritory.getArmyCount() - startAttackArmy);
            defendTerritory.setArmyCount(defendArmy);
            endAttackPhase();
        }
        else {
            attackTerritory.setArmyCount(attackTerritory.getArmyCount() - startAttackArmy + attackArmy);
            defendTerritory.setArmyCount(defendArmy);
        }
    }
    public void endAttackPhase(){
        if (isEnd()) {
            System.out.println(getCurrentPlayer());
            gamephase = 0;
            ui.endGame(getCurrentPlayer());
        }
        else{
            resetAttackTerritory();
            resetDefendTerritory();
            ui.updateUI();
        }

        ui.closeAttackDialog();


    }
    //TODO Movementphase
    public void giveCard(){
        if(attackSuccess){
            getCurrentPlayer().addCard();
            attackSuccess = false;
        }
    }

    public void setMovePhase(){
        gamephase = 4;
        giveCard();
        ui.updateUI();
    }
    public void setMoveFromTerritory(Territory territory){
        moveFromTerritory = territory;
        System.out.println(getMoveFromTerritory());
    }
    public void resetMoveFromTerritory(){
        moveFromTerritory = null;
    }
    public void resetMoveToTerritory(){
        moveToTerritory = null;
    }
    public void setMoveToTerritory(Territory territory){
        moveToTerritory = territory;
    }
    public void isMoveTerritoryNeighbour(Territory chosenMoveToTerritory){
        for(Territory neighbour : moveFromTerritory.getNeighbours()){
            if(neighbour == chosenMoveToTerritory){
                setMoveToTerritory(chosenMoveToTerritory);
                ui.openMoveDialog();
            }
        }
    }
    public void moveArmy(int startTerritoryArmy, int endTerritoryArmy){
        moveFromTerritory.setArmyCount(startTerritoryArmy);
        moveToTerritory.setArmyCount(endTerritoryArmy);
        resetMoveFromTerritory();
        resetMoveToTerritory();
        ui.updateUI();

        nextTurn();
    }
    //TODO EndConditions
    public boolean isEnd(){
        return getCurrentPlayer().getTerritories().size() == map.getTerritories().size();
    }
}
