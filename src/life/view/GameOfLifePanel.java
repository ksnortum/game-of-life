package life.view;

import life.model.GlobalData;
import life.model.Universe;

import javax.swing.*;
import java.awt.*;

public class GameOfLifePanel extends JPanel {
    private final int side = GlobalData.SIDE;
    private final int pixelsPerSide = GlobalData.PIXELS_PER_SIDE;
    private final int length = side * pixelsPerSide;

    private Universe universe = null;

    public GameOfLifePanel() {
        int dimLength = length + GlobalData.EXTRA_PIXELS;
        setPreferredSize(new Dimension(dimLength, dimLength));
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paint grid
        for (int i = 0; i < length + 2; i += side) {
            g.drawLine(0, i, length, i); // vertical line
            g.drawLine(i, 0, i, length); // horizontal line
        }

        // Paint cells
        if (universe == null) {
            return;
        }

        boolean[][] matrix = universe.getUniverseMatrix();

        if (matrix == null) {
            return;
        }

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                if (matrix[row][column]) {
                    g.fillRect(column * pixelsPerSide, row * pixelsPerSide, pixelsPerSide, pixelsPerSide);
                } else {
                    g.drawRect(column * pixelsPerSide, row * pixelsPerSide, pixelsPerSide, pixelsPerSide);
                }
            }
        }
    }
}
