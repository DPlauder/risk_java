import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackDialog extends JDialog {
    private Game game;
    private Territory attackTerritory;
    private Territory defendTerritory;
    private int attackArmy;
    private int attackTerritoryArmyCount;
    private int defendTerritoryArmyCount;
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

    private boolean isConfirmed;

    public AttackDialog(Game game){

        setContentPane(contentPane);
        setModal(true);

        this.game = game;
        this.attackTerritory = game.getAttackTerritory();
        this.defendTerritory = game.getDefendTerritory();

        this.attackTerritoryArmyCount = attackTerritory.getArmyCount() - 1;
        this.defendTerritoryArmyCount = defendTerritory.getArmyCount();

        this.attackTerritoryName.setText(attackTerritory.getName());
        this.attackTerritoryArmy.setText(String.valueOf(attackTerritory.getArmyCount() - 1));
        this.defendTerritoryName.setText(defendTerritory.getName());
        this.defendTerritoryArmy.setText(String.valueOf(defendTerritory.getArmyCount()));

        this.isConfirmed = false;
        this.attackArmy = 1;
        this.attackArmyUi.setText(String.valueOf(attackArmy));

        addArmyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attackArmy < attackTerritory.getArmyCount() - 1) {
                    attackArmy++;
                    attackArmyUi.setText(String.valueOf(attackArmy));
                    attackTerritoryArmyCount--;
                    attackTerritoryArmy.setText(String.valueOf(attackTerritoryArmyCount));
                }
            }
        });

        minusArmyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attackArmy > 1) {
                    attackArmy--;
                    attackArmyUi.setText(String.valueOf(attackArmy));
                    attackTerritoryArmyCount++;
                    attackTerritoryArmy.setText(String.valueOf(attackTerritoryArmyCount));
                }
            }
        });

        attackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isConfirmed){
                    isConfirmed = true;
                    addArmyBtn.setVisible(false);
                    minusArmyBtn.setVisible(false);

                }
                else{
                    game.fight(attackArmy);
                    attackArmy = game.getAttackArmy();
                    attackArmyUi.setText(String.valueOf(attackArmy));
                    defendTerritoryArmy.setText(String.valueOf(game.getDefendArmy()));
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }


}
