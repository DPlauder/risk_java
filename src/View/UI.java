package View;

import Control.Game;
import Model.*;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;


public class UI extends JFrame {

    private JPanel contentPane;

    private JPanel playerOnePane;
    private JPanel playerTwoPane;
    private JPanel playerThreePane;
    private JPanel playerFourPane;
    private JPanel playerFivePane;
    private JPanel playerSixPane;

    private JPanel redContinent;
    private JPanel yellowContinent;
    private JPanel blueContinent;
    private JPanel greenContinent;

    private List<JButton> territoryButtons;

    private JButton redOne;
    private JButton redTwo;
    private JButton redThree;
    private JButton redFour;
    private JButton redFive;
    private JButton redSix;

    private JButton yellowOne;
    private JButton yellowTwo;
    private JButton yellowThree;
    private JButton yellowFour;
    private JButton yellowFive;
    private JButton yellowSix;

    private JButton blueOne;
    private JButton blueTwo;
    private JButton blueThree;
    private JButton blueFour;
    private JButton blueFive;
    private JButton blueSix;

    private JButton greenOne;
    private JButton greenTwo;
    private JButton greenThree;
    private JButton greenFour;
    private JButton greenFive;
    private JButton greenSix;
    private JButton nextPhaseBtn;

    private JLabel gamePhaseLbl;

    private Game game;
    private Map map;
    private AttackDialog attackDialog;
    private MoveDialog moveDialog;

    public UI(Game game, Map map){
        this.setTitle("Risiko");
        this.game = game;
        this.map = map;
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.gamePhaseLbl.setText(String.valueOf( game.getGamephase())); ;


        initializeButtonsList();
        connectButtonsToTerritories();
        nextPhaseBtn.setVisible(false);



        nextPhaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getGamephase() == 0) {
                    game.newGame();
                }
                if(game.getGamephase() == 2){
                    game.endAttackPhase();
                    game.setMovePhase();

                }
                else if(game.getGamephase() == 4){
                    game.nextTurn();
                }
            }
        });

        this.pack();
        setLocationRelativeTo(null);
    }
    public void newGameUi(){
        initiatePlayersZone();
        updateUI();
    }

    private void initiatePlayersZone() {
        List<JPanel> playerPanelsList = new ArrayList<>();
        playerPanelsList.add(playerOnePane);
        playerPanelsList.add(playerTwoPane);
        playerPanelsList.add(playerThreePane);
        playerPanelsList.add(playerFourPane);
        playerPanelsList.add(playerFivePane);
        playerPanelsList.add(playerSixPane);

        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            JPanel tempPanel = playerPanelsList.get(i);

            tempPanel.setLayout(new GridLayout(1, 1));

            JLabel playerNameLbl = new JLabel();
            playerNameLbl.setText(player.getName());

            Color playerColor = Utils.stringToColor(player.getColor());
            tempPanel.setBorder(BorderFactory.createLineBorder(playerColor, 3));
            tempPanel.add(playerNameLbl);

            JWindow infoWindow = new JWindow();
            infoWindow.setLayout(new BorderLayout());

            // Label to display the number of cards
            JLabel playerInfoLbl = new JLabel();
            infoWindow.add(playerInfoLbl, BorderLayout.CENTER);
            infoWindow.pack();

            // Add mouse listener to show/hide the info window
            tempPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Update the label text with the current number of cards
                    playerInfoLbl.setText("Cards: " + player.getCards().size());

                    // Reposition and show the info window
                    Point locationOnScreen = tempPanel.getLocationOnScreen();
                    infoWindow.setLocation(locationOnScreen.x + tempPanel.getWidth(), locationOnScreen.y);
                    infoWindow.pack(); // Repack the window to adjust the size if needed
                    infoWindow.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    infoWindow.setVisible(false);
                }
            });
        }
    }

    private void initializeButtonsList() {
        territoryButtons = new ArrayList<>();

        territoryButtons.add(redOne);
        territoryButtons.add(redTwo);
        territoryButtons.add(redThree);
        territoryButtons.add(redFour);
        territoryButtons.add(redFive);
        territoryButtons.add(redSix);

        territoryButtons.add(yellowOne);
        territoryButtons.add(yellowTwo);
        territoryButtons.add(yellowThree);
        territoryButtons.add(yellowFour);
        territoryButtons.add(yellowFive);
        territoryButtons.add(yellowSix);

        territoryButtons.add(blueOne);
        territoryButtons.add(blueTwo);
        territoryButtons.add(blueThree);
        territoryButtons.add(blueFour);
        territoryButtons.add(blueFive);
        territoryButtons.add(blueSix);

        territoryButtons.add(greenOne);
        territoryButtons.add(greenTwo);
        territoryButtons.add(greenThree);
        territoryButtons.add(greenFour);
        territoryButtons.add(greenFive);
        territoryButtons.add(greenSix);
    }
    private void connectButtonsToTerritories() {
        connectButtonToTerritory(redOne, "Red territory 1");
        connectButtonToTerritory(redTwo, "Red territory 2");
        connectButtonToTerritory(redThree, "Red territory 3");
        connectButtonToTerritory(redFour, "Red territory 4");
        connectButtonToTerritory(redFive, "Red territory 5");
        connectButtonToTerritory(redSix, "Red territory 6");

        connectButtonToTerritory(yellowOne, "Yellow territory 1");
        connectButtonToTerritory(yellowTwo, "Yellow territory 2");
        connectButtonToTerritory(yellowThree, "Yellow territory 3");
        connectButtonToTerritory(yellowFour, "Yellow territory 4");
        connectButtonToTerritory(yellowFive, "Yellow territory 5");
        connectButtonToTerritory(yellowSix, "Yellow territory 6");

        connectButtonToTerritory(blueOne, "Blue territory 1");
        connectButtonToTerritory(blueTwo, "Blue territory 2");
        connectButtonToTerritory(blueThree, "Blue territory 3");
        connectButtonToTerritory(blueFour, "Blue territory 4");
        connectButtonToTerritory(blueFive, "Blue territory 5");
        connectButtonToTerritory(blueSix, "Blue territory 6");

        connectButtonToTerritory(greenOne, "Green territory 1");
        connectButtonToTerritory(greenTwo, "Green territory 2");
        connectButtonToTerritory(greenThree, "Green territory 3");
        connectButtonToTerritory(greenFour, "Green territory 4");
        connectButtonToTerritory(greenFive, "Green territory 5");
        connectButtonToTerritory(greenSix, "Green territory 6");
    }
    private void connectButtonToTerritory(JButton button, String territoryName) {
        Territory territory = map.getTerritory(territoryName);
        if(territory != null) {
            button.setText(String.valueOf(territory.getArmyCount()));
            button.setName(territory.getName());
            //Color territoryColor = Utils.stringToColor(territory.getOwner().getColor());
            //button.setForeground(territoryColor);


            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(game.getGamephase() == 1 && territory.getOwner() == game.getCurrentPlayer()){
                        game.reinforcementPhase(territory);
                        button.setText(String.valueOf(territory.getArmyCount()));
                    }
                    else if(game.getGamephase() == 2){
                        if (territory == game.getAttackTerritory()) {
                            game.resetAttackTerritory();
                            resetHighlights();
                        } else if (territory.getOwner() == game.getCurrentPlayer()) {
                            game.resetDefendTerritory();
                            game.setAttackTerritory(territory);
                            highlightAttackableTerritories(territory);
                        } else {
                            game.isAttackTerritoryNeighbour(territory);
                        }
                        updateButtonVisuals();
                    }
                    else if(game.getGamephase() == 4 && territory.getOwner() == game.getCurrentPlayer()){
                        if(territory == game.getMoveFromTerritory()){
                            game.resetMoveFromTerritory();
                            resetHighlights();
                        }
                        else if(game.getMoveFromTerritory() == null){
                            game.setMoveFromTerritory(territory);
                            updateButtonVisuals();
                        }
                        else if (game.getMoveFromTerritory() != null && territory.getOwner() == game.getCurrentPlayer()) {
                            game.isMoveTerritoryNeighbour(territory);
                        }
                        updateButtonVisuals();
                    }
                }

            });
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (game.getGamephase() == 2 && territory.getOwner() == game.getCurrentPlayer()) {
                        highlightAttackableTerritories(territory);
                    } else if (game.getGamephase() == 4 && territory.getOwner() == game.getCurrentPlayer()) {
                        highlightMoveableTerritories(territory);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    resetHighlights();

                }
            });
        } else {
            button.setEnabled(false);
            button.setToolTipText("Territory not found: " + territoryName);
        }

    }
    public void openAttackDialog(Game game){
        attackDialog = new AttackDialog(game, this);
        attackDialog.setVisible(true);
    }
    public void updateUI() {
        for (JButton button : territoryButtons) {
            String territoryName = button.getName();
            Territory territory = map.getTerritory(territoryName);
            if (territory != null) {
                button.setText(String.valueOf(territory.getArmyCount()));
                Color territoryColor = Utils.stringToColor(territory.getOwner().getColor());
                button.setForeground(territoryColor);
            }

        }

        gamePhaseLbl.setText(String.valueOf(game.getGamephase()) + " " + game.getCurrentPlayer().getName());
        if(game.getGamephase() == 1){
            nextPhaseBtn.setVisible(false);
        }
        updateButtonVisuals();
        resetHighlights();
    }
    public void closeAttackDialog() {
        if (attackDialog != null) {
            resetHighlights();
            attackDialog.dispose();
        }
    }
    public void showNextPhaseBtn(){
        nextPhaseBtn.setVisible(true);
        game.setMovePhase();

    }
    public void openMoveDialog(){
        moveDialog = new MoveDialog(game);
        moveDialog.setVisible(true);
    }
    private void highlightAttackableTerritories(Territory chosenTerritory) {

        for (JButton button : territoryButtons) {
            button.setBackground(null);
        }

        List<Territory> attackableTerritories = game.getAttackableNeighbours(chosenTerritory);
        for (Territory territory : attackableTerritories) {
            for (JButton button : territoryButtons) {
                if (button.getName().equals(territory.getName())) {
                    button.setBackground(Color.RED);
                }
            }
        }
    }
    public void resetHighlights() {
        for (JButton button : territoryButtons) {
            button.setBackground(null);
        }
    }

    public void updateButtonVisuals() {
        for (JButton button : territoryButtons) {
            if (game.getAttackTerritory() != null && button.getName().equals(game.getAttackTerritory().getName())) {
                button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            }else if(game.getMoveFromTerritory() != null && button.getName().equals(game.getMoveFromTerritory().getName())){
                button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            }
            else {
                button.setBorder(null);
            }
        }
    }
    private void highlightMoveableTerritories(Territory chosenTerritory) {
        resetHighlights();
        List<Territory> moveableTerritories = chosenTerritory.getNeighbours();
        for (Territory territory : moveableTerritories) {
            for (JButton button : territoryButtons) {
                if (button.getName().equals(territory.getName()) && territory.getOwner() == game.getCurrentPlayer()) {
                    button.setBackground(Color.CYAN);
                }
            }
        }
    }
    public void endGame(Player winner){
        gamePhaseLbl.setText(winner.getName() + " won");
        nextPhaseBtn.setText("New Game");
        updateButtonVisuals();
    }

}
