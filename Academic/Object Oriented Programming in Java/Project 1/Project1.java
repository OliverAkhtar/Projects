/**The following Project1 class implements an application that reads strings from a text file line by 
 * line, separates strings using String Tokenizer, then displays an original and sorted list of the
 * strings(alphabetical) on a JFrame. Use of objects from the io.File, Scanner, StringTokenizer, 
 * swing, awt are used.*/
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Project1 {
 
   public static void main(String[] agrs) throws FileNotFoundException {
      String filename ="textfile.txt";
      int tokens = (numOfTokens(filename)); //Gets and stores size of needed String array.
      String[] words = new String[tokens];
      fillarray(words, filename); //Creates a string array with all tokens in the text file.
      initialize(words); //Creates 1 row, 2 column JFrame with two text areas for original and sorted
                         //lists, calls on sort function. 

   }

   /**numOfTokens takes the filename, opens it, reads each line, counts the token in each line, then 
   * returns the total number of tokens to main, closes file.*/
   private static int numOfTokens(String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      int count = 0;
      while (inputFile.hasNextLine()) { //Read until no more lines remaining.
         String line = inputFile.nextLine();
         StringTokenizer strTokenizer = new StringTokenizer(line, ",;"); //Separate by "," and ";".
         count += strTokenizer.countTokens();
      }
      inputFile.close();
      return count;
   }
   
   /**fillarray takes the filename, opens it, reads each line, stores the token in each line in a 
   *string array also passed to it, closes file. */   
   private static void fillarray(String[] arr, String filename) throws FileNotFoundException{
      File file = new File(filename);
      Scanner inputFile = new Scanner(file);
      int count = 0;
      while(inputFile.hasNextLine()){
         String line = inputFile.nextLine();
         StringTokenizer strTokenizer = new StringTokenizer(line, ",;");
         while(strTokenizer.hasMoreTokens()){ // Each line can have multiple tokens
            arr[count] = strTokenizer.nextToken();
            count++; // After a token is stored, we must keep track of next empty array element.
         }
      }
      inputFile.close();      
   }
   
   /**initialize creates a JFrame with two TextAreas in a 1-Row, 2 Column GridLayout that displays
   *words from a String array in a original order and alphabetical order.*/
   public static void initialize(String [] arr){
      JFrame myGUI = new JFrame();
      myGUI.setSize(400,200);
      myGUI.setLocation(100, 100);
      myGUI.setTitle("Text File");
      myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myGUI.setVisible(true);
      myGUI.setLayout(new GridLayout(1,2));
      Container myContentPane = myGUI.getContentPane();
      TextArea original = new TextArea();
      TextArea ordered = new TextArea();
      myContentPane.add(original);
      myContentPane.add(ordered);
      for (int i = 0; i<arr.length; i++) 
         original.append(arr[i] + "\n"); //Display words as received from text file.
      sort(arr);                         //Sort array of words before printing again.
      for(int i = 0; i<arr.length; i++)  //Now display in alphabetical order.
         ordered.append(arr[i] + "\n");
   }
   
   /**sort takes a String array and uses a modified selection sort algorithm to compare an array of
   *strings and sort them in alphabetical order.*/
   private static void sort(String [] arr){
      for(int i = 0; i < arr.length-1; i++){          //Starting from first string, compare each 
         int indexLowest = i;                         //following string to determine which comes
         for(int j = i + 1; j < arr.length; j++)      //earlier alphabetically.
            if(arr[j].compareTo(arr[indexLowest]) < 0) 
               indexLowest = j;                        
         if(arr[indexLowest].compareTo(arr[i])!=0){  
            String temp = arr[indexLowest];            //Swap string with greater alphabetical 
            arr[indexLowest] = arr[i];                 //with string that was used to compare
            arr[i] = temp;                             //with all subsequent strings.
         }
      }
   }
   
}