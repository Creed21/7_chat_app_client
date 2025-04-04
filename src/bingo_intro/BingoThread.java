/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bingo_intro;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Korisnik
 */
public class BingoThread extends Thread {
    private javax.swing.JTextField txt1Bingo;
    private boolean run;
    private Random random;

    public BingoThread(JTextField txt1Bingo) {
        this.txt1Bingo = txt1Bingo;
        this.random = new Random();
        run = true;
    }

    @Override
    public void run() {
        while(run) {
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BingoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int number = random.nextInt(100);
            txt1Bingo.setText(number+"");
            System.out.println("random number: "+number);
            
        }
    }
    
    public void stopThread() {
        run = false;
    }
}
