package com.domergue.bastide.jTetris.IHM;

import com.domergue.bastide.jTetris.components.Board;
import com.domergue.bastide.jTetris.components.Score;
import com.domergue.bastide.jTetris.components.tetriminos.Tetrimino;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoBuilder;
import com.domergue.bastide.jTetris.components.tetriminos.TetriminoRotater;
import com.domergue.bastide.jTetris.components.throwables.SideTouched;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.InputStream;

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
    private Music end;
    private Score score = Score.getInstance();

    public Game() {
        super("jTetris");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.setVSync(true);
        app.setShowFPS(false);
        app.start();
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        this.map.render(0, 0);
        if(board.isGameEnded()){
            setFont(g);
            g.setColor(new Color(164,0,0));
            g.drawString("Final Score : ", 30, 268);
            g.drawString(Long.toString(score.getCurrentScore()), 30, 302);
            g.drawString("Best Score : ", 30, 336);
            g.drawString(Long.toString(score.getBestScore()), 30, 368);
        } else {
            g.drawString("Score : " + Long.toString(score.getCurrentScore()), 10, 10);
            g.drawString("Best Score : " + Long.toString(score.getBestScore()), 10, 30);
            drawBoard(g);
        }


    }

    private void setFont(Graphics g) {
        try {
            InputStream inputStream	= ResourceLoader.getResourceAsStream("fonts/nasalization-rg.ttf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(32f); // set font size
            TrueTypeFont font = new TrueTypeFont(awtFont, false);
            g.setFont(font);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        end = new Music("music/end.ogg");
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
            if(!board.isGameEnded()){
                move();
                board.checkLine();
            } else {
                endGame();
            }
        } catch (SideTouched e) {

        }
    }

    private void endGame() {
        background.stop();
        if(!end.playing()){
            end.loop();
        }
        if(score.getCurrentScore() > score.getBestScore()){
            score.newHighScore(score.getCurrentScore());
        }
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
                } else if (!board.isGameEnded()){
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
