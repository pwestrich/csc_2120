/**
 *
 *    Tortilla.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the bastract class for all tortillas.
 *
 */

public abstract class Tortilla extends BurritoItem {
     
     /**
      *   The only constructor. Creates the tortilla object by calling the
      *   parent constructor and passing null.
      */
     
     public Tortilla(){
          
          super(null);
          
     }
     
     /**
      *   The toString() function.
      *
      *   @return   string, containing a description of the entire order to this point.
      */
     
     public String toString(){
          
          return super.toString() + "Tortilla: ";
          
     }
     
     /**
      *   @return   double, the price of all previous items in the order.
      */
     
     public double getPrice(){
          
          return super.getPrice();
          
     }

}
