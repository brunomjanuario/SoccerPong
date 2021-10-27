package org.academiadecodigo.loopeytunes;

public class  Main {
    public static void main(String[] args) {
        Game game = new Game();

        try {
            while(!game.isQuit()) {
                game.menu();
                if(!game.isQuit()) {
                    game.init();
                    game.start();
                }
            }
        } catch (Exception e) {
            System.out.println("FODEU");
        }

    }
}
