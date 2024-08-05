import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class UI extends JFrame {
    //private JFrame frame;
    private JPanel contentPane;
    private JPanel redContinent;
    private JPanel yellowContinent;
    private JPanel blueContinent;
    private JPanel greenContinent;

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


    private Game game;
    private Map map;

    public UI(Game game, Map map){
        this.setTitle("Risiko");
        this.game = game;
        this.map = map;
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setVisible(true);
        connectButtonsToTerritories();
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

        if (territory != null) {
            button.setText(territory.getName() + " - " + territory.getArmyCount());
        } else {
            button.setEnabled(false);
            button.setToolTipText("Territory not found: " + territoryName);
        }
    }
}
