import javax.swing.*;

public class UI {
    private JFrame frame;
    private Game game;

    public UI(Game game){
        this.game = game;
        frame = new JFrame("Risiko");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
    }
    public void show(){
        frame.setVisible(true);
    }
}
