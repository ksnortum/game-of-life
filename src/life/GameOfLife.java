package life;

import life.controller.UniverseController;
import life.model.Data;
import life.view.GameOfLifePanel;
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
        GameOfLifePanel gameOfLifePanel = new GameOfLifePanel();
        data.setGameOfLifePanel(gameOfLifePanel);
        graphicDisplay.add(gameOfLifePanel);
        add(graphicDisplay);

        return data;
    }

}