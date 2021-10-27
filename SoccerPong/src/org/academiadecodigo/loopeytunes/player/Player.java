package org.academiadecodigo.loopeytunes.player;

import org.academiadecodigo.loopeytunes.Position;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {
    private Picture pic;
    private Position pos;
    private PlayerState state;

    // CONSTRUCTOR
    public Player(int posX, int posY, int col, int row, String photo) {
        pic = new Picture(posX, posY, photo);
        pic.draw();
        pos = new Position(col, row);
        state = PlayerState.NORMAL;
    }

    // GET POS
    public Position getPos() {
        return pos;
    }

    // MOVE
    public void move() {

        switch (state) {
            case LEFT:
                if (pos.getCol() > 3) {
                    pic.translate(-50, 0);
                    pos.setCol(-5);
                }
                break;
            case RIGHT:
                if (pos.getCol() < 112) {
                    pic.translate(50, 0);
                    pos.setCol(5);
                }
                break;
            case JUMP:
                if (pos.getRow() == 51) {
                    pic.translate(0, -100);
                    pos.setRow(-10);
                }
                break;
            case NORMAL:
                break;
        }
    }

    // MOVE DOWN
    public void moveDown() {
        if (pos.getRow() < 51) {
            pic.translate(0, 50);
            pos.setRow(5);
        }
    }

    // SET STATE
    public void setState(PlayerState state) {
        this.state = state;
    }

    // PLAYER STATUS
    public enum PlayerState {
        NORMAL,
        JUMP,
        LEFT,
        RIGHT;
    }
}



