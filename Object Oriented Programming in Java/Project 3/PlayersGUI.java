import java.awt.*;
import javax.swing.*;


public class PlayersGUI extends JFrame{
   public PlayersGUI(){
      setSize(600, 300);
      setLocation(100, 100);
      setTitle("Baseball Players");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridLayout(1,3));
      setVisible(true);
   }
}
