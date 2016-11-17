/**
 *
 *    BurritoFillerSteak.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the object for Steak.
 */

public final class BurritoFillerSteak extends BurritoFiller{
     
     /**
      *   The only constructor. Calls the parent with item.
      *
      *   @param    item BurritoItem of any kind.
      */
     
     public BurritoFillerSteak(BurritoItem item){
          
          super(item);
          
     }
     
     /**
      *   @return   double, the price of the entire order thus far.
      */
     
     public double getPrice(){
          
          return super.getPrice() + 0.77;
          
     }
     
     /**
      *   The toString method.
      *
      *   @return   String, describing the entire order.
      */
     
     public String toString(){
          
          return super.toString() + "Steak\n";
          
     }
     
}
