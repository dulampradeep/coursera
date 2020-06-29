/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.snake;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author PRADEEP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JFrame obj=new JFrame();
       Gameplay gp=new Gameplay();
       obj.setBounds(150,50,900,650);
       obj.setBackground(Color.DARK_GRAY);
       obj.setResizable(false);
       obj.setVisible(true);
       obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       obj.add(gp);
    }
    
}
