package View;

import Control.Game;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveDialog extends JDialog {
    private JPanel contentPane;
    private JLabel fromTerritoryNameLbl;
    private JLabel fromTerritoryArmyLbl;
    private JLabel toTerritoryNameLbl;
    private JLabel toTerritoryArmyLbl;
    private JButton addArmyBtn;
    private JButton minusArmyBtn;
    private JLabel armyToMoveLbl;
    private JButton confirmBtn;
    private JButton cancelBtn;

    private Game game;
    private Territory territoryFrom;
    private Territory territoryTo;
    private int armyToMove;
    private int armyFromCount;
    private int armyToCount;


    public MoveDialog(Game game){
        setContentPane(contentPane);
        setModal(true);

        territoryFrom = game.getMoveFromTerritory();
        territoryTo = game.getMoveToTerritory();

        armyFromCount = territoryFrom.getArmyCount();
        armyToCount = territoryTo.getArmyCount();

        fromTerritoryNameLbl.setText(territoryFrom.getName());
        fromTerritoryArmyLbl.setText(String.valueOf(armyFromCount));

        toTerritoryNameLbl.setText(territoryTo.getName());
        toTerritoryArmyLbl.setText(String.valueOf(armyToCount));

        armyToMove = 0;
        armyToMoveLbl.setText(String.valueOf(armyToMove));

        addArmyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (armyFromCount > 1) {
                    armyToMove++;
                    armyToMoveLbl.setText(String.valueOf(armyToMove));
                    armyFromCount--;
                    fromTerritoryArmyLbl.setText(String.valueOf(armyFromCount));
                    armyToCount++;
                    toTerritoryArmyLbl.setText(String.valueOf(armyToCount));
                }
            }
        });
        minusArmyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (armyToCount > 1) {
                    armyToMove--;
                    armyToMoveLbl.setText(String.valueOf(armyToMove));
                    armyFromCount++;
                    fromTerritoryArmyLbl.setText(String.valueOf(armyFromCount));
                    armyToCount--;
                    toTerritoryArmyLbl.setText(String.valueOf(armyToCount));
                }
            }
        });
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.moveArmy(armyFromCount, armyToCount);
                dispose();
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
