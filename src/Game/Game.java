/*
ID: 212054480
ID: 322991563
 */

package Game;

import Geometry.Point;
import Geometry.Rectangle;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Collidable;
import Sprites.Paddle;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game.Game class represents a game with a collection of sprites and collidables.
 * It manages the game environment, including the GUI, paddle, blocks, and balls.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Paddle paddle;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private List<Ball> balls;
    private Counter score;


    /**
     * Constructs a new Game.Game instance, initializing the sprite collection, game environment,
     * GUI, and paddle.
     */
    public Game() {
        this.sprites = new SpriteCollection(new ArrayList<>());
        this.environment = new GameEnvironment(new ArrayList<>());
        this.gui = new GUI("Arknoid", 800, 600);
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        this.paddle = new Paddle(keyboard, new Block(new Rectangle(new Point(20, 565), 70, 15),
                Color.BLACK, null));
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.balls = new ArrayList<>();
        this.score = new Counter(0);
    }

    /**
     * Returns the list of balls currently in the game.
     *
     * @return a List of Ball objects representing the balls in the game
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * Adds a collidable to the game environment.
     *
     * @param c the collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Creates the borders of the game.
     * Adds blocks to the game to represent the borders.
     */
    public void createBorders() {
        Block right = new Block(new Rectangle(new Point(780, 20), 20, 580), Color.GRAY, null);
        right.addToGame(this);
        Block left = new Block(new Rectangle(new Point(0, 20), 20, 580), Color.GRAY, null);
        left.addToGame(this);
        Block up = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.GRAY, null);
        up.addToGame(this);
    }

    /**
     * Initializes a specified number of blocks with the given color and adds them to the game.
     * Each block is created with a specified position, size, and color. The blocks are also
     * added to the provided list of blocks and the game's block counter is updated.
     *
     * @param color the color of the blocks to be initialized
     * @param numBlocks the number of blocks to be initialized
     * @param y the y-coordinate of the row where the blocks will be placed
     * @param blocks the list to which the initialized blocks will be added
     */
    public void initBlocks(Color color, int numBlocks, int y, List<Block> blocks) {
        Counter levelCounter = new Counter(0);
        for (int i = 780 - (50 * numBlocks); i <= 730; i += 50) {
            Block block = new Block(new Rectangle(new Point(i, y), 50, 20), color, levelCounter);
            levelCounter.increase(1);
            blocks.add(block);
            block.addToGame(this);
        }
        this.remainingBlocks.increase(numBlocks);
    }

    /**
     * Initializes three balls with specified positions, velocities, and adds them to the game.
     * Each ball is set with a specific starting position, velocity, color, and game environment.
     * The initialized balls are added to the game's ball list and game environment.
     */
    public void initBalls() {
        Ball ball1 = new Ball(new Point(50, 50), 5, Color.WHITE);
        ball1.setVelocity(5, -6);
        ball1.setGameEnvironment(this.environment);
        ball1.addToGame(this);
        this.balls.add(ball1);
        Ball ball2 = new Ball(new Point(600, 50), 5, Color.WHITE);
        ball2.setVelocity(5, 6);
        ball2.setGameEnvironment(this.environment);
        ball2.addToGame(this);
        this.balls.add(ball2);
        Ball ball3 = new Ball(new Point(200, 50), 5, Color.WHITE);
        ball3.setVelocity(5, -6);
        ball3.setGameEnvironment(this.environment);
        ball3.addToGame(this);
        this.balls.add(ball3);
    }

    /**
     * Initializes the game environment by setting up the background, paddle, borders,
     * blocks, and balls. Adds all elements to the game.
     */
    public void initialize() {
        Block backGround = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.LIGHT_GRAY, null);
        backGround.addToGame(this);
        this.paddle.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        List<Block> blocks = new ArrayList<>();
        createBorders();
        Block deathRegion = new Block(new Rectangle(new Point(20, 599), 760, 20), Color.BLACK, null);
        deathRegion.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        deathRegion.addHitListener(ballRemover);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        initBlocks(Color.BLUE, 12, 110, blocks);
        initBlocks(Color.RED, 11, 130, blocks);
        initBlocks(Color.YELLOW, 10, 150, blocks);
        initBlocks(Color.MAGENTA, 9, 170, blocks);
        initBlocks(Color.WHITE, 8, 190, blocks);
        initBlocks(Color.GREEN, 7, 210, blocks);
        for (Block b : blocks) {
            b.addHitListener(blockRemover);
            b.addHitListener(stl);
        }
        initBalls();
        this.remainingBalls.increase(3);
        this.paddle.setBalls(this.balls);
        ScoreIndicator si = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20), this.score);
        si.addToGame(this);
    }

    /**
     * Runs the game by starting the animation loop.
     * The loop draws all sprites and notifies them that time has passed.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (this.remainingBlocks.getValue() == 0) {
                gui.close();
                return;
            }
            if (this.remainingBalls.getValue() == 0) {
                this.score.increase(100);
                gui.close();
                return;
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollection().remove(c);
    }

    /**
     * Removes a sprite object from the game's sprite collection.
     *
     * @param s the sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.getCollection().remove(s);
    }
}
