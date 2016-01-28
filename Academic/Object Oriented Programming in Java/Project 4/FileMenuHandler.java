/**Class FileMenuHandler allows event driven GUI programming. It specifically handles a 
 * particular menu named FileMenu. It allows the ability to use menu options
 * that will open a file or quit the program.
 * @param - jframe Holds JFrame to be used for menus and events.
 * actionPerformed method executes code depending on which event/menu-item is selected.
 * openFile method allows the selection of a file from a file system.
 * createList method creates a LinkedList of BaseballPlayer objects in their respective
 * subclass from a text file.*/

import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.*;

public class FileMenuHandler implements ActionListener {
   
   JFrame jframe;
   
   public FileMenuHandler(JFrame jf){
      jframe = jf;
   }

   public void actionPerformed(ActionEvent event) {
      String  itemName;
      itemName = event.getActionCommand();
      if (itemName.equals("Open"))
         try {
            openFile();
         } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
         }
      else if (itemName.equals("Quit"))
         System.exit(0);
   }
   
   private void openFile( ) throws FileNotFoundException {
      JFileChooser chooser = new JFileChooser( );;
      int status;
      status = chooser.showOpenDialog(null);
      if (status == JFileChooser.APPROVE_OPTION) 
         createList(chooser.getSelectedFile()); //Send file to function that creates linked list
      else 
         JOptionPane.showMessageDialog(null, "Open File dialog canceled");
   }
   
   private static void createList(File filename) throws FileNotFoundException {
      Scanner inputFile = new Scanner(filename);
      while (inputFile.hasNextLine()) { // Multiple lines in file of all players
         Fielder Fplayer = null;
         Pitcher Pplayer = null;
         int n = 0;
         String last = null;
         String first = null;
         float Bavg = 0;
         float ERavg = 0;
         String line = inputFile.nextLine();
         StringTokenizer strTokenizer = new StringTokenizer(line, ",");
         if (strTokenizer.nextToken().equals("F")) { // input line has Fielder
                                                     // info
            if (strTokenizer.countTokens() < 4) //Not enough tokens
               System.out.println(line);
            else {
               n = Integer.parseInt(strTokenizer.nextToken());

               last = strTokenizer.nextToken(); // Info is ordered in same
                                                // pattern
                                                // for each line
               first = strTokenizer.nextToken();
               Bavg = Float.parseFloat(strTokenizer.nextToken());
               try {
                  Fplayer = new Fielder(n, last, first, Bavg);
                  Project4.mylist.append(Fplayer);
               } catch (IllegalArgumentException e) {
                  System.out.println(line);
               }
            }
         } 
         else { // input line has Pitcher info
            if (strTokenizer.countTokens() < 5) //Not enough tokens
               System.out.println(line);
            else {

               n = Integer.parseInt(strTokenizer.nextToken());
               last = strTokenizer.nextToken();
               first = strTokenizer.nextToken();
               Bavg = Float.parseFloat(strTokenizer.nextToken());
               ERavg = Float.parseFloat(strTokenizer.nextToken());
               try {
                  Pplayer = new Pitcher(n, last, first, Bavg, ERavg);
                  Project4.mylist.append(Pplayer);
               } catch (IllegalArgumentException e) {
                  System.out.println(line);
               }
            }
         }
      }
      inputFile.close();
   }

}
