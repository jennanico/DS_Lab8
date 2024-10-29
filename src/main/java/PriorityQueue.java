import java.util.Comparator;

public class PriorityQueue<T> extends Queue<T>
{

   Comparator<T> compare;

   public PriorityQueue(Comparator<T> comp)
   {
      compare = comp;
   }


    //@Override
   public void push(T val)
   {
	   Node<T> myNode = new Node<T>(val, null);
	   if (front == null)
	   {
		   front = myNode;
		   back = myNode;
	   } else 
	   {
		 //  back.next = myNode;
		 //  back = myNode;
		   sort(val, front);
	   }
   }
   
   private void sort(T insertVal, Node<T> node) 
   {
	   if (node.next == null)
	   {
		   Node<T> myNode = new Node<T>(insertVal, null);
		   back.next = myNode;
		   back = myNode;
		   return;
	   }
//	   if (insertVal < node.val && insertVal > node.next.val)
	   if (compare.compare(insertVal, (T) node.val) < 0 && compare.compare(insertVal, (T) node.next.val) > 0)
	   {
		   Node<T> myNode = new Node<T>(insertVal, node.next);
		   node.next = myNode;
		   return;
	   }
	   sort(insertVal, node.next);
   }


}
