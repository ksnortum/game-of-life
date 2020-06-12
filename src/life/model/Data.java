package life.model;

import life.view.GameOfLifePanel;

import javax.swing.*;

public class Data {
    private GameOfLifePanel gameOfLifePanel = null;
    private JLabel generationNumberLabel;
    private JLabel aliveNumberLabel;

    public GameOfLifePanel getGameOfLifePanel() {
        return gameOfLifePanel;
    }

    public void setGameOfLifePanel(GameOfLifePanel gameOfLifePanel) {
        this.gameOfLifePanel = gameOfLifePanel;
    }

    public JLabel getGenerationNumberLabel() {
        return generationNumberLabel;
    }

    public void setGenerationNumberLabel(JLabel generationNumberLabel) {
        this.generationNumberLabel = generationNumberLabel;
    }

    public JLabel getAliveNumberLabel() {
        return aliveNumberLabel;
    }

    public void setAliveNumberLabel(JLabel aliveNumberLabel) {
        this.aliveNumberLabel = aliveNumberLabel;
    }
}
