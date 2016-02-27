/*Show GUI of three columns, first containing original list of baseball players,
 * second contains fielders sorted by player number, and third contains pitches 
 * sorted by player number. Using Linked List to store baseball player objects.*/

import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.*;
import java.io.*;

public class Project3 {
   public static void main(String[] agrs) throws FileNotFoundException {
      LinkedList mylist = new LinkedList();
      fillList(mylist, "players.txt"); 
      String[] unsortedcolumn = new String[sizeneeded("players.txt")]; //For first column
      fillunsorted(unsortedcolumn, "players.txt"); 
      initialize(mylist, unsortedcolumn);
   }


   
   private static void fillList(LinkedList list, String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      while (inputFile.hasNextLine()) { //Multiple lines in file of all players
         Fielder Fplayer = null;
         Pitcher Pplayer = null;
         int n = 0;
         String last = null;
         String first = null;
         float Bavg = 0;
         float ERavg = 0;
         String line = inputFile.nextLine();
         StringTokenizer strTokenizer = new StringTokenizer(line, ",");
         if(strTokenizer.nextToken().equals("F")){ //input line has Fielder info
            n = Integer.parseInt(strTokenizer.nextToken());
            last = strTokenizer.nextToken();   //Info is ordered in same pattern for each line
            first = strTokenizer.nextToken();
            Bavg = Float.parseFloat(strTokenizer.nextToken());
            Fplayer = new Fielder(n, last, first, Bavg);
            list.append(Fplayer);
         }
         else {                                    //input line has Pitcher info
            n = Integer.parseInt(strTokenizer.nextToken());
            last = strTokenizer.nextToken();
            first = strTokenizer.nextToken();
            Bavg = Float.parseFloat(strTokenizer.nextToken());
            ERavg = Float.parseFloat(strTokenizer.nextToken());
            Pplayer = new Pitcher(n, last, first, Bavg, ERavg);
            list.append(Pplayer);
         }
      }
      inputFile.close();
   }

   private static int sizeneeded(String filename) throws FileNotFoundException{
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      int count = 0;
      while (inputFile.hasNextLine()) {
         count++; //Each iteration means another line
         inputFile.nextLine();
      }
      inputFile.close();
      return count;
   }
   
   private static void fillunsorted(String[] arr, String filename) throws FileNotFoundException{
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      int count = 0;
      while(inputFile.hasNextLine()){
         arr[count] = inputFile.nextLine();
         count++; //Must keep track of next available array element.
      }
      inputFile.close();
   }
   
   private static void initialize(LinkedList list, String[] arr) {
      PlayersGUI myPlayersGUI = new PlayersGUI();
      Container myContentPane = myPlayersGUI.getContentPane();
      TextArea unsortedAll = new TextArea();
      TextArea sortedFielders = new TextArea();
      TextArea sortedPitchers = new TextArea();
      myContentPane.add(unsortedAll);
      myContentPane.add(sortedFielders);
      myContentPane.add(sortedPitchers);
      for(int i = 0; i<arr.length; i++){ //First unsorted column of all players
         unsortedAll.append(arr[i] + '\n');
      }
      ListNode p = list.getFirst().next;
      while(p!=null){
         if(p.player instanceof Fielder){ //2nd column of Fielders from sorted linked list
            sortedFielders.append(Integer.toString(p.player.getNum()) +',');
            sortedFielders.append(p.player.getLast() + ',');
            sortedFielders.append(p.player.getFirst() + ',');
            sortedFielders.append(Float.toString(p.player.getBavg()) + '\n');
         }
         if(p.player instanceof Pitcher){ //3rd columns of Pitchers from sorted linked list
            sortedPitchers.append(Integer.toString(p.player.getNum()) +',');
            sortedPitchers.append(p.player.getLast() + ',');
            sortedPitchers.append(p.player.getFirst() + ',');
            sortedPitchers.append(Float.toString(p.player.getBavg()) + ',');
            sortedPitchers.append(Float.toString(((Pitcher) p.player).getERavg()) + '\n');
         }
         p = p.next;
      }
   }
   
   
         
}