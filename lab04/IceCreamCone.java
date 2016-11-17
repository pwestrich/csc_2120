
class IceCreamCone{

     private IceCream iceCreamType;
     private Cone coneType;
     
     public IceCreamCone(IceCream newIceCream, Cone newCone){
          
          if (newIceCream == null){
               
               iceCreamType = new IceCream(1, null, null);
               
          }
          
          else iceCreamType = newIceCream;
          
          if (newCone == null){
               
               coneType = new Cone(1);
               
          }
          
          else coneType = newCone;
          
     }
     
     public String getPrice(){
          
          return "Price: " +  Currency.formatCurrency(1.99 + iceCreamType.getPrice() + coneType.getPrice());
          
     }
     
     public double getNumericPrice(){
          
          return (1.99 + iceCreamType.getPrice() + coneType.getPrice());
          
     }
     
     public String toString(){
          
          return "Your ice cream cone:\n" + coneType + iceCreamType;
          
     }


}