/**
 *    BurritoFillerBean.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the class for the Black Bean filler in a Burrito.
 */

public final class BurritoFillerBean extends BurritoFiller{
     
     /**
      *   The only constructor. Calls the parent, passing in item
      *
      *   @param    item BurritoItem of any kind.
      */
     
     public BurritoFillerBean(BurritoItem item){
          
          super(item);
          
     }
     
     /**
      *   @return   double, the price of the filler and all items before it,
      */
     
     public double getPrice(){
          
          return super.getPrice() + 0.77;
          
     }
     
     /**
      *   the toString() function.
      *
      *   @return   String, the contents of the current item and all before it.
      */
     
     public String toString(){
          
          return super.toString() + "Black Bean\n";
          
     }
     
}
