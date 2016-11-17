/**
*  XMLDriver.java
*
*  @author  Philip Westrich
*  @version November 25, 2013
*
*  This is the driver for the "Well-Formed XML" assignment. <br>
*  It will handle all interaction with the user, and direct the XMLStateManager
*  to do work with the file.
*/

import java.util.Stack;
import java.util.LinkedList;

public class XMLDriver{
   
   /** The StateManager */
   public static XMLStateManager manager;
   
   /**
    * Main method. <br>
    * Will ask the user for filenames, and print results.
    *
    * @param   args  Ignored.
    */
   
   public static void main(String args[]){
      
      System.out.println("Well-Formed XML");
      System.out.println("---------------------------------------------------");
      System.out.println("This program will parse an XML document, "
                         + "and tell if it is well-formed.");
      System.out.println("If it is, then it will print the read document in its "
                         + "properly-indented format, and prompt for another.");
      System.out.println("");
      
      while (true){
         
         manager = new XMLStateManager(); //Make a new manager
         String filename = Keyboard.readString("Please enter a filename (Q to quit): ");
         
         if (filename.equalsIgnoreCase("Q")) break;
         
         try {
            
            System.out.print("Parsing file... ");
            manager.parseFile(filename);
            System.out.println("Done!");
            
         }
         
         catch (FileIOException it){
            
            System.out.println("Invalid filename: " + filename);
            continue; //don't try to write file
            
         }
         
         catch (XMLParsingException it){
            
            System.out.println(it.getMessage());
            continue; //don't try to write file
            
         }
         
         System.out.println("File is well-formed!");
         filename = Keyboard.readString("Please enter a file to save to: ");
         
         try {
            
            manager.writeFile(filename);
            
         }
         
         catch (FileIOException it){
            
            System.out.println("Invalid filename: " + filename);
            
         }
         
      }
      
   }
   
}
