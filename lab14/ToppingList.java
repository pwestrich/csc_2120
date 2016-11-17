import javax.swing.JList;
import java.util.ArrayList;
import java.awt.dnd.*;
import java.awt.datatransfer.*;

public class ToppingList extends JList implements DragGestureListener, DropTargetListener
{
   //DO THIS
   //constructor, etc.
   
   //Array list of topping names
   private ArrayList<String> toppings;
   
   //the selected topping
   private ToppingSelected tsl;
   
   //constructor toaccept a new selected topping
   public ToppingList(ToppingSelected newTopping){
      
      super();
      this.reset();
      tsl = newTopping;
      
   }
   
   //function to reset the object
   public void reset(){
      
      //make list
      toppings = new ArrayList<String>();
      
      //add topping names to list
      toppings.add("pepperoni");
      toppings.add("onions");
      toppings.add("green peppers");
      toppings.add("sausage");
      toppings.add("mushrooms");
      
      //set the items in the lis to whT is in the ArrayList
      setListData(toppings.toArray());
      
   }

   //interface method to recognize the start of a drag event and track the drag as it proceeds
   public void dragGestureRecognized(DragGestureEvent dge) 
   {
      if (getSelectedIndex() == -1) return;  //no valid selection, ignore the drag

      //wrap the object up into a transferable
      //DO THIS

      ToppingTransferable transferable = new ToppingTransferable((String) getSelectedValue());
 

      //start the dragging process

      //extract the drag source out from the event object
      DragSource dragSource = dge.getDragSource();  

      //a copy of the dragged object placed inside the drag source
      //track the drag process
      dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, null);
   }

   //interface method to respond to a drop event
   public void drop(DropTargetDropEvent dtde) 
   {
      //get the dropped object and try to figure out what it is
      Transferable transfer = dtde.getTransferable();

      //possible flavors for the dropped object
      //DO THIS
      DataFlavor[] flavors = transfer.getTransferDataFlavors();

      String addTopping;
      try
      {
         //poll the flavors to determine the object being dropped
         //the drop target does not know from where the object has been dragged
         //the object can thus be of several different types
         //in our case, however, only one object type is allowed or expected--  a String reference

         //DO THIS
         addTopping = (String) transfer.getTransferData(flavors[0]);
         tsl.toppingSelected(addTopping);

      }
      //the flavor is not the correct type
      catch (UnsupportedFlavorException ufe)
      {
         dtde.rejectDrop();  
         return;
      }
      catch (java.io.IOException ioe)
      {
         dtde.rejectDrop();
         return;
      }

      //accept copy drops...
      dtde.acceptDrop(DnDConstants.ACTION_COPY);

      //if we made it this far, everything worked.
      dtde.dropComplete(true);

      //DO THIS remove the topping, inform the GUI

      removeTopping(addTopping);

   }
   
   public void removeTopping(String toRemove){
      
      //remove topping from array
      toppings.remove(toRemove);
      
      //remove topping from listener
      tsl.toppingSelected(toRemove);
      
      //reset the items in the list
      setListData(toppings.toArray());
      
   }

   public void dragEnter(DropTargetDragEvent dtde) {}
   public void dragExit(DropTargetEvent dte) {}
   public void dragOver(DropTargetDragEvent dtde) {}
   public void dropActionChanged(DropTargetDragEvent dtde) {}
}