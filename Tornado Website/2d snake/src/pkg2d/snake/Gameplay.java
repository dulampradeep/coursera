/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author PRADEEP
 */
public class Gameplay extends JPanel implements KeyListener ,ActionListner{
    private int[] snakexlength=new int[750];
    private int[] snakeylength=new int[750];
    private Boolean left=false;
    private Boolean right=false;
    private Boolean up=false;
    private Boolean down=false;
    
    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;
    
    private Timer timer;
    private int delay =100;
    
    private ImageIcon snakeimage;
    
    
    
    private ImageIcon titleImage;
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer =new Timer(delay , this);
        timer.start();
    }
    @Override
    public void paint (Graphics g){
        //draw title  image border
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        //draw title image
        titleImage=new ImageIcon("E:\\15-1-2020\\2d snake\\src\\snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        
        //border for plain area
        
        g.setColor(Color.white);
        g.drawRect(24,74,851,575);
        
        //set backgraound for gameplay
       
        g.setColor(Color.black);
        g.fillRect(25,75, 850, 570);
        
        rightMouth=new ImageIcon();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
