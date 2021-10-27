package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class ScoreBoard {

    private Picture score;
    private int scorePlayer1;
    private int scorePlayer2;
    private Text text;

    // CONSTRUCTOR
    public ScoreBoard() {
        score = new Picture(-38, 10, "resources/scoreboard.png");
        score.draw();
        text = new Text(630, 105, scorePlayer1 + " : " + scorePlayer2);
        text.setColor(Color.WHITE);
        text.grow(15, 10);
        text.draw();

    }

    // RESET TEXT
    public void setText() {
        text.delete();
        score = new Picture(-38, 10, "resources/scoreboard.png");
        score.draw();
        text = new Text(630, 105, scorePlayer1 + " : " + scorePlayer2);
        text.setColor(Color.WHITE);
        text.grow(15, 10);
        text.draw();

    }

    // GET SCORE PLAYER 1
    public int getScorePlayer1() {
        return scorePlayer1;
    }

    // GET SCORE PLAYER 2
    public int getScorePlayer2() {
        return scorePlayer2;
    }

    // SET SCORE PLAYER 1
    public void setScorePlayer1() {
        this.scorePlayer1++;
    }

    // SET SCORE PLAYER 2
    public void setScorePlayer2() {
        this.scorePlayer2++;
    }
}

