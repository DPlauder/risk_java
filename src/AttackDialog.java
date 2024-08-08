import javax.swing.*;

public class AttackDialog extends JDialog {
    private Territory attackTerritory;
    private Territory defendTerritory;
    private int attackArmy;
    private JPanel contentPane;
    private JLabel attackTerritoryName;
    private JLabel attackTerritoryArmy;
    private JLabel defendTerritoryName;
    private JLabel defendTerritoryArmy;
    private JLabel attackArmyUi;
    private JButton addArmyBtn;
    private JButton minusArmyBtn;
    private JButton attackBtn;
    private JButton cancelBtn;

    public AttackDialog(Territory attackTerritory, Territory defendTerritory){
        this.attackTerritory = attackTerritory;
        this.defendTerritory = defendTerritory;

        this.attackTerritoryName.setText(attackTerritory.getName());
        this.attackTerritoryArmy.setText(String.valueOf(attackTerritory.getArmyCount()));
        this.defendTerritoryName.setText(defendTerritory.getName());
        this.defendTerritoryArmy.setText(String.valueOf(defendTerritory.getArmyCount()));
        this.attackArmyUi.setText(String.valueOf(0));
    }
}
