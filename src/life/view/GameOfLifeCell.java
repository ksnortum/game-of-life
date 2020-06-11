package life.view;

import life.model.CellStatus;
import life.model.GlobalData;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeCell extends JPanel {
    private final int side = GlobalData.SIDE;
    private final int x;
    private final int y;
    private final CellStatus status;

    public GameOfLifeCell(int x, int y, CellStatus status) {
        this.x = x;
        this.y = y;
        this.status = status;
        setOpaque(false);
        int length = side * GlobalData.PIXELS_PER_SIDE;
        setPreferredSize(new Dimension(length + 2, length + 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if (status == CellStatus.DRAW) {
            g.drawRect(x, y, side, side);
        } else if (status == CellStatus.FILL) {
            g.fillRect(x, y, side, side);
        }
    }
}
