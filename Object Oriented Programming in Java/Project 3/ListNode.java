/**Class ListNode represents data structure that holds a BaseballPlayer object and a pointed
 * to the next node for use as a linked list. 
 * @param player - BaseballPlayer object.
 *        next - Next list node.*/
public class ListNode {
   public BaseballPlayer player;
   public ListNode next;
   
   public ListNode(BaseballPlayer p){
      player = p;
      next = null;
   }
}
