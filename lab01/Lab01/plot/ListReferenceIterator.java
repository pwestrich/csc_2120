package plot;
import java.util.Iterator;

public class ListReferenceIterator<T> implements Iterator<T>
{
   /** The next reference to iterate over in the list. */
   private Node<T> ref;

   public ListReferenceIterator(Node<T> head)
   {
      ref = head;  //obtain the head reference
   }

   public void remove()
   {   
      throw new UnsupportedOperationException ("Remove not implemented.");
   }

   public boolean hasNext()
   {
      return ref != null;
   }

   public T next()
   {
      if (hasNext())  //should be used in conjunction with hasNext(), but just in case...
      {
         T temp = ref.getItem();
         ref = ref.getNext();
         return temp; 
      }
      else
      {
         throw new ListException("End of list.");
      }

   }
}