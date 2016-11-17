/**
 *    TortillaSpinich.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the class for a Spinach tortilla.
 *
 */

public final class TortillaSpinach extends Tortilla{
     
     /**
      *   The only constructor. Calls the parent constructor.
      */
     
     public TortillaSpinach(){
          
          super();
          
     }
     
     /**
      *   The toString() function.
      *
      *   @return   A string, containing, "Tortilla: Spinach\n"
      */
     
     public String toString(){
          
          return super.toString() + "Spinach\n";
          
     }
     
     /**
      *   @return A double, the price of the tortilla and all previous items
      */
     
     public double getPrice(){
          
          return 1.89 + super.getPrice();
          
     }
     
}
 