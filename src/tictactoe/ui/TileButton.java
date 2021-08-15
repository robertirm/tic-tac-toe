package tictactoe.ui;

import javafx.scene.control.Button;

public class TileButton extends Button {
    private final int x;
    private final int y;

    public TileButton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
