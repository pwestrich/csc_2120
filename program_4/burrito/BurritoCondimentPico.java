/**
 *    BurritoCondimentPico.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   The class for Pico de Gallo.
 */

package burrito;

public final class BurritoCondimentPico extends BurritoCondiment{
     
     /**
      *   The only constructor. Calls the parent with item.
      *
      *   @param    item BurritoItem of any kind.
      */
     
     public BurritoCondimentPico(BurritoItem item){
          
          super(item);
          
     }
     
     /**
      *   The toString() function.
      *
      *   @return   String, containing the entire previpus order.
      */
     
     public String toString(){
          
          return super.toString() + "Pico de Gallo\n";
          
     }
     
     /**
      *   @return   double, the price of the entire previous order.
      */
     
     public double getPrice(){
          
          return super.getPrice() + 0.11;
          
     }
     
     /**
      *   The equals() method. Tests if the given object is an instance of the
      *   curent object.
      *
      *   @param bc      The object to be tested
      *   @return        boolean, whether ornot the object is a duplicate
      */
     
     public boolean equals(BurritoCondiment bc){
          
          return (bc instanceof BurritoCondimentPico);
     }
     
}
