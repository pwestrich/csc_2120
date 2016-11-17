import java.util.ArrayList;
import bst.*;


public class TreeSortDriver
{

   public static void main (String[] args)
   {
      //use the Keyboard class, try-catch, and a while loop to continue calling readMusic 
      //until a valid file name is entered
      //as checked exceptions have been converted to unchecked exceptions, 
      //you must remember to do this with end user input, the compiler will not help you

        Keyboard board = Keyboard.getKeyboard(); //Get the keyboard
        String input = null;
        CD[] cds = null;
        boolean success = false;
        
        do {
             
             input = board.readString("Please enter a filename: ");
             
             try {
             
                  cds = readMusic(input);
                  success = true;
                  
             }
             
             catch (FileIOException it) {
                  
                  System.out.println(it.getMessage());
                  
             }
             
        } while (!success);

      //once you have the array of CDs back from readMusic, sort them
      //and print them out to make sure that they are sorted
        
        System.out.println("UNSORTED------------------------------------");
        
        for (CD item : cds){
             
             System.out.println(item);
             
        }
        
        BinarySearchTree tree = new BinarySearchTree(true, true);
        
        for (CD item : cds){
             
             tree.insert(item); //Insert all items into the tree
             
        }
        
        TreeIterator iter = tree.iterator(); //Get iterator
        
        iter.setInorder(); //Set to inorder traversal
        
        int i = 0;
        
        while (iter.hasNext()){ //Put
             
             cds[i] = (CD) iter.next();
             i++;
             
        }
        
        System.out.println("SORTED-----------------------------------------");
        
        for (Object item : cds){ //Print CDs
             
             System.out.println(item);
             
        }

   }

   private static CD[] readMusic(String fileName)
   {
      //DO THIS complete this method using the FileIO class
        FileIO file = new FileIO(fileName, FileIO.FOR_READING);
        String str = file.readLine();
        ArrayList<CD> cds = new ArrayList<CD>();

      while (!file.EOF())
      {
           String title = file.readLine();
         int year = Integer.parseInt(file.readLine());
         int rating = Integer.parseInt(file.readLine());
         int numTracks = Integer.parseInt(file.readLine());
         CD cd = new CD(title, str, year, rating, numTracks);

         cds.add(cd);
         int tracks = 1;

         while (tracks <= numTracks)
         {
              String track_str = file.readLine();
              String[] pieces = track_str.split(","); //divide the string up into two pieces
              String len = pieces[0];
              String songTitle = pieces[1];
              cd.addSong(songTitle, len);
              tracks++;
         }

           str = file.readLine();
      }


      //create a CD[] of the correct size, populate it using a for-each statement
        
        CD[] cds_array = new CD[cds.size()];
        int i = 0;
        
        for (CD item : cds){
             
             cds_array[i] = cds.get(i);
             i++;
             
        }
        
      return cds_array;
   }
}