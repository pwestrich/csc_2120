/**
 *    BurritoFillerChicken.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the object for chicken.
 */

package burrito;

public final class BurritoFillerChicken extends BurritoFiller{
     
     /**
      *   The only constructor. Simply calls the parent with item.
      *
      *   @param    item BurritoItem the next item in the order.
      */
     
     public BurritoFillerChicken(BurritoItem item){
          
          super(item);
          
     }
     
     /**
      *   @return   double, the price of the entire order at this point.
      */
     
     public double getPrice(){
          
          return super.getPrice() + 0.77;
          
     }
     
     /**
      *   The toString() method.
      *
      *   @return   String, the current order.
      */
     
     public String toString(){
          
          return super.toString() + "Chicken\n";
          
     }
     
}
