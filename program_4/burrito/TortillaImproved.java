/**
 *    Burrito Driver.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the definintion of the TortillaImproved class.
 */

package burrito;

public final class TortillaImproved extends BurritoItem {
     
     /** A String with the name of the tortilla */
     private final String tortillaType;
     
     /** A double containing the price of the tortilla */
     private final double price;
     
     /**
      *   The only constructor. Sets up the class to be a tortilla of the kind given
      *
      *   @param    price double, the cost of the tortilla
      *   @param    tortillaType String, the kind of tortilla to be.
      */
     
     public TortillaImproved(double price, String tortillaType){
          
          super(null);
          
          this.price = price;
          this.tortillaType = tortillaType;
          
     }
     
     /**
      *   Gets the price of the tortilla.
      *
      *   @return double, the price of the tortilla.
      */
     
     public double getPrice(){
          
          return price;
          
     }

     /**
      *   The toString() method. Gives the current state of the object.
      *
      *   @return   String, containing the entire order to thi point.
      */
     
     public String toString(){
          
          return super.toString() + "Tortilla: " + tortillaType + "\n";
          
     }

}
