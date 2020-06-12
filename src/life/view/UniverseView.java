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
        GameOfLifePanel gameOfLifePanel = data.getGameOfLifePanel();
        gameOfLifePanel.setUniverse(universe);
        //gameOfLifePanel.repaint();
        int length = GlobalData.SIDE * GlobalData.PIXELS_PER_SIDE + GlobalData.EXTRA_PIXELS;
        gameOfLifePanel.paintImmediately(0, 0, length, length);
    }

}
