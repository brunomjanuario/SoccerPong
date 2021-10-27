package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {
    private Picture pic;
    private Picture nets;

    // CONSTRUCTOR
    public Background() {

        // SET BACKGROUND STADIUM
        pic = new Picture(10,10, "resources/Stadium.jpg");
        pic.draw();
    }

    // SET THE NETS IN FRONT OF EVERYTHING
    public void setGoal() {
        nets = new Picture(10,10, "resources/balizas.png");
        nets.draw();
    }
}
