/**Class DisplayMenuHandler allows event driven GUI programming. It specifically handles a 
 * particular menu named DisplayMenu. It allows the ability to use menu options
 * that will display objects of a particular subclass of class BaseballPlayer.
 * @param jframe - Holds JFrame to be used for menus and events.
 * actionPerformed method executes code depending on which event/menu-item is selected.*/

import java.awt.event.*;
import javax.swing.*;

public class DisplayMenuHandler implements ActionListener {

   JFrame jframe;
   
  
   
   public DisplayMenuHandler(JFrame jf) {
      jframe = jf;
   }

   public void actionPerformed(ActionEvent event) {
      String itemName;
      itemName = event.getActionCommand();
      if (itemName.equals("All"))
         Project4.myGUI.DisplayAll(); 
      else if (itemName.equals("Fielders"))
         Project4.myGUI.DisplayFielders();
      else if (itemName.equals("Pitchers"))
         Project4.myGUI.DisplayPitchers();
   }
   
}