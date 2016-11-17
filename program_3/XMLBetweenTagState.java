/**
*  XMLBetweenTagState.java
*
*  @author  Philip Westrich
*  @version November 25, 2013
*
*  This object will process the data between the XML tags. <br>
*  It determines whether or not the next bit of data is an 
*  element or another opening tag, and changes states as needed. <br>
*/

import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class XMLBetweenTagState implements XMLStateInterface {

   /** The manager */
   private XMLStateManager manager;
   
   /** Reference to opening tag stack */
   private Stack<String> openingTags;
   
   /** Reference to list of output */
   private LinkedList<String> output;
   
   /** Is there an element in here? */
   private boolean isElement;
   
   /** Built element */
   private StringBuilder element;
   
   /**
    * Sets up the state.
    *
    * @param   manager     Reference to the StateManager
    * @param   openingTags Reference to the opening tag stack
    * @param   output      Reference to the list of formatted output
    *
    * @throws  NullPointerException if any reference is null.
    */
   
   public XMLBetweenTagState(XMLStateManager manager, Stack<String> openingTags,
                             LinkedList<String> output) throws NullPointerException {
      
      if (manager == null || openingTags == null || output == null){
         
         throw new NullPointerException("Error: Null argument(s) passed in.");
         
      }
      
      this.manager      = manager;
      this.openingTags  = openingTags;
      this.output       = output;
      isElement         = false;
      element           = new StringBuilder();
      
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
    * There are two cases for this: <br>
    *
    * If there is not an element in the tag, it signals that the next set of
    * characters are another opening tag, and we need to move to the Detect Tag state. <br>
    *
    * If there is an element, it signals the end of the element, and we need to
    * move to the EndBetweenTag state. <br>
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectLessThan(char[] line) throws XMLParsingException {
      
      if (isElement){
         
         isElement = false; //We're done building the element
         
         String tabs = ""; //Tabs for properly formatted output
         
         for (int i = 0; i < openingTags.size(); i++){
            
            tabs += "\t";
            
         }
         
         output.add(tabs + element.toString()); //Add element with tabs to output
         
         element = new StringBuilder(); //Reset element
         
         //Change to the end between tag state
         XMLStateInterface nextState = manager.getEndTagState();
         manager.setNextState(nextState);
         nextState.detect(line);
         
      }
      
      else {
         
         //Change to the detect tag state
         XMLStateInterface nextState = manager.getDetectTagState();
         manager.setNextState(nextState);
         nextState.detect(line);
         
      }
      
   }
   
   /**
    * Detects the > character. <br>
    * This character is not allowed in the element.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectGreaterThan(char[] line) throws XMLParsingException {
      
      throw new XMLParsingException("Invalid character in element at line ");
      
   }
   
   /**
    * Detects the / character. <br>
    * This character does not matter in the element, and is treated as any other.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectSlash(char[] line) throws XMLParsingException {
      
      detectOther(line);
      
   }
   
   /**
    * Detects any other character. <br>
    * Adds the character to the built element
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectOther(char[] line) throws XMLParsingException {
      
      isElement = true; //There is an element in this tag
      
      element.append(line[0]); //Add character to element
      line = Arrays.copyOfRange(line, 1, line.length); //Remove character
      this.detect(line); //Keep same state.
      
   }

}
