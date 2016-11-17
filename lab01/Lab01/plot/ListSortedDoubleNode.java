package plot;
import java.util.Iterator;

public class ListSortedDoubleNode<T extends Comparable<T>> implements ListSortedInterface<T>
{

   //interior reference to linked list of items
   private DoubleNode<T> loc; 
   private int size;          

   public ListSortedDoubleNode() 
   {
      size = 0;
      loc = null;
   } 

   public boolean isEmpty() 
   {
      return size == 0;
   } 

   public int size() 
   {
      return size;
   }  

   private Node<T> getNextRec(Node<T> curr, Node<T> prev, T item)
   {
      int comp = item.compareTo(curr.getItem());
      Node<T> next = curr.getNext();

      //the order of these checks matters
      if (comp < 0)  //moved one too far
      {
         return prev;
      }
      else if (next == null || comp == 0)  //reached the end of the list or found an exact match
      {
         return curr;
      }
      else
      {
         return getNextRec(next, curr, item);
      }
   }

   private DoubleNode<T> locateNode(T item, boolean op) throws ListException
   {
      // size = 0 special cases
      if (size == 0)
      {
         if (op)
         {
            return null;  //insert into an empty list
         }
         else
         {
            throw new ListException("Item not found.");  //trying to remove from an empty list
         }
      }

      //loc is not null
      DoubleNode<T> curr = loc;
      int comp = item.compareTo(curr.getItem());

      if (comp > 0)  //use next refs
      {
         //trailing ref obtained recursively
         curr = (DoubleNode<T>) getNextRec(curr, curr.getPrev(), item);
         //need to compute this again
         comp = item.compareTo(curr.getItem());  
      }

      //use prev refs (this loop will execute for insertion at top of a non-empty list)
      //the loop will stop at the head, however
      else if (comp < 0) 
      {
         //positioned correctly without trailing ref
         while (curr.getPrev() != null && comp < 0)
         {
            curr = curr.getPrev();
            comp = item.compareTo(curr.getItem());
         }
      }

      // other special cases (above loops stop when a match is found to make these tests easy)
      if (comp == 0 && op)  //adding and found a match
      {
         throw new ListException("Duplicate found."); 
      }
      else if(comp != 0 && !op)  //removing and didn't find a match
      { 
         throw new ListException("Item not found."); 
      }

      //for add into an empty list, return null
      //for add at the top of a non-empty list, return a reference to the top of the list
      //for a general add, return the node right before the insertion location 
      //for remove, return the node to be removed

      return curr; 
   }

   public void add(T item) throws ListException 
   {
      addDN(item);
   }

   //default visibility methods for ComplexData
   DoubleNode<T> addDN(T item) throws ListException 
   {
      //add the item in as usual and return a reference to its final location
      DoubleNode<T> prev = locateNode(item, true);
      DoubleNode<T> node = new DoubleNode<T>(item);

      //inserting into an empty list (loc is null)
      if (prev == null) 
      {
         node.setNext(loc);
      } 

      //inserting at the top of a non-empty list
      else if (prev.getPrev() == null && item.compareTo(prev.getItem()) < 0)  
      {
         prev.setPrev(node);
         node.setNext(prev);
      }

      //general case (the node right before the insertion location returned by locateNode for add)
      else 
      {
         DoubleNode<T> curr = prev.getNext();  //could be null (can't set a prev link)
         node.setNext(curr);
         node.setPrev(prev);

         prev.setNext(node);
         if (curr != null)  //check for end of list
         {
            curr.setPrev(node);
         }
      } 

      loc = node;
      size++;
      return node;
   } 

   public void remove(T item) throws ListException 
   {
      DoubleNode<T> prev = locateNode(item, false);
      remove(prev);
   }  

   T remove(DoubleNode<T> curr)
   {
      T temp;
      DoubleNode<T> prev;
      DoubleNode<T> after;

      prev = curr.getPrev();
      after = curr.getNext();
      temp = curr.getItem();
      loc = after;  

      if (prev != null)
      {
         prev.setNext(after);
         loc = prev;  //move loc here if not removing the last item
      }

      if (after != null)
      {
         after.setPrev(prev);
      }

      size--;
      return temp;
   }  

   public void removeAll() 
   {
      if (loc != null)
      {
         loc = findHead();
         loc = loc.getNext();

         // need to remove all previous links to prevent memory leak
         while (loc != null)
         {
            loc.setPrev(null);
            loc = loc.getNext();
         }

         loc = null;
         size = 0;
      }
   } 

   private DoubleNode<T> findHead()
   {
      DoubleNode<T> find = loc;

      if (find == null)
      {
         return find;
      }

      while (find.getPrev() != null)
      {
         find = find.getPrev();
      }

      return find;
   }

   public Iterator<T> iterator()
   {
      //locate the head for the iterator without moving interior node
      return new ListReferenceIterator<T>(findHead());  
   } 
} 