package life.view;

import life.model.Data;
import life.model.GlobalData;
import life.model.Universe;

public class UniverseView {
    private final Data data;

    public UniverseView(Data data) {
        this.data = data;
    }

    public void print(Universe universe) {
        data.getGenerationNumberLabel().setText("#" + universe.getGenerationNumber());
        data.getAliveNumberLabel().setText(String.valueOf(universe.totalCellsAlive()));
        int side = GlobalData.SIDE;
        GameOfLifeCell[][] cells = data.getCells();
        boolean[][] matrix = universe.getUniverseMatrix();

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                cells[row][column].setVisible(matrix[row][column]);
            }
        }

        try {
            Thread.sleep(GlobalData.TIME_BETWEEN_GENERATIONS);
        } catch (InterruptedException ignored) {
        }
    }

}
