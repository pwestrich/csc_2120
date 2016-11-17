/**
 *    TortillaChipotle.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the class for a Chipotle tortilla
 *
 */

public final class TortillaChipotle extends Tortilla {
     
     /**
      *   The only constructor. Calls the parent constructor.
      */
     
     public TortillaChipotle(){
          
          super();
          
     }
     
     /**
      *   The toString() function.
      *
      *   @return   string, containing, "Tortilla: Chipotle\n"
      */
     
     public String toString(){
          
          return super.toString() + "Chipotle\n";
          
     }
     
     /**
      *   @return   double, the price of the tortilla and all previous items
      */
     
     public double getPrice(){
          
          return 2.29 + super.getPrice();
          
     }
     
}
