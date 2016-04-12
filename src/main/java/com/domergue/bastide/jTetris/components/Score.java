package com.domergue.bastide.jTetris.components;

/**
 * Created by Christophe on 12/04/2016.
 */
public class Score {

    private static Score instance = null;

    private long currentScore = -20;

    private Score(){

    }

    public static Score getInstance(){
        return instance == null ? instance = new Score() : instance;
    }


    public long getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(long currentScore) {
        this.currentScore = currentScore;
    }

    public void add(int add) {
        this.setCurrentScore(this.getCurrentScore() + add);
    }
}
