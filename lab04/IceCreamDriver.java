
class IceCreamDriver{

     public static void main(String[] args){
          
          double total = 0.0;
          int numOrders = 0;
          String input = "\0";
          
          Keyboard board = Keyboard.getKeyboard();
          
          while (true){
          
               input = board.readString("Would you like to order an ice cream cone? (Y/N): ");
          
               if (!input.equalsIgnoreCase("Y")) break;
          
               Flavor newFlavor = getFlavorChoice();
               Topping newTopping = getToppingChoice();
               int numScoops = getScoopChoice();
               Cone newCone = getConeChoice();
          
               IceCream newIceCream = new IceCream(numScoops, newFlavor, newTopping);
          
               IceCreamCone newIceCreamCone = new IceCreamCone(newIceCream, newCone);
               
               total += newIceCreamCone.getNumericPrice();
               numOrders++;
          
               System.out.println("Your order: \n" + newIceCreamCone + newIceCreamCone.getPrice());
               
          }
          
          System.out.println("Your total is: " + Currency.formatCurrency(total) +
                             " for " + numOrders + " cone(s).");
          
     }
     
     public static Flavor getFlavorChoice(){
          
          int choice = 0;
          Flavors allFlavors = Flavors.getFlavors();
          Keyboard board = Keyboard.getKeyboard();

          System.out.println(allFlavors.listFlavors()); //list all flavors
          
          do {
          
               choice = board.readInt("Enter yout flavor choice: ");
               
          } while (choice < 1 || choice > 18);
          
          return allFlavors.getFlavor(choice);
          
     }
     
     public static Topping getToppingChoice(){
          
          int choice = 0;
          Toppings allToppings = Toppings.getToppings();
          Keyboard board = Keyboard.getKeyboard();

          System.out.println(allToppings.listToppings());
          
          do {
               
               choice = board.readInt("Enter your topping choice: ");
               
          } while (choice < 1 || choice > 5);
          
          return allToppings.getTopping(choice);
          
     }
     
     public static int getScoopChoice(){
          
          int choice = 0;
          Keyboard board = Keyboard.getKeyboard();

          do {
               choice = board.readInt("How many scoops would you like (up to 3)? ");
               
          } while (choice < 1 || choice > 3);
          
          return choice;
          
          
     }
     
     public static Cone getConeChoice(){
          
          int choice = 0;
          Keyboard board = Keyboard.getKeyboard();

          do {
          
               choice = board.readInt("Would you like a 1) sugar cone, 2) waffle cone, or 3) cup? ");
               
          } while (choice < 1 || choice > 3);
          
          return new Cone(choice);
          
     }

}
