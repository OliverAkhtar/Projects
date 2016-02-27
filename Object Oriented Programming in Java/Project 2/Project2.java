/*Show GUI of two columns where the first contains original list of baseball
 * players and the second contains them sorted by last name. */
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.*;
import java.io.*;

public class Project2 {

   public static void main(String[] agrs) throws FileNotFoundException {
      String filename = "players.txt";
      int num = numOfPlayers(filename); // Gets and stores size of needed
                                        // BaseballPlayer array
      BaseballPlayer[] arr = new BaseballPlayer[num];
      fillarray(arr, filename); //Get's player info, creates an object of each
                                //player, and stores it into an array.
      initialize(arr); // Creates 1 row, 2 column JFrame with two text areas
                         // for original and sorted
                         // lists, calls on sort function.

   }

   private static int numOfPlayers(String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      int count = 0;
      while(inputFile.hasNextLine()){
         count++;             //Each time loop iterates, another line of player info exists.
         inputFile.nextLine();
      }
      inputFile.close();
      return count;
   }
   
   private static void fillarray(BaseballPlayer[] arr, String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      int count = 0;
      while (inputFile.hasNextLine()) {
         String line = inputFile.nextLine();
         StringTokenizer strTokenizer = new StringTokenizer(line, ",");
         while (strTokenizer.hasMoreTokens()) { // Each line can have multiple
                                                // tokens
            int playernumber = Integer.parseInt(strTokenizer.nextToken());
            String lastname = strTokenizer.nextToken(); 
            String firstname = strTokenizer.nextToken();
            float Bavg = Float.parseFloat(strTokenizer.nextToken());//tokens show up as pattern
            BaseballPlayer player = new BaseballPlayer(playernumber, lastname, firstname, Bavg);
            arr[count] = player;
         }
         count++; //Must keep track of next available array element.
      }
      inputFile.close();
   }

   public static void initialize(BaseballPlayer[] arr) {
      PlayersGUI myPlayersGUI = new PlayersGUI();
      Container myContentPane = myPlayersGUI.getContentPane();
      TextArea original = new TextArea();
      TextArea sorted = new TextArea();
      myContentPane.add(original);
      myContentPane.add(sorted);
      for(int i = 0; i<arr.length; i++){ //Append before sorting by last name.
         original.append(arr[i].getNum() + ',');
         original.append(arr[i].getLast() + ',');
         original.append(arr[i].getFirst() + ',');
         original.append(arr[i].getBavg() + '\n');
      }
      sort(arr); //Must sort for appending to 2nd column.
      for(int i = 0; i<arr.length; i++){ //Now append again as sorted list.
         sorted.append(arr[i].getNum() + ',');
         sorted.append(arr[i].getLast() + ',');
         sorted.append(arr[i].getFirst() + ',');
         sorted.append(arr[i].getBavg() + '\n');
      }
   }
   
   private static void sort(BaseballPlayer[] arr){
      for (int i = 0; i < arr.length-1; i++){
         int indexLowest = i;
         for(int j = i + 1; j < arr.length; j++)
            if(arr[j].compareTo(arr[indexLowest]) < 0) //Check alphabetic order relationship.
               indexLowest = j;
         BaseballPlayer temp = new BaseballPlayer();  //Temporary object for swap.
         if(arr[indexLowest].compareTo(arr[i])!=0){
            temp = arr[indexLowest];
            arr[indexLowest] = arr[i];
            arr[i] = temp;
         }
      }
   }   
}