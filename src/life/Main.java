package life;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        new GameOfLife();
    }
}
