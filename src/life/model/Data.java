package life.model;

import life.view.GameOfLifePanel;

import javax.swing.*;

public class Data {
    private GameOfLifePanel gameOfLifePanel = null;
    private JLabel generationNumberLabel;
    private JLabel aliveNumberLabel;
    private JToggleButton pauseButton;
    private JButton resetButton;

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

    public JToggleButton getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(JToggleButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void setResetButton(JButton resetButton) {
        this.resetButton = resetButton;
    }
}
