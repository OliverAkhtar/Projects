/**Class BaseballPlayer is a blueprint for objects that represent baseball players.
 * @param Number - Number that represents baseball player.
 *        LastName - Last name of baseball player.
 *        FirstName -  First name of baseball player.
 *        BattingAverage - Batting average of baseball player.
 *get methods for each field variable return respective field variable for use in GUI. */
public class BaseballPlayer {
   private int Number;
   private String LastName;
   private String FirstName;
   private float BattingAverage;
   
   public BaseballPlayer(int n, String l, String f, float Bavg){
      if(n<0 || n>99) throw new IllegalArgumentException("Number must be from 0 to 100.");
      if(Bavg<=0 || Bavg>=1.000) throw new IllegalArgumentException("Illegal batting average.");
      Number = n;
      LastName = l;
      FirstName = f;
      BattingAverage = Bavg;
   }
   
   public int getNum(){
      return Number;
   }
   
   public String getLast(){
      return LastName;
   }
   
   public String getFirst(){
      return FirstName;
   }
   
   public float getBavg(){
      return BattingAverage;
   }
   

}
   