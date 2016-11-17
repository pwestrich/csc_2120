
class IceCream {

     private int numScoops;
     private Flavor flavor;
     private Topping topping;

     public IceCream(int scoops, Flavor newFlavor, Topping newTopping){
          
          Flavors allFlavors = Flavors.getFlavors();
          Toppings allToppings = Toppings.getToppings();

          if (scoops < 1 || scoops > 3){

               numScoops = 1;

          }

          else numScoops = scoops;

          if (newFlavor == null){

               flavor = allFlavors.getFlavor(1);

          }

          else flavor = newFlavor;

          if (newTopping == null){

               topping = allToppings.getTopping(1);

          }

          else topping = newTopping;

     }

     public double getPrice(){

          return ((0.75 * (numScoops - 1)) + topping.price());

     }

     public String toString(){

          return    "Number of scoops: " + numScoops +
                    "\nType of topping: " + topping +
                    "\nFlavor: " + flavor + "\n";

     }

}
