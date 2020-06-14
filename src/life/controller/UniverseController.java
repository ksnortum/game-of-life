package life.controller;

import life.model.*;
import life.view.UniverseView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UniverseController {
    private final int side = GlobalData.SIDE;
    private final UniverseView universeView;
    private final Data data;
    private final Lock lock;

    private int generation = 1;

    public UniverseController(UniverseView universeView) {
        this.universeView = universeView;
        data = universeView.getData();
        lock = data.getLock();
    }

    public void evolve() {
        Universe universe = new Universe();
        universe.createUniverse();
        universeView.print(universe);

        Timer timer = new Timer(GlobalData.TIME_BETWEEN_GENERATIONS, event -> {
            if (data.getState() == GameOfLifeState.STOP) { // TODO implement
                Timer timer1 = (Timer) event.getSource();
                timer1.stop();
            } else if (data.getState() == GameOfLifeState.PAUSE) {
                System.out.println("In timer pause"); // debug
                synchronized (lock) {
                    while (data.getState() == GameOfLifeState.PAUSE) {
                        try {
                            System.out.println("  before wait"); // debug
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                        System.out.println("  after wait"); // debug
                    }
                }
            }

            evolveOneGeneration(universe);
            universe.setGenerationNumber(generation);
            universeView.print(universe);
            generation++;
        });
        timer.setInitialDelay(1000);
        timer.start();

//        for (int generation = 2; generation <= GlobalData.NUMBER_OF_GENERATIONS; generation++) {
//            evolveOneGeneration(universe);
//            universe.setGenerationNumber(generation);
//            universeView.print(universe);
//
//            try {
//                Thread.sleep(GlobalData.TIME_BETWEEN_GENERATIONS);
//            } catch (InterruptedException ignored) {
//            }
//        }
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
