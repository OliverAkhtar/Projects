/**Class BaseballPlayer is a blueprint of objects that represents baseball players.
 *@param number - Player's number.
 *       LastName - Player's last name.
 *       FirstName - Player's first name.
 *       BattingAverage - Player's batting average.
 *get functions for each parameter return string version of the data to append to JFrame.
 *compareTo function accesses private LastName data, and uses String class' compareTo
 *function to determine alphabetic order for sorting purposes.*/

public class BaseballPlayer {
   private int Number;
   private String LastName;
   private String FirstName;
   private float BattingAverage;
   
   public BaseballPlayer(int n, String l, String f, float avg){
      if(n<0 || n>100) throw new IllegalArgumentException("Number must be from 0 to 100.");
      if(avg<=0 || avg>=1) throw new IllegalArgumentException("Illegal batting average.");
      Number = n;
      LastName = l;
      FirstName = f;
      BattingAverage = avg;
   }
   
   public BaseballPlayer(){
      Number = 0;
      LastName = null;
      FirstName = null;
      BattingAverage = 0;
   }

   public String getNum(){
      return Integer.toString(Number); //String type will readily append to JFrame.
   }
   
   public String getLast(){
      return LastName;
   }
   
   public String getFirst(){
      return FirstName;
   }
   
   public String getBavg(){
      return Float.toString(BattingAverage);
   }
   
   public int compareTo(BaseballPlayer o){
      return (this.LastName.compareTo(o.LastName));
   }

}
