
class Cone{

     private final int coneType;
     
     public Cone(int coneType){
          
          if (coneType < 1 || coneType > 3){ //Check for error in input
               
               this.coneType = coneType;
               
          }
          
          else this.coneType = 1;            //Set to default value
          
     }
     
     public int getConeType(){
          
          return coneType;
          
     }
     
     public double getPrice(){
          
          if (coneType == 1){
               
               return 0.59;
               
          }
          
          else if (coneType == 2){
               
               return 0.79;
               
          }
          
          else return 0.00;
          
     }
     
     public String toString(){
          
          if (coneType == 1){
               
               return "Cone type: Sugar cone\n";
               
          }
          
          else if (coneType == 2){
               
               return "Cone type: Waffle cone\n";
               
          }
          
          else return "Cone type: Cup\n";
          
     }

}
