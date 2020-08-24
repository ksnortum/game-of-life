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
        data.getPauseButton().addItemListener(universeController);
        data.getResetButton().addActionListener(universeController);
        universeController.evolve();
    }

    private Data buildGui() {
        Data data = new Data();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JToggleButton pauseButton = new JToggleButton("Pause");
        pauseButton.setName("PlayToggleButton");
        data.setPauseButton(pauseButton);
        buttonPanel.add(pauseButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        data.setResetButton(resetButton);
        buttonPanel.add(resetButton);
        buttonPanel.setPreferredSize(new Dimension(150, 30));

        // Text labels
        JPanel generationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel generationLabel = new JLabel("Generation");
        generationsPanel.add(generationLabel);
        JLabel generationNumberLabel = new JLabel("#0");
        generationNumberLabel.setName("GenerationLabel");
        generationsPanel.add(generationNumberLabel);
        generationsPanel.setPreferredSize(new Dimension(80, 10));
        data.setGenerationNumberLabel(generationNumberLabel);

        JPanel alivePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel aliveLabel = new JLabel("Alive:");
        alivePanel.add(aliveLabel);
        JLabel aliveNumberLabel = new JLabel("0");
        aliveNumberLabel.setName("AliveLabel");
        alivePanel.add(aliveNumberLabel);
        alivePanel.setPreferredSize(new Dimension(80, 20));
        data.setAliveNumberLabel(aliveNumberLabel);

        // Side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(buttonPanel);
        sidePanel.add(generationsPanel);
        sidePanel.add(alivePanel);
        sidePanel.setPreferredSize(new Dimension(160, 70));

        // North panel is used to "push" the side panel up
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(sidePanel);
        northPanel.add(Box.createRigidArea(new Dimension(160, 420)));
        add(northPanel);

        // Graphics panel
        JPanel graphicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        GameOfLifePanel gameOfLifePanel = new GameOfLifePanel();
        data.setGameOfLifePanel(gameOfLifePanel);
        graphicPanel.add(gameOfLifePanel);
        add(graphicPanel);

        return data;
    }

}