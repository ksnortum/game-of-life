package life.model;

import java.util.Random;

public class Universe {
    private final int side = GlobalData.SIDE;
    private boolean[][] universeMatrix;
    private int generationNumber;

    public void createUniverse() {
        universeMatrix = new boolean[side][side];
        generationNumber = 1;
        Random random = new Random();

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                universeMatrix[row][column] = random.nextBoolean();
            }
        }
    }

    public boolean[][] getUniverseMatrix() {
        return universeMatrix;
    }

    public void setUniverseMatrix(boolean[][] universeMatrix) {
        this.universeMatrix = universeMatrix;
    }

    public int totalCellsAlive() {
        int total = 0;

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                total += universeMatrix[row][column] ? 1 : 0;
            }
        }

        return total;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public void setGenerationNumber(int generationNumber) {
        this.generationNumber = generationNumber;
    }
}
