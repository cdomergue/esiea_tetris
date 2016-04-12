package com.domergue.bastide.jTetris.IHM;

import com.domergue.bastide.jTetris.components.Board;
import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoBuilder;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoRotater;
import com.domergue.bastide.jTetris.components.throwables.SideTouched;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {

    /**
     * Screen width
     */
    private static final int WIDTH = 300;
    /**
     * Screen height
     */
    private static final int HEIGHT = 660;

    private static final TetriminoBuilder tBuild = TetriminoBuilder.getInstance();
    private static final TetriminoRotater tRotate = TetriminoRotater.getInstance();

    private Board board;
    private boolean moving;
    private int direction;

    private MovingDown movingDown;

    private Image unit;
    private TiledMap map;
    private GameContainer container;
    private Music background;

    public Game() {
        super("jTetris");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.start();
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        // g.drawString("Hello, " + Integer.toString(counter) + "!", 50, 50);
        this.map.render(0, 0);
        drawBoard(g);

    }

    private void drawBoard(Graphics g) {
        for (int i = 2; i < board.DEFAULT_LINES; i++) {
            for (int j = 0; j < board.DEFAULT_COLUMNS; j++) {
                if (board.getCell(i, j).isOccupied()) {
                    g.drawImage(unit, j * 30, i * 30);
                }
            }
        }

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("maps/board.tmx");
        board = Board.getInstance();
        Tetrimino tetrimino = tBuild.pickRandom();
        board.addNewMovingTetrimino(tetrimino);
        loadImages();
        movingDown = new MovingDown(board, Thread.currentThread());
        movingDown.start();
        background = new Music("music/music.ogg");
        background.loop();
    }

    private void loadImages() {
        try {
            unit = new Image("images/unit_yellow.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        try {
            move();
        } catch (SideTouched e) {

        }
        board.checkLine();
    }

    private void move() throws SideTouched {
        if (this.moving) {
            switch (this.direction) {
                case 1:
                    board.moveMovingTetriminoLeft();
                    break;
                case 3:
                    board.moveMovingTetriminoRight();
                    break;

                case 0:
                    board.setMovingTetrimino(tRotate.rotate(board.getMovingTetrimino(), TetriminoRotater.ROTATE_LEFT));
                    break;
                case 2:
                    board.setSpeed(42);
                    movingDown.interrupt();
                    break;
            }
            this.moving = false;
            board.tryUpdateMovingTetrimino();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_UP:
                this.direction = 0;
                this.moving = true;
                break;
            case Input.KEY_LEFT:
                this.direction = 1;
                this.moving = true;
                break;
            case Input.KEY_DOWN:
                this.direction = 2;
                this.moving = true;
                break;
            case Input.KEY_RIGHT:
                this.direction = 3;
                this.moving = true;
                break;
            case Input.KEY_M:
                if(this.background.playing()){
                    this.background.stop();
                } else {
                    this.background.loop();
                }
                break;

        }
    }

    @Override
    public void keyReleased(int key, char c) {
        this.moving = false;
        board.setSpeed(board.getNormalSpeed());
    }

}
