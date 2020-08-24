package life.controller;

import life.model.Data;
import life.model.GameOfLifeState;
import life.model.GlobalData;
import life.model.Universe;
import life.view.UniverseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UniverseController implements ItemListener, ActionListener {
    private final int side = GlobalData.SIDE;
    private final UniverseView universeView;
    private final JToggleButton pauseButton;
    private volatile Thread counterThread;
    private final Object lock = new Object();

    private int generation = 1;
    private GameOfLifeState state = GameOfLifeState.RESUME;

    public UniverseController(UniverseView universeView) {
        this.universeView = universeView;
        Data data = universeView.getData();
        pauseButton = data.getPauseButton();
    }

    public void evolve() {
        Universe universe = new Universe();
        universe.createUniverse();
        universeView.print(universe);

        counterThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(GlobalData.TIME_BETWEEN_GENERATIONS);

                    if (state == GameOfLifeState.PAUSE) {
                        synchronized (lock) {
                            while (state == GameOfLifeState.PAUSE) {
                                lock.wait();
                            }
                        }
                    } else if (state == GameOfLifeState.RESTART) {
                        state = GameOfLifeState.RESUME;
                        universe.createUniverse();
                        universeView.print(universe);
                        generation = 1;
                        Thread.sleep(GlobalData.TIME_BETWEEN_GENERATIONS);
                    }
                } catch (InterruptedException ignored) {
                }

                evolveOneGeneration(universe);
                universe.setGenerationNumber(generation);
                universeView.print(universe);
                generation++;
            }
        });
        counterThread.setDaemon(true);
        counterThread.start();
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
            state = GameOfLifeState.PAUSE;
            pauseButton.setText("Run");
        } else {
            state = GameOfLifeState.RESUME;
            pauseButton.setText("Pause");
            synchronized (lock) {
                lock.notify();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("Reset".equals(actionEvent.getActionCommand())) {
            state = GameOfLifeState.RESTART;
        }
    }

    private void evolveOneGeneration(Universe universe) {
        boolean[][] nextUniverseMatrix = new boolean[side][side];

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                nextUniverseMatrix[row][column] = liveOrDie(row, column, universe.getUniverseMatrix());
            }
        }

        universe.setUniverseMatrix(nextUniverseMatrix);
    }

    private boolean liveOrDie(int row, int column, boolean[][] universe) {
        int northRow = dimensionNorthOrWest(row);
        int southRow = dimensionSouthOrEast(row);
        int westColumn = dimensionNorthOrWest(column);
        int eastColumn = dimensionSouthOrEast(column);
        boolean wasAlive = universe[row][column];
        int totalAlive = 0;

        // north
        totalAlive += universe[northRow][column] ? 1 : 0;

        // south
        totalAlive += universe[southRow][column] ? 1 : 0;

        // west
        totalAlive += universe[row][westColumn] ? 1 : 0;

        // east
        totalAlive += universe[row][eastColumn] ? 1 : 0;

        // northeast
        totalAlive += universe[northRow][eastColumn] ? 1 : 0;

        // northwest
        totalAlive += universe[northRow][westColumn] ? 1 : 0;

        // southeast
        totalAlive += universe[southRow][eastColumn] ? 1 : 0;

        // southwest
        totalAlive += universe[southRow][westColumn] ? 1 : 0;

        return (wasAlive && (totalAlive == 2 || totalAlive == 3))
                || (!wasAlive && totalAlive == 3);
    }

    private int dimensionNorthOrWest(int dimension) {
        return dimension == 0 ? side - 1 : dimension - 1;
    }

    private int dimensionSouthOrEast(int dimension) {
        return dimension == side - 1 ? 0 : dimension + 1;
    }

}
