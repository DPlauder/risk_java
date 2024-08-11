import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;


public class UI extends JFrame {
    //private JFrame frame;
    private JPanel contentPane;
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



    private Game game;
    private Map map;
    private AttackDialog attackDialog;

    public UI(Game game, Map map){
        this.setTitle("Risiko");
        this.game = game;
        this.map = map;
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setVisible(true);
        setLocationRelativeTo(null);

        initializeButtonsList();
        connectButtonsToTerritories();
        nextPhaseBtn.setVisible(false);

        nextPhaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.getGamephase() == 3){
                    game.setMovePhase();
                }
                else if(game.getGamephase() == 4){

                };

            }
        });
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
            Color territoryColor = Utils.stringToColor(territory.getOwner().getColor());
            button.setForeground(territoryColor);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(game.getGamephase() == 1 && territory.getOwner() == game.getCurrentPlayer()){
                        game.reinforcementPhase(territory);
                        button.setText(String.valueOf(territory.getArmyCount()));
                    }
                    if(game.getGamephase() == 2){
                        if(territory.getOwner() == game.getCurrentPlayer()) {
                            game.resetDefendTerritory();
                            game.setAttackTerritory(territory);
                        }
                        else{
                            game.isAttackTerritoryNeighbour(territory);
                        }
                    }
                    if(game.getGamephase() == 4 && territory.getOwner() == game.getCurrentPlayer()){
                        if(game.getMoveFromTerritory() == null){
                            System.out.println("hello move");
                        } else if (game.getMoveFromTerritory() != null) {
                            
                        }
                    }
                }
            });
        } else {
            button.setEnabled(false);
            button.setToolTipText("Territory not found: " + territoryName);
        }

    }
    public void openAttackDialog(Game game){
        attackDialog = new AttackDialog(game);
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
    }
    public void closeAttackDialog() {
        if (attackDialog != null) {
            attackDialog.dispose();  // Close the dialog
            attackDialog = null;     // Clear the reference
        }
    }
    public void showNextPhaseBtn(){
        System.out.println("hello move BTN");
        nextPhaseBtn.setVisible(true);
        game.setMovePhase();
    }
}
