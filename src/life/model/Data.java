package life.model;

import life.view.GameOfLifeCell;

import javax.swing.*;

public class Data {
    private GameOfLifeCell[][] cells;
    private JLabel generationNumberLabel;
    private JLabel aliveNumberLabel;

    public GameOfLifeCell[][] getCells() {
        return cells;
    }

    public void setCells(GameOfLifeCell[][] cells) {
        this.cells = cells;
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
