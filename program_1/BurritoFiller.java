/**
 *
 *    BurritoFiller.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the abstract class for burrito fillers.
 */

public abstract class BurritoFiller extends BurritoItem {

     /**
      *   The connstructor for Tortillas. Calls the parent with item.
      *
      *   @param    item Tortilla item, the first item in the order.
      */
     
     public BurritoFiller(Tortilla item){
          
          super(item);
          
     }
     
     /**
      *   The constructor for BurritoFillers. Calls the parent with item.
      *
      *   @param    item BurritoFiller object.
      */
     
     public BurritoFiller(BurritoItem item){
          
          super(item);
          
     }
     
     /**
      *   Gets the price of all prefious items.
      *
      *   @return   double, the price of all previous items
      */
     
     public double getPrice(){
          
          return super.getPrice();
          
     }
     
     /**
      *   The toString() method.
      *
      *   @return   String, the current order.
      */
     
     public String toString(){
          
          return super.toString() + "Filler: ";
          
     }

}
