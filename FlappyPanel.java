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
    final int WallVelocity = 15 ;
    final int WallWidth =50;
    int flappyHeight = Height/4;
    int flappyVelocity =0,flappyAccelearation =8,flappyImpulse = 1;   //basically velocity is moving down or up  ||  accelearation controls velocity status ||  impulse (which is adding up on accleration ) is max of flappy jumping tendency 
    // int wallx = Width+10;
    int wallx [] = {Width,Width+ Width/2};
    // int gapOfWall  = r.nextInt(200);
    int gapOfWall[]  = {r.nextInt(150),r.nextInt(170)};
    
    public boolean gameOver = false;
     

    public FlappyPanel(){
        setSize(Width,Height); //25 pixel as x and 50 pixel as y
        setFocusable(true); 
        addKeyListener(this);

        setBackground(Color.BLACK);
        new Timer(100,this).start();
    }

    
    public void paint(Graphics g){
        super.paint(g);//square is gonna be apearing everywhere if not super
        if(!gameOver){
        repeater();
        DrawWall(g);
        DrawFlappy(g);
        
        }
        else{
            
        }

    }

    private void DrawFlappy(Graphics g){

        
        g.setColor(Color.white);//bird color


        g.fillRect(150,flappyHeight+flappyVelocity , 25, 25);//50X50 bird and x y is 150 and other var

    }
    private void DrawWall(Graphics g){
        for(int i = 0;i<2;i++){
        
        
        g.setColor(Color.RED);
        g.fillRect(wallx[i], 0, WallWidth, Height);

        g.setColor(Color.black);
        g.fillRect(wallx[i], gapOfWall[i], WallWidth, 100);

        }

    }
    private void repeater(){
        for(int  i=0;i<2;i++){
            if(wallx[i] <=100 && wallx[i]+WallWidth >=100){            //75+25 pixel sixe
                if((flappyHeight + flappyVelocity) >=0  && (flappyHeight +flappyVelocity) <=gapOfWall[i]
                || (flappyHeight + flappyVelocity +25 >= (gapOfWall[i]+100))  && (flappyHeight+flappyVelocity +25)<= Height){
                    gameOver = true;
                }
            }
            if(wallx[i]+WallWidth<=0){
                if(i==0){ 
                    wallx[0] = Width;


                }
                else{ 
                     wallx[1] = Width ;
                }
            }
        }
        
    }
    public void actionPerformed(ActionEvent e) {


        flappyAccelearation += flappyImpulse;
        flappyVelocity += flappyAccelearation;
        wallx[0]-=WallVelocity;
        wallx[1]-=WallVelocity;


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
