/*
ID: 212054480
ID: 322991563
 */

import Game.Game;

/**
 * The Ass5Game class is the entry point for running the game.
 * It creates a Game.Game object, initializes it, and runs it.
 */
public class Ass5Game {

    /**
     * The main method to start the game.
     * It creates an instance of the Game.Game class, initializes the game,
     * and starts the game loop.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
