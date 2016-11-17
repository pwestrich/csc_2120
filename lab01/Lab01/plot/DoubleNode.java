package plot;

public class DoubleNode<T> extends Node<T>
{
   private DoubleNode<T> prev;
   // inherited Node next;

   public DoubleNode(T item) 
   {
      super(item, null);
      prev = null;
   } 

   public DoubleNode(T item, DoubleNode<T> next, DoubleNode<T> prev) 
   {
      super(item, next);
      this.prev = prev;
   } 

   public void setPrev(DoubleNode<T> prev)
   {
      this.prev = prev;
   }

   public DoubleNode<T> getPrev()
   {
      return prev;
   }

   //we can override a method and change the return type
   public DoubleNode<T> getNext()
   {
      return (DoubleNode<T>) super.getNext();
   }

   //inherited public void setNext(Node next)

} 

