/*
   This program reads a sentence typed by a user, determines the number
   of upper case letters, lower case letters, and digits, then prints
   counts, all using JOptionPane message dialog.
*/

import javax.swing.JOptionPane; //For use of dialog boxes.

public class Project0
{
 public static void main(String[] args)
 {
  String input; // To hold user's input sentence.
  
  // Get the user's sentence.
  input = JOptionPane.showInputDialog("Enter your sentence.");

  // Determine if user wants to stop and exit, otherwise repeats the process as required.
  while((input.equalsIgnoreCase("stop")!=true)){

  int upper = 0; // Counter for upper case letters.
  int lower = 0; // Counter for lower case letters.
  int digit = 0; // Counter for digits.

  // Detemine if characters are uppercase letters, lowercase, or digits.
  for(int i = 0; i < input.length(); i++){
   char test;
   test = input.charAt(i); //Stores a character of the string starting from first index to last.
   if(Character.isUpperCase(test)) upper++; //Check if character is uppercase letter,
   if(Character.isLowerCase(test)) lower++; //lower
   if(Character.isDigit(test)) digit++;}    //or  digit, and counts respective conditions.

  // Print counts
  JOptionPane.showMessageDialog(null, "Upper Case Letters: " + upper + "\n" + 
                                      "Lower Case Letters: " + lower + "\n" +
                                      "Digits: " + digit);

  //Set-up repetition of task.
  input = JOptionPane.showInputDialog("Enter your sentence.");}
 }
}
    