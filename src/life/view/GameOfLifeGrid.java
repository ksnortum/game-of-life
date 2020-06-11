package life.view;

import life.model.GlobalData;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeGrid extends JPanel {
    private final int side = GlobalData.SIDE;
    private final int length = side * GlobalData.PIXELS_PER_SIDE;

    public GameOfLifeGrid() {
        setPreferredSize(new Dimension(length + 2, length+ 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < length + 2; i += side) {
            g.drawLine(0, i, length, i); // vertical line
            g.drawLine(i, 0, i, length); // horizontal line
        }
    }
}
