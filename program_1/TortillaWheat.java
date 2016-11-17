/**
 *    TortillaWheat.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the class for a Wheat tortilla.
 */

public final class TortillaWheat extends Tortilla{
     
     /**
      *   The only constructor. Calls the parent constructor.
      */
     
     public TortillaWheat(){
          
          super();
          
     }
     
     /**
      *   The toString() function.
      *
      *   @return   A string, containing a description of the order
      */
     
     public String toString(){
          
          return super.toString() + "Wheat\n";
          
     }
     
     /**
      *   @return A double, the price of the tortilla and all previous items.
      */
     
     public double getPrice(){
          
          return 0.79 + super.getPrice();
          
     }
     
}
