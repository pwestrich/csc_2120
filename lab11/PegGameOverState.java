

public class PegGameOverState implements PegState {

   private Pegs pegs;
   
   public PegGameOverState(Pegs pegs){
      
      this.pegs = pegs;
      
   }
   
   public void mouseClicked(int x, int y){
      
      int clickedSlot = pegs.findSelectedSlot(x, y);
      
      if (clickedSlot < 0){
         
         return;
         
      }
      
      else pegs.startGame(clickedSlot);
      
   }

}
