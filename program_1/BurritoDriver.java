/**
*    BurritoDriver.java
*
*    for CSC 2120 Program #1
*    "Decorated Burritos"
*
*    @author Philip Westrich
*    @date   October 7, 2013
*
*    This is the driver for ordering Burritos. It will prompt the user for input
*    and process it accordingly.
*
*    It makes use of both the Builder and Decorator patterns we discussed in class.
*
*    I know it was said in the specification to not check condiments in the driver,
*    but it was the easiest way I could think of to make the prompt display correctly.
*    It will still allow you to input a duplicate condiment; it just won't tell
*    the user that he/she is allowed to do so.
*/

public class BurritoDriver {
     
     /**
      *   The main method. Handles calling all functions needed to create any
      *   burritos.
      *
      *   @param    args Array of command-line arguments. Is not used; will ignore
      *                  any data passed into it.
      */
     
     
     public static void main(String[] args){
          
          Keyboard board = Keyboard.getKeyboard(); //The keyboard
          BurritoBuilder bb = null;
          String input = board.readString("Would you like to order a burrito? (Y/N): ");
          int choice = 0;
          int numBurritos = 0;
          double totalPrice = 0.0;
          
          while (input.equalsIgnoreCase("Y")){
               
               choice = menu(); //Display menu
               
               if (choice == 1){ //Build own burrito option
                    
                    bb = new BurritoBuilder(); //Create builder object
                    requestTortilla(bb);
                    requestFillers(bb);
                    requestCondiments(bb);
                    
               }
               
               if (choice == 2){ //SpicyVeggie option
                    
                    bb = new SpicyVeggie();
                    
               }
               
               if (choice == 3) { //Spicy Meaty option
                    
                    bb = new SpicyMeaty();
                    
               }
               
               BurritoItem bi = bb.orderBurrito(); //Get the burrito
               numBurritos++; //Increment number of burritos ordered
               totalPrice += bi.getPrice(); //Add the price to the running total
               showOrder(bi); //Show the order
               
               input = board.readString("Would you like to order another burrito? (Y/N): ");

          }
          
          System.out.println("The total cost of your order is: " +
                             Currency.formatCurrency(totalPrice) + ".");
          
          System.out.println("You ordered " + numBurritos + " burrito(s).");
          
     }
     
     /**
      *   Displays the menu, asking the user for input.
      *
      *   @return   int  the choice given by the user, either 1, 2, or 3.
      */
     
     private static int menu(){
          
          Keyboard board = Keyboard.getKeyboard();
          
          int choice = 0;
          
          System.out.println("1. Build your own burrito");
          System.out.println("2. Spicy Veggie");
          System.out.println("3. Spict Meaty");
          
          while (choice < 1 || choice > 3){
               
               //Make the user enter until a valid choice is selected
               choice = board.readInt("Your choice? (1, 2, or 3): ");
               
          }
          
          return choice;
          
     }
     
     /**
      *   Asks the user for a tortilla. Must have at least one; will not let the
      *   user continue if there isn't one
      *
      *   @param bb the current BurritoBuilder object
      */
     
     private static void requestTortilla(BurritoBuilder bb){
          
          Keyboard board = Keyboard.getKeyboard();
          String input = null;
          
          System.out.println("What kind of tortilla would you like?");
          
          do { //Loop until a tortilla is bult successfully
               
               input = board.readString("(C)hipotle, (S)pinach, or (W)heat: ");
               
               if (input == null || input.length() == 0 || input.equals("")) {
                    
                    input = "NOPE"; //Empty lines are unacceptable!
                    
               }
               
               input = input.toUpperCase(); //Convert all characters to uppercase
               
           } while (!bb.buildTortilla(input.charAt(0)));
          
     }
     
     private static void requestFillers(BurritoBuilder bb){ 
          
          //Variables for working
          Keyboard board = Keyboard.getKeyboard();
          String input = null;
          char fillerChar = '\0';
          boolean atLeastOne = false;
          
          System.out.println("What filler(s) would you like for your burrito?");
          
          while (true) { //Loop until at least one is selected, and inputChar is D
          
               input = board.readString("(G)rilled Chicken, (S)teak, (B)lack Bean, (D)one: ");
               
               if (input == null || input.length() == 0 || input.equals("")) {
                    
                    input = "NOPE"; //Empty lines are unacceptable!
                    
               }
               
               input = input.toUpperCase(); //Convert all characters to uppercase
               fillerChar = input.charAt(0); //Move first char of string into char
               
               //Break from loop if at least one filler was selected and the user is done
               if (atLeastOne && (fillerChar == 'D')) break;
               
               atLeastOne = bb.buildFiller(fillerChar);
               
          }
          
     }
     
     /**
      *   Asks the user for condiments. Will loop until the user is done, or
      *   there are no more condiments to add.
      *   Also will not allow multiple servings of each condiment to be had
      *
      *   @param bb the current BurritoBuilder object
      */
     
     private static void requestCondiments(BurritoBuilder bb){
          
          Keyboard board = Keyboard.getKeyboard();
          String input = null;
          String display = "(P)ico De Gallo, (G)uacamole, (O)nion, (J)alapeno, (D)one: ";
          char condiChar = '\0';
          
          //Array of booleans to check if condiments have been ordered previously
          //Is only used for proper prompt display.
          boolean[] bools = {false, false, false, false};
          
          
          System.out.println("What condiments would you like?");
          
          while (true){ //Loop until user is done
               
               input = board.readString(display); //Read the input from the user
               
               if (input == null || input.length() == 0 || input.equals("")) {
                    
                    input = "NOPE"; //Empty lines are unacceptable!
                    
               }
               
               else {
                    
                    input = input.toUpperCase(); //Convert all to uppercase
                    condiChar = input.charAt(0); //Get the first character of the string.
                    
               }
               
               if (condiChar == 'P' && !bools[0]){
                    
                    bools[0] = true; //Mark condiment as used
                    
                    //Remove the selected condiment from the prompt
                    display = display.replace("(P)ico De Gallo, ", "");
                    
               }
               
               else if (condiChar == 'G' && !bools[1]){
                    
                    bools[1] = true; //Mark condiment as used

                    //Remove the selected condiment from the prompt
                    display = display.replace("(G)uacamole, ", "");
                    
               }
               
               else if (condiChar == 'O' && !bools[2]){
                    
                    bools[2] = true; //Mark condiment as used
                    
                    //Remove the selected condiment from the prompt
                    display = display.replace("(O)nion, ", "");
                    
               }
               
               else if (condiChar == 'J' && !bools[3]){
                    
                    bools[3] = true; //Mark condiment as used
                    
                    //Remove the selected condiment from the prompt
                    display = display.replace("(J)alapeno, ", "");
                    
               }
               
               else if (condiChar == 'D'){
                    
                    break; //Break if the user is done 
                    
               }
               
               else continue; //If input is invalid, continue the loop.
               
               bb.buildCondiment(condiChar); //Add the condiment to the burrito
               
          }
          
     }
     
     /**
      *   Shows the order. Calls the toString() method on the given object, and
      *   the getPrice() method.
      *
      *   @param bi a BurritoItem, the order to be shown.
      */
     
     private static void showOrder(BurritoItem bi){
          
          System.out.println("Your order:");
          System.out.println(bi.toString());
          
          System.out.println("Your burrito cost is " + Currency.formatCurrency(bi.getPrice()));
          
          
     }

}
