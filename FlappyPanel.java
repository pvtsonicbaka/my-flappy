import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.nio.channels.WritableByteChannel;
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
    int score =0;
    int wallx [] = {Width,Width+ Width/2};
    // int gapOfWall  = r.nextInt(200);
    int gapOfWall[]  = {r.nextInt(160 ),r.nextInt(170)};
    
    public boolean gameOver = false;
    public Timer t=new Timer(100,this);
     

    public FlappyPanel(){
        setSize(Width,Height); //25 pixel as x and 50 pixel as y
        setFocusable(true); 
        addKeyListener(this);

        setBackground(Color.BLACK);
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
            if(wallx[i] <=100 && wallx[i]+WallWidth >=100 || wallx[i] <=75 && Width >=75){            //75+25 pixel sixe
                if((flappyHeight + flappyVelocity) >=0  && (flappyHeight +flappyVelocity) <=gapOfWall[i] ||
                 (flappyHeight + flappyVelocity +25 >= (gapOfWall[i]+100))  && (flappyHeight+flappyVelocity +25)<= Height){
                    gameOver = true;
                }
            }
            if(flappyHeight + flappyVelocity <=0 || flappyHeight+flappyVelocity+25 >=Height){
                gameOver = true;
            }
            
            if( 75 > wallx[i]   + WallWidth){
                score++;
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
        if(keyss == KeyEvent.VK_S){
            t.start();
        }
        if(keyss == e.VK_R){
            t.stop();
             flappyHeight = Height/4;
            flappyVelocity =0;
            flappyAccelearation =8;
            wallx[0] = Width;
            score=0;
            wallx[1] = Width + Width/2;
            gapOfWall[0]= r.nextInt(160);
            gapOfWall[1]=r.nextInt(170);
            gameOver = false;
            repaint();
        }
        
    }
    
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
         
    }

   

}
