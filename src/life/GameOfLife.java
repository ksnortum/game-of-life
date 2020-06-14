package life;

import life.controller.UniverseController;
import life.model.Data;
import life.model.GameOfLifeState;
import life.model.Lock;
import life.view.GameOfLifePanel;
import life.view.UniverseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class GameOfLife extends JFrame {
    private final Lock lock = new Lock();

    public GameOfLife() {
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Data data = buildGui();
        data.setLock(lock);
        pack();
        setVisible(true);

        // Start evolving
        UniverseView universeView = new UniverseView(data);
        UniverseController universeController = new UniverseController(universeView);
        universeController.evolve();
    }

    private Data buildGui() {
        Data data = new Data();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JToggleButton pauseButton = new JToggleButton("Pause");
        pauseButton.setName("PlayToggleButton");
        pauseButton.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                data.setState(GameOfLifeState.PAUSE);
                pauseButton.setName("Run");
            } else {
                data.setState(GameOfLifeState.RESUME);
                pauseButton.setName("Pause");
                synchronized (lock) {
                    lock.notify();
                }
            }
        });
        buttonPanel.add(pauseButton);
        // TODO Reset button
        // TODO add to buttonPanel
        add(buttonPanel);

        // Text labels
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
        add(textDisplay);

        // Graphics
        JPanel graphicDisplay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        GameOfLifePanel gameOfLifePanel = new GameOfLifePanel();
        data.setGameOfLifePanel(gameOfLifePanel);
        graphicDisplay.add(gameOfLifePanel);
        add(graphicDisplay);

        return data;
    }

}