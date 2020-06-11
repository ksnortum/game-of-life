package life;

import life.controller.UniverseController;
import life.model.CellStatus;
import life.model.Data;
import life.model.GlobalData;
import life.view.GameOfLifeCell;
import life.view.GameOfLifeGrid;
import life.view.UniverseView;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    public GameOfLife() {
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Data data = buildGui();
        pack();
        setVisible(true);

        // Start evolving
        UniverseView universeView = new UniverseView(data);
        UniverseController universeController = new UniverseController(universeView);
        universeController.evolve();
    }

    private Data buildGui() {
        Data data = new Data();

        JPanel generationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel generationLabel = new JLabel("Generation");
        generationsPanel.add(generationLabel);
        JLabel generationNumberLabel = new JLabel("#0");
        generationNumberLabel.setName("GenerationLabel");
        generationsPanel.add(generationNumberLabel);
        data.setGenerationNumberLabel(generationNumberLabel);

        JPanel alivePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel aliveLabel = new JLabel("Alive:");
        alivePanel.add(aliveLabel);
        JLabel aliveNumberLabel = new JLabel("0");
        aliveNumberLabel.setName("AliveLabel");
        alivePanel.add(aliveNumberLabel);
        data.setAliveNumberLabel(aliveNumberLabel);

        JPanel textDisplay = new JPanel();
        textDisplay.setLayout(new BoxLayout(textDisplay, BoxLayout.Y_AXIS));
        textDisplay.add(generationsPanel);
        textDisplay.add(alivePanel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(textDisplay);

        JPanel graphicDisplay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        GameOfLifeGrid gridPanel = new GameOfLifeGrid();
        int side = GlobalData.SIDE;
        GameOfLifeCell[][] cells = new GameOfLifeCell[side][side];

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                GameOfLifeCell cell = new GameOfLifeCell(column * side, row * side, CellStatus.FILL);
                cell.setVisible(false);
                gridPanel.add(cell);
                cells[row][column] = cell;
            }
        }

        data.setCells(cells);
        graphicDisplay.add(gridPanel);
        add(graphicDisplay);

        return data;
    }

}