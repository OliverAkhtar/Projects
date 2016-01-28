/**Class LinkedList is implementation of a LinkedList data structure to be used to link
 * objects of type BaseballPlayer.
 * @param first - First list node.
 *        last - Last list node.
 *        length = Length of linked list.
 *append method adds another list node which holds a BaseballPlayer objects to the linked in
 *a position such that the list is sorted by the player's number.
 *getFirst method returns the first list node for use in main function.*/
public class LinkedList {
   private ListNode first = new ListNode(null);
   private ListNode last = first;
   private int length = 0;
   
   public void append(BaseballPlayer p){
      ListNode n = new ListNode(p);
      if(length==0){ //Empty list, so add to start of list.
         first.next = n;
         last = n;
         length++;
      }
      
      else if(n.player.getNum()>last.player.getNum()){ //New player's number greater than
         last.next = n;                                //last player's number so add to end.
         last = n;
         length++;
      }
      
      else if(n.player.getNum()<first.next.player.getNum()){//New player's number less than
         n.next = first.next;                               //first player's number so add to
         first.next = n;                                    //beginning.
         length++;
      }
      else{ //New player's number is in between numbers of already linked players.
         ListNode t = first.next; //Starting from beginning. 
         while(t.next.player.getNum()<n.player.getNum()){//Advance until next node's player
            t=t.next;                                    //number is greater than new player's
         }                                               //number.
         n.next = t.next;  //Re-link nodes so new player is linked to in between two players,
         t.next = n;  //one who has a greater number, and one whose number is less.
         length++;
      }
      
   }
   
   public ListNode getFirst(){
      return first;
   }
}
