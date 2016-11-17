/**
*  XMLProcessClosState.java
*
*  @author  Philip Westrich
*  @version November 25, 2013
*
*  This object processes the close of the tag. <br>
*  It will accumulate characters, then check to make sure it matched the last
*  opening tag on the stack. <br>
*/

import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.EmptyStackException;

public class XMLProcessCloseState implements XMLStateInterface {

   /** The manager */
   private XMLStateManager manager;
   
   /** Reference to opening tag stack */
   private Stack<String> openingTags;
   
   /** Reference to list of output */
   private LinkedList<String> output;
   
   /** Is the tag opened? */
   private boolean tagOpened;
      
   /** Closing tag to build */
   private StringBuilder closingTag;
   
   /**
    * Sets up the state.
    *
    * @param   manager     Reference to the StateManager
    * @param   openingTags Reference to the opening tag stack
    * @param   output      Reference to the list of formatted output
    *
    * @throws  NullPointerException if any reference is null.
    */
   
   public XMLProcessCloseState(XMLStateManager manager, Stack<String> openingTags,
                             LinkedList<String> output) throws NullPointerException {
      
      if (manager == null || openingTags == null || output == null){
         
         throw new NullPointerException("Error: Null argument(s) passed in.");
         
      }
      
      this.manager      = manager;
      this.openingTags  = openingTags;
      this.output       = output;
      tagOpened         = false;
      closingTag        = new StringBuilder();
      
   }
   
   /**
    * Detects a character. <br>
    * Calls one of the other four detect mehtods pending implementation.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detect(char[] line) throws XMLParsingException {
      
      if (line.length == 0) return; //Return and get next line
      
      if (line[0] == '<'){
         
         detectLessThan(line);
         
      }
      
      else if (line[0] == '>'){
         
         detectGreaterThan(line);
         
      }
      
      else if (line[0] == '/'){
         
         detectSlash(line);
         
      }
      
      else {
         
         detectOther(line);
         
      }
      
   }
   
   /**
    * Detects the < character. <br>
    * Appends the character to the closing tag, and keeps same state. <br>
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectLessThan(char[] line) throws XMLParsingException {
      
      tagOpened = true; //A tag nas been opened
      closingTag.append(line[0]); //Append the character
      line = Arrays.copyOfRange(line, 1, line.length); //Remove character from line
      
      this.detect(line); //Keep the same state
      
   }
   
   /**
    * Detects the > character. <br>
    * Must consider two cases: <br>
    *
    * If the tag is opened, this is the end of the tag, and we need to validate
    * that the opening and closing tags are matching, then add it to the output. 
    * We then need to move to the OutsideTag state. <br>
    *
    * If the tag is not open, this is an illegal character. <br>
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectGreaterThan(char[] line) throws XMLParsingException {
      
      if (!tagOpened){
         
         throw new XMLParsingException("Cannot close nonopen tag at line ");
         
      }
      
      closingTag.append(line[0]); //Append character
      line = Arrays.copyOfRange(line, 1, line.length); //Remove character from line
      
      String openingTag = null;
      
      try {
      
         openingTag = openingTags.pop(); //Get opening tag
         
      }
      
      catch (EmptyStackException it){
         
         throw new XMLParsingException("Too many closing tags at line ");
         
      }
      
      String tabs = ""; //Tabs for proper formatting
      
      for (int i = 0; i < openingTags.size(); i++){
         
         tabs += "\t";
         
      }
      
      output.add(tabs + closingTag.toString()); //Add closing tag to output
      
      //Verify that opening and closong tags match
      String closingTagString = "<" + closingTag.substring(2, closingTag.length()); //Remove '/'
      
      if (openingTag.equals(closingTagString)){
         
         //reset closingTag
         closingTag = new StringBuilder();
          
         //Tags match, move to next state
         XMLStateInterface nextState = manager.getOutsideTagState();
         manager.setNextState(nextState);
         nextState.detect(line);
         
      }
      
      else {
         
         throw new XMLParsingException("Closing tag does not match opening tag at line ");
         
      }
      
   }
   
   /**
    * Detects the / character. <br>
    * Is treated like any other. The previous state verified for us that this
    * character is in the right place. However, we need to check and make sure
    * that there isn't another one in the tag; that's not allowed.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectSlash(char[] line) throws XMLParsingException {
         
         detectOther(line); //Pass to the detectOther method
      
   }
   
   /**
    * Detects any other character. <br>
    * Adds the character to the builder, then removes it from the line.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectOther(char[]line) throws XMLParsingException {
      
      closingTag.append(line[0]); //Append character
      line = Arrays.copyOfRange(line, 1, line.length); //Remove character
      this.detect(line); //Keep same state
      
   }

}
