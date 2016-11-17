/**
 *    BurritoItem.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This class abstracts a BurritoItem. It has four children: Tortilla, 
 *   TortillaImproved, BurritoFiller, and BurritoCondiment.
 */

public abstract class BurritoItem {
     
     /**
      *   Private data member, holding the next item in the order.
      */
     
     private BurritoItem nextItem;
     
     /**
      *   The only constructor.
      *
      *   @param    item BurritoItem or child.
      */
     
     public BurritoItem(BurritoItem item){
          
          nextItem = item;
          
     }
     
     /**
      *   Returns the cost of the next item
      *
      *   @return   double, containing the cost of all previous items in the order
      */
     
     public double getPrice(){
          
          if (nextItem != null) return nextItem.getPrice();
          
          else return 0.0;
          
     }
     
     /**
      *   The good ol' "toString" method. Calls the toString() methods for all
      *   previous items
      *
      *   @return   string containing a description of the entire order.
      */
     
     public String toString(){
          
          if (nextItem != null) return nextItem.toString();
          
          else return "";
          
     }
     
     /**
      *   Checks to see if there is a duplicate condiment in the order
      *
      *   @param    check BurritoCondiment object to be tested
      *   @return   boolean, telling if the order has the given condiment
      */
     
     public boolean isDuplicate(BurritoCondiment check){
          
          return (check.equals(nextItem) || nextItem.isDuplicate(check));
          
     }

}
