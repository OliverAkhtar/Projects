import java.awt.Container;

import javax.swing.*;

public class PlayersGUI extends JFrame {

   Container myContentPane = this.getContentPane();
   JTextArea myTextArea = new JTextArea(); //Only one TextArea will be manipulated 

   public PlayersGUI() {
      setSize(600, 300);
      setLocation(100, 100);
      setTitle("Baseball Players");
      createMenus();
      myContentPane.add(myTextArea);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   private void createMenus() {
      JMenuBar menubar = new JMenuBar();
      JMenu FileMenu = new JMenu("File");
      JMenu DisplayMenu = new JMenu("Display");
      JMenuItem Open = new JMenuItem("Open");
      JMenuItem Quit = new JMenuItem("Quit");
      FileMenu.add(Open);
      FileMenu.add(Quit);
      FileMenuHandler fmh = new FileMenuHandler(this);
      Open.addActionListener(fmh);
      Quit.addActionListener(fmh);
      JMenuItem All = new JMenuItem("All");
      JMenuItem Fielders = new JMenuItem("Fielders");
      JMenuItem Pitchers = new JMenuItem("Pitchers");
      DisplayMenu.add(All);
      DisplayMenu.add(Fielders);
      DisplayMenu.add(Pitchers);
      DisplayMenuHandler dmh = new DisplayMenuHandler(this);
      All.addActionListener(dmh);
      Fielders.addActionListener(dmh);
      Pitchers.addActionListener(dmh);
      menubar.add(FileMenu);
      menubar.add(DisplayMenu);
      setJMenuBar(menubar);
   }

   public void DisplayAll() {
      myTextArea.setText(""); //Clear text in case text field already has text
      ListNode p = Project4.mylist.getFirst().next; //Traverse through LinkedList from beginning
      while (p != null) {
         myTextArea.append(Integer.toString(p.player.getNum()) + ',');
         myTextArea.append(p.player.getLast() + ','); //Append all data in object
         myTextArea.append(p.player.getFirst() + ',');
         myTextArea.append(Float.toString(p.player.getBavg()));
         if (p.player instanceof Pitcher) //If player is a Pitcher, we must also print Earned 
            myTextArea.append(","         //Run Average
                  + Float.toString(((Pitcher) p.player).getERavg()) + '\n');
         else
            myTextArea.append("" + '\n');
         p = p.next;
      }
   }

   public void DisplayFielders() {
      myTextArea.setText("");
      ListNode p = Project4.mylist.getFirst().next;
      while (p != null) {
         if (p.player instanceof Fielder) {
            myTextArea.append(Integer.toString(p.player.getNum()) + ',');
            myTextArea.append(p.player.getLast() + ',');
            myTextArea.append(p.player.getFirst() + ',');
            myTextArea.append(Float.toString(p.player.getBavg()) + '\n');
         }
         p = p.next;
      }
   }
   
   public void DisplayPitchers() {
      myTextArea.setText("");;
      ListNode p = Project4.mylist.getFirst().next;
      while (p != null) {
         if (p.player instanceof Pitcher) {
            myTextArea.append(Integer.toString(p.player.getNum()) + ',');
            myTextArea.append(p.player.getLast() + ',');
            myTextArea.append(p.player.getFirst() + ',');
            myTextArea.append(Float.toString(p.player.getBavg()) + ',');
            myTextArea.append(Float.toString(((Pitcher) p.player).getERavg()) + '\n');
         }
         p = p.next;
      }
   }

}
