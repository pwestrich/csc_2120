/**
*  XMLStateManager.java
*
*  @author  Philip Westrich
*  @version November 25, 2013
*
*  This class will manage the various states in the program. <br>
*  It follows the State design pattern. <br>
*
*  It also has a reference to two data structures: a Stack<String> and 
*  LinkedList<String>, to help process the opening tags and properly formatted
*  output text, respectivley. <br>
*  
*  Each state gets a refernce to each of these, as well as this object.
*/

import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class XMLStateManager {
   
   /** Stack to store opening tags */
   private Stack<String> openingTags;
   
   /** List storing the data */
   private LinkedList<String> output;
   
   /** Line number */
   private int lineNumber;
   
   /** The current state. */
   private XMLStateInterface currentState;
   
   /** Outside the tag state */
   private XMLOutsideTagState outsideTag;
   
   /** Between tag state */
   private XMLBetweenTagState betweenTags;
   
   /** Detect tag state */
   private XMLDetectTagState detectTag;
   
   /** Process closing tag state */
   private XMLProcessCloseState closeTag;
   
   /** Process opening tag state */
   private XMLProcessOpenState openTag;
   
   /** End between tag state */
   private XMLEndTagState endState;
   
   /**
    * The only constructor. <br>
    * Sets up all of the program's states, giving each a reference to this. <br>
    */
   
   public XMLStateManager(){
      
      openingTags = new Stack<String>();
      output = new LinkedList<String>();
      lineNumber = 0;
      
      //The states
      outsideTag  = new XMLOutsideTagState   (this, openingTags, output);
      betweenTags = new XMLBetweenTagState   (this, openingTags, output);
      detectTag   = new XMLDetectTagState    (this, openingTags, output);
      closeTag    = new XMLProcessCloseState (this, openingTags, output);
      openTag     = new XMLProcessOpenState  (this, openingTags, output);
      endState    = new XMLEndTagState       (this, openingTags, output);
      
      //Set current state to starting state (outside of any tag)
      currentState = outsideTag;
      
   }
   
   /**
    * Parses the file, and detects any errors in formatting. <br>
    *
    * @param   filename             Name of file to parse
    *
    * @throws  XMLParsingException  if there is a syntax error in the file.
    * @throws  FileIOException      if there is an error reading from the file.
    */
   
   public void parseFile(String filename) throws XMLParsingException, FileIOException {
      
      String nextLine = null; //Line from the file
      char[] line = null;     //Line in char[] form
      FileIO inFile = null;   //File to read
      
      try {
         
         inFile = new FileIO(filename, FileIO.FOR_READING);
         
         nextLine = inFile.readLine(); //Read first line
         
         if (nextLine == null){ //Check if it is empty or not
            
            throw new FileIOException("No data in file ");
            
         }
         
         while (!inFile.EOF()){ //Continue to parse file while data remains
            
            nextLine = nextLine.trim();      //Trim extra whitespace
            line = nextLine.toCharArray();   //Put line in array format
            lineNumber++;                    //Increment current line number
            currentState.detect(line);       //Parse the line
            nextLine = inFile.readLine();    //Get the next line
            
         }
         
         inFile.close(); //Close file
         
      }
      
      catch (XMLParsingException it){

         inFile.close(); //Close file

         //Add line number to exception, then pass it along
         throw new XMLParsingException(it.getMessage() + lineNumber);
         
      }
                                                    
      if (!openingTags.isEmpty()){
         
         //Check for extra closing tags on stack
         throw new XMLParsingException("Not enough closing tags at line " + lineNumber);
         
      }
      
   }
   
   /**
    * Saves the finished file to disk.
    *
    * @param      filename          Name of file to save to
    *
    * @throws     FileIOException   if problem writing file
    */
   
   public void writeFile(String filename) throws FileIOException {
      
      //Open file
      FileIO outFile = new FileIO(filename, FileIO.FOR_WRITING);
      
      //Print each line
      for (String line : output){
         
         outFile.writeLine(line);
            
      }
      
      outFile.close(); //Close file
      
   }
   
   /**
    * Sets the next state to the incoming one.
    *
    * @param   next  Next state to enter.
    *
    * @throws  NullPointerException if input is null.
    */
   
   public void setNextState(XMLStateInterface next) throws NullPointerException {
      
      if (next == null){
         
         throw new NullPointerException("Error: Null argument.");
         
      }
      
      currentState = next;
      
   }
   
   /**
    * Gets the BetweenTagState.
    *
    * @return  XMLBetweenTagState   Reference to the state
    *
    */
   
   public XMLBetweenTagState getBetweenTagState(){
      
      return betweenTags;
      
   }
   
   /**
    * Gets the DetectTagState.
    *
    * @return  XMLDetectTagState Refernce to the state.
    */
   
   public XMLDetectTagState getDetectTagState(){
      
      return detectTag;
      
   }
   
   /**
    * Gets the OutsideTagState
    *
    * @return  XMLOutsideTagState   Refernce to the state.
    */
   
   public XMLOutsideTagState getOutsideTagState(){
      
      return outsideTag;
      
   }
   
   /**
    * Gets the ProcessOpenState.
    *
    * @return  XMLProcessOpenState  Refernce to the state.
    */
   
   public XMLProcessOpenState getOpenTagState(){
      
      return openTag;
      
   }
   
   /**
    * Gets the ProcessCloseState.
    *
    * @return  XMLProcessCloseState Refernce to the state.
    */
   
   public XMLProcessCloseState getClosingState(){
      
      return closeTag;
      
   }
   
   /**
    * Gets the EndTagState.
    *
    * @return  XMLEndTagState Reference to the state.
    */
   
   public XMLEndTagState getEndTagState(){
      
      return endState;
      
   }

}
