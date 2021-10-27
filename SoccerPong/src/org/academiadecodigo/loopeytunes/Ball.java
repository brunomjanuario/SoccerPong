package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball {
    private Picture pic;
    private Position pos;
    private boolean directionToggle;
    private boolean leftToggle;
    private boolean rightToggle;

    public Ball() {
        pic = new Picture(600, 250, "resources/ball.png");
        pos = new Position(63,28);
        pic.draw();
        directionToggle = false;
        leftToggle = false;
        rightToggle = false;
    }

    //MOVE
    public void move() {

        if(rightToggle) {
            moveRight();
        }

        if(leftToggle) {
            moveLeft();
        }

        if (!directionToggle) {
            moveDown();
        } else {
            moveUp();
        }
    }
    public void moveUp() {
        pic.translate(0,-(40 * pos.getRow()/72));
        pos.setRow(-((40 * pos.getRow()/72)/10));
    }
    public void moveDown() {
        pic.translate(0,40 * pos.getRow()/72);
        pos.setRow((40 * pos.getRow()/72)/10);
    }
    public void moveLeft() {
        pic.translate(-30,0);
        pos.setCol(-(3));
    }
    public void moveRight() {
        pic.translate(30,0);
        pos.setCol((3));
    }

    // GET POS
    public Position getPos() {
        return pos;
    }

    // GET PIC
    public Picture getPic() {
        return pic;
    }

    // DIRECTION UP DOWN
    public void setDirectionToggle(boolean directionToggle) {
        this.directionToggle = directionToggle;
    }

    // RIGHT TOGGLE
    public void setRightToggle(boolean rightToggle) {
        this.rightToggle = rightToggle;
    }

    // LEFT TOGGLE
    public void setLeftToggle(boolean leftToggle) {
        this.leftToggle = leftToggle;
    }
}
