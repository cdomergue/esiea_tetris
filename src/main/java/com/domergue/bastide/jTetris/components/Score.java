package com.domergue.bastide.jTetris.components;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by Christophe on 12/04/2016.
 */
public class Score {

    private static Score instance = null;

    private long currentScore = -20;
    private long bestScore;
    private File file = new File("scores");

    private Score(){
        readFile();
    }

    private void readFile() {

        try {
            DataInputStream reader = new DataInputStream (new FileInputStream (file));
            bestScore = reader.readLong();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de score n'a pas été trouvé");
            bestScore = 0;
        } catch (IOException e) {
            System.out.println("Erreur lors de l'ouverture du fichier des scores");
            e.printStackTrace();
            bestScore = 0;
        }
    }

    public void newHighScore(long score) {
        bestScore = score;
        DataOutputStream writer = null;
        try {
            writer = new DataOutputStream(new FileOutputStream(file));
            writer.flush();
            writer.writeLong(score);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Veuillez vérifier que le fichier de score est présent");
        } catch (IOException e) {
            e.printStackTrace();
        }


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

    public long getBestScore() {
        return bestScore;
    }

}
