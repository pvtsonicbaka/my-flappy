import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Color;


public class FlappyPanel extends JPanel implements KeyListener ,ActionListener{
    Random r  = new Random();


    final int Width=525 , Height=550 ;
    final int WallVelocity = 5;
    final int WallWidth =50;
    int flappyHeight = Height/4;
    int flappyVelocity =0,flappyAccelearation =8,flappyImpulse = 1;   //basically velocity is moving down or up  ||  accelearation controls velocity status ||  impulse (which is adding up on accleration ) is max of flappy jumping tendency 
    int wallx = Width+10;
    int gapOfWall  = r.nextInt(200);
    public boolean gameOver = false;
     

    public FlappyPanel(){
        setSize(Width,Height); //25 pixel as x and 50 pixel as y
        setFocusable(true); 
        addKeyListener(this);

        setBackground(Color.BLACK);
        new Timer(60 ,this).start();
    }

    
    public void paint(Graphics g){
        super.paint(g);//square is gonna be apearing everywhere if not super
        if(!gameOver){
        DrawWall(g);
        DrawFlappy(g);
        }
        else{

        }

    }

    private void DrawFlappy(Graphics g){

        if(wallx <=100 && wallx+WallWidth >=100){            //75+25 pixel sixe
            if((flappyHeight + flappyVelocity) >=0  && (flappyHeight +flappyVelocity) <=gapOfWall  
            || (flappyHeight + flappyVelocity +25 >= (gapOfWall+100))  && (flappyHeight+flappyVelocity +25)<= Height){
                gameOver = true;
            }
        }
        g.setColor(Color.white);//bird color


        g.fillRect(150,flappyHeight+flappyVelocity , 25, 25);//50X50 bird and x y is 150 and other var

    }
    private void DrawWall(Graphics g){

        g.setColor(Color.RED);
        g.fillRect(wallx, 0, WallWidth, Height);

        g.setColor(Color.black);
        g.fillRect(wallx, gapOfWall, WallWidth, 100);

    }
    private void repeater(){
        
    }
    public void actionPerformed(ActionEvent e) {


        flappyAccelearation += flappyImpulse;
        flappyVelocity += flappyAccelearation;
        wallx-=WallVelocity;


        repaint();

    }
    public void keyPressed(KeyEvent e) {
        int keyss = e.getKeyCode();
        if(keyss == KeyEvent.VK_SPACE){
            flappyAccelearation = -8;
        }
        
    }
    
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
         
    }

   

}
