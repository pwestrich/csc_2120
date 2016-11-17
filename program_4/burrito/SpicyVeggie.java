/**
 *    SpicyVeggie.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is a predefined kind of burrito.
 */

package burrito;

public final class SpicyVeggie extends BurritoBuilder {
     
     /**
      *   The only constructor. Calls the parent constructor
      */
     
     public SpicyVeggie(){
          
          super();
          
     }
     
     /**
      *   Orders a SpicyVeggie burrito.
      *
      *   @return   BurritoItem, the top of the order.
      */
     
     public BurritoItem orderBurrito(){
          
          buildTortilla('C');  //Add tortilla
          buildFiller('B');    //Add beans
          buildCondiment('P'); //Add pico
          buildCondiment('J'); //Add jalapenos
          
          return super.orderBurrito(); //Return the built burrito.
          
     }

}
