/**Class Pitcher extends BaseballPlayer and represents objects that are baseball players who
 * play the Pitcher position.
 * @param EarnedRunAverage - Pitcher's earned run average.
 * Accepts all parameters of BaseballPlayer which are passed to it using super
 * constructor.
 * getERavg returns EarnedRunAverage field variable.*/
public class Pitcher extends BaseballPlayer {
   private float EarnedRunAverage;
   
   public Pitcher(int n, String l, String f, float Bavg, float ERavg){
      super(n, l, f, Bavg);
      EarnedRunAverage = ERavg;
   }
   
   public float getERavg(){
      return EarnedRunAverage;
   }
}
