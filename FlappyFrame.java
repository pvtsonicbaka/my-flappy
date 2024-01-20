import javax.swing.JFrame;

public class FlappyFrame extends JFrame{

    public FlappyFrame(){
        add(new FlappyPanel());

        setSize(500,500);//screen size
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String argsp[]){
        new FlappyFrame();
    }
}