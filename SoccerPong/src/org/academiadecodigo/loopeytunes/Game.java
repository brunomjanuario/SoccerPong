package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.loopeytunes.player.Player;
import org.academiadecodigo.loopeytunes.player.Player1;
import org.academiadecodigo.loopeytunes.player.Player2;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game implements KeyboardHandler {
    private Background field;
    private Ball ball;
    private int delay = 30;
    private Player player1;
    private Player player2;
    private Keyboard kb;
    private boolean start = false;
    private boolean quit = false;
    private ScoreBoard scoreBoard;

    // MENU
    public void menu() {
        Picture menu = new Picture(10, 10, "resources/Menu.jpg");
        initKb();
        Sound championsLeague = new Sound("/resources/sounds/ChampionsLeague.wav");
        championsLeague.play(true);
        championsLeague.setLoop(5);
        while (!start && !quit) {
            menu.draw();
        }
        menu.delete();
        championsLeague.stop();
    }

    // INIT THE GAME
    public void init() {
        Sound whistle = new Sound("/resources/sounds/Whistle.wav");
        field = new Background();
        player1 = new Player1();
        player2 = new Player2();
        ball = new Ball();
        scoreBoard = new ScoreBoard();
        whistle.play(true);
        field.setGoal();
    }

    // START THE GAME
    public void start() throws InterruptedException {
        // SET SOUND STADIUM
        Sound stadium = new Sound("/resources/sounds/StadiumSound.wav");
        stadium.play(true);
        stadium.setLoop(5);

        // WHILE ONE OF THE PLAYERS REACH THE 3 GOALS
        while (scoreBoard.getScorePlayer1() < 3 && scoreBoard.getScorePlayer2() < 3) {

            // DELAY
            Thread.sleep(delay);

            // PLAYERS MOVE
            player1.move();
            player2.move();

            // BALL MOVE
            ball.move();

            checkLimit();

            playerTouch();

            isGoal();

            player1.moveDown();
            player2.moveDown();
        }

        stadium.stop();
        start = false;
        menu();
    }

    // EXIT THE GAME
    public boolean isQuit() {
        return quit;
    }

    // GAME LIMITS
    private void checkLimit() {

        // LIMIT FLOOR
        if (ball.getPos().getRow() > 64) {
            ball.setDirectionToggle(true);
        }

        // LIMIT SKY
        if (ball.getPos().getRow() < 6) {
            ball.setDirectionToggle(false);
        }

        // LIMIT LEFT
        if (ball.getPos().getCol() < 4) {
            ball.setRightToggle(true);
            ball.setLeftToggle(false);
        }

        // LIMIT RIGHT
        if (ball.getPos().getCol() > 122) {
            ball.setRightToggle(false);
            ball.setLeftToggle(true);
        }

        // LIMIT NET LEFT
        if (ball.getPos().getRow() < 32 && ball.getPos().getRow() > 30 && ball.getPos().getCol() < 20) {
            ball.setDirectionToggle(true);
        }

        // LIMIT NET RIGHT
        if (ball.getPos().getRow() < 32 && ball.getPos().getRow() > 30 && ball.getPos().getCol() > 116) {
            ball.setDirectionToggle(true);
        }
    }

    // CHECK IF PLAYERS CONTACT THE BALL
    private void playerTouch() {
        if (ball.getPos().getRow() + 4 > player1.getPos().getRow() &&
                ball.getPos().getCol() > player1.getPos().getCol() &&
                ball.getPos().getCol() < player1.getPos().getCol() + 10) {
            ball.setDirectionToggle(true);
            ball.setRightToggle(true);
            ball.setLeftToggle(false);
        }

        if (ball.getPos().getRow() + 4 > player2.getPos().getRow() &&
                ball.getPos().getCol() > player2.getPos().getCol() &&
                ball.getPos().getCol() < player2.getPos().getCol() + 10) {
            ball.setDirectionToggle(true);
            ball.setRightToggle(false);
            ball.setLeftToggle(true);
        }
    }

    //GOAL
    private void isGoal() {
        if (ball.getPos().getRow() > 30 && ball.getPos().getCol() < 6) {
            goal();
            scoreBoard.setScorePlayer2();
            scoreBoard.setText();
            return;
        }

        if (ball.getPos().getRow() > 30 && ball.getPos().getCol() > 120) {
            goal();
            scoreBoard.setScorePlayer1();
            scoreBoard.setText();
            return;
        }
        return;
    }
    private void goal() {
        ball.getPic().delete();
        ball = new Ball();
        Sound goalSound = new Sound("/resources/sounds/GoalSound.wav");
        goalSound.play(false);
    }

    // KEYBOARD SET
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_A:
                player1.setState(Player.PlayerState.LEFT);
                break;
            case KeyboardEvent.KEY_D:
                player1.setState(Player.PlayerState.RIGHT);
                break;
            case KeyboardEvent.KEY_W:
                player1.setState(Player.PlayerState.JUMP);
                break;

            case KeyboardEvent.KEY_LEFT:
                player2.setState(Player.PlayerState.LEFT);
                break;
            case KeyboardEvent.KEY_RIGHT:
                player2.setState(Player.PlayerState.RIGHT);
                break;
            case KeyboardEvent.KEY_UP:
                player2.setState(Player.PlayerState.JUMP);
                break;
            case KeyboardEvent.KEY_SPACE:
                start = true;
                break;
            case KeyboardEvent.KEY_Q:
                quit = true;
                System.exit(0);
                break;

            default:
        }
    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        player1.setState(Player.PlayerState.NORMAL);
        player2.setState(Player.PlayerState.NORMAL);
    }

    // KEYBOARD INIT
    private void initKb() {

        kb = new Keyboard(this);
        KeyboardEvent aPress = new KeyboardEvent();
        aPress.setKey(KeyboardEvent.KEY_A);
        aPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent dPress = new KeyboardEvent();
        dPress.setKey(KeyboardEvent.KEY_D);
        dPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent wPress = new KeyboardEvent();
        wPress.setKey(KeyboardEvent.KEY_W);
        wPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent wRelease = new KeyboardEvent();
        wRelease.setKey(KeyboardEvent.KEY_W);
        wRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        KeyboardEvent aRelease = new KeyboardEvent();
        aRelease.setKey(KeyboardEvent.KEY_A);
        aRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        KeyboardEvent dRelease = new KeyboardEvent();
        dRelease.setKey(KeyboardEvent.KEY_D);
        dRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(aPress);
        kb.addEventListener(aRelease);
        kb.addEventListener(dPress);
        kb.addEventListener(dRelease);
        kb.addEventListener(wPress);
        kb.addEventListener(wRelease);
        KeyboardEvent leftPress = new KeyboardEvent();
        leftPress.setKey(KeyboardEvent.KEY_LEFT);
        leftPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent rightPress = new KeyboardEvent();
        rightPress.setKey(KeyboardEvent.KEY_RIGHT);
        rightPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent upPress = new KeyboardEvent();
        upPress.setKey(KeyboardEvent.KEY_UP);
        upPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent upRelease = new KeyboardEvent();
        upRelease.setKey(KeyboardEvent.KEY_UP);
        upRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        KeyboardEvent leftRelease = new KeyboardEvent();
        leftRelease.setKey(KeyboardEvent.KEY_LEFT);
        leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        KeyboardEvent rightRelease = new KeyboardEvent();
        rightRelease.setKey(KeyboardEvent.KEY_RIGHT);
        rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(leftPress);
        kb.addEventListener(rightPress);
        kb.addEventListener(upPress);
        kb.addEventListener(upRelease);
        kb.addEventListener(leftRelease);
        kb.addEventListener(rightRelease);

        //START
        KeyboardEvent spacePress = new KeyboardEvent();
        spacePress.setKey(KeyboardEvent.KEY_SPACE);
        spacePress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(spacePress);


        //QUIT
        KeyboardEvent qPress = new KeyboardEvent();
        qPress.setKey(KeyboardEvent.KEY_Q);
        qPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(qPress);

    }
}
