/*This program opens a GUI which allows the user to open the .txt file which
 * contains a list of the baseball players, then via the display menu, select
 * whether all players, pitchers, or fielders should be displayed. */
import java.io.*;

public class Project4 {
   static PlayersGUI myGUI;
   static LinkedList mylist = new LinkedList();
   
   public static void main(String[] agrs) throws FileNotFoundException {
      myGUI = new PlayersGUI();
   }

}
