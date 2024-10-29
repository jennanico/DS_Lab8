
public class Queue<T>
{
    //be sure that your attributes are protected, so subclasses can use them

	protected Node<T> front;
	protected Node<T> back;
   
   public class Node<T>
   {
		T val;
		Node<T> next;
		
		public Node(T value, Node<T> nextNode)
		{
			this.val = value;
			this.next = nextNode;
		}
   }

   public Queue()
   {
	   this.front = null;
	   this.back = null;
   }

    /**Adds val to the end of the queue
     */
   public void push(T val)
   {
	   Node<T> myNode = new Node<T>(val, null);
	   if (front == null)
	   {
		   front = myNode;
		   back = myNode;
	   } else 
	   {
		   back.next = myNode;
		   back = myNode;
	   }
   }


    /**
       removes and returns the oldest value in the queue
       throws QueueUnderFlowException if the queue is empty
     */
   public T pop()
   {
       if (front == null)
       {
    	   throw new QueueUnderFlowException();
       }
	   
	   T hold = front.val;
       front = front.next;
       return hold;
   }    


    /**
      returns true if the queue is empty
     */

   public boolean isEmpty()
   {
       return (front == null);
   }




}
