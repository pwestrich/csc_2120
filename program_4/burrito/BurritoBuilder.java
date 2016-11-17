/**
 *    BurritoBuilder.java
 *
 *    for CSC 2120 Program #1
 *    "Decorated Burritos"
 *
 *    @author Philip Westrich
 *    @date   October 7, 2013
 *
 *   This is the builder class for creating BurritoItems. It implements the
 *   Builder design pattern we discussed in class.
 */

package burrito;

public class BurritoBuilder {
     
     /**
      *   This is the data member that holds the top of the order.
      *   It is essentially a linked list.
      */
     private BurritoItem topOfOrder;
     
     /**
      *   The default constructor. Initializes the object to the empty state.
      */
     
     public BurritoBuilder(){
          
          topOfOrder = null;
          
     }
     
     /**
      *   Creates the tortilla. Must be called first; will fail otherwise.
      *
      *   @param    tortillaChar char, either C, S, or W.
      *   @return   Returns a boolean telling if the operation was successful or not.
      */
     
     public boolean buildTortilla(char tortillaChar){
          
          if (hasTortilla()){ //If there is already a tortilla, can't add another
               
               return false;
               
          }
          
          else if (tortillaChar == 'C'){ //Create a chipotle tortilla
               
               //topOfOrder = new TortillaChipotle();
               topOfOrder = new TortillaImproved(2.29, "Chipotle");
               return true;
               
          }
          
          else if (tortillaChar == 'S'){ //Create a Spinach tortilla
               
               //topOfOrder = new TortillaSpinach();
               topOfOrder = new TortillaImproved(1.89, "Spinach");
               return true;
               
          }
          
          else if (tortillaChar == 'W'){ //Create a wheat tortilla
               
               //topOfOrder = new TortillaWheat();
               topOfOrder = new TortillaImproved(0.79, "Wheat");
               return true;
               
          }
          
          else return false; //Return false if the character given is bad
          
     }
     
     /**
      *   Adds any requested fillers. Must be called after making a tortilla; will fail otherwise.
      *   
      *   @param    fillerChar Accepts a single char, either G, S, or B
      *   @return   boolean, telling whether the operation is successful or not.
      */
     
     public boolean buildFiller(char fillerChar){
          
          if (!hasTortilla()){
               
               return false; //If there is nothing in the order, we can't add a filler
               
          }
          
          else if (fillerChar == 'G'){ //Add chicken
               
               topOfOrder = new BurritoFillerChicken(topOfOrder);
               return true;
               
          }
          
          else if (fillerChar == 'S'){ //Add steak
               
               topOfOrder = new BurritoFillerSteak(topOfOrder);
               return true;
               
          }
          
          else if (fillerChar == 'B'){ //Add beans
               
               topOfOrder = new BurritoFillerBean(topOfOrder);
               return true;
               
          }
          
          else return false; //Return false if a bad character was given
          
     }
     
     /**
      *   Adds the condiments to the order. Only one of each can be added.
      *
      *   @param    condiChar Accepts a single char, either P, G, O, or J.
      *   @return   boolean, telling whether or not the operation was successful.
      *             Will fail if a duplicate condiment is given.
      */
     
     public boolean buildCondiment(char condiChar){ //ADD CHECKING
          
          if (!hasTortilla()){
               
               return false; //Return false if there is no order
               
          }
          
          if (condiChar == 'P'){ //Add pico
               
               topOfOrder = new BurritoCondimentPico(topOfOrder);
               return true;
               
          }
          
          else if (condiChar == 'G'){ //Add guacamole
               
               topOfOrder = new BurritoCondimentGuacamole(topOfOrder);
               return true;
               
          }
          
          else if (condiChar == 'O'){ //Add onions
               
               topOfOrder = new BurritoCondimentOnion(topOfOrder);
               return true;
               
          }
          
          else if (condiChar == 'J'){ //Add jalapenos
               
               topOfOrder = new BurritoCondimentJalapeno(topOfOrder);
               return true;
               
          }
          
          else return false; //Return false if a bad character was given
          
     }
     
     /**
      *   Returns the top of the order, and initializes the builder back to the empty state
      *
      *   @return   A BurritoItem containing the burrito ordered.
      *             Will return null if there is no order
      */
     
     public BurritoItem orderBurrito(){
          
          BurritoItem returnItem = topOfOrder;
          topOfOrder = null; //Initalize builder
          return returnItem; //Return completed order
          
     }
     
     /**
      *   Checks the order to see if it has a tortilla.
      *
      *   @return   A boolean, telling whether or not the order has a tortilla
      */
          
     private boolean hasTortilla(){
          
          if (topOfOrder == null) return false; //Return false if no items in order
          
          else return true; //Return true if one item is in order
          
     }

}
