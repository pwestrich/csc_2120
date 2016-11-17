/**
 *    SpicyMeaty.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is a predefined kind of burrito.
 *
 */

public final class SpicyMeaty extends BurritoBuilder {
     
     /**
      *   The only constructor. Calls parent.
      */
     
     public SpicyMeaty(){
          
          super();
          
     }
     
     /**
      *   Orders the burrito. Is predefined; no need to actuallt build it.
      *   Overrides the orderBurrito() method in the parent.
      *
      *   @return   The built burrito.
      */
     
     public BurritoItem orderBurrito(){
          
          buildTortilla('C');  //Add Chipotle tortilla
          buildFiller('S');    //Add steak
          buildFiller('C');    //Add chicken
          buildCondiment('J'); //Add jalapenos
          buildCondiment('P'); //Add pico
          
          return super.orderBurrito();
     
     }

}
