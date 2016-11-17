/**
*  XMLOutsideTagState.java
*
*  @author  Philip Westrich
*  @version November 25, 2013
*
*  This object processes the oustide of the tag. <br>
*  It will find all whitespace before the tag and delete it, then send it to
*  the DetectTagState. <br>
*/

import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class XMLOutsideTagState implements XMLStateInterface {

   /** The manager */
   private XMLStateManager manager;

   /** Reference to opening tag stack */
   private Stack<String> openingTags;
   
   /** Reference to list of output */
   private LinkedList<String> output;
   
   /**
    * Sets up the state.
    *
    * @param   manager     Reference to the StateManager
    * @param   openingTags Reference to the opening tag stack
    * @param   output      Reference to the list of formatted output
    *
    * @throws  NullPointerException if any reference is null.
    */
   
   public XMLOutsideTagState(XMLStateManager manager, Stack<String> openingTags,
                             LinkedList<String> output) throws NullPointerException {
      
      if (manager == null || openingTags == null || output == null){
         
         throw new NullPointerException("Error: Null argument(s) passed in.");
         
      }
      
      this.manager      = manager;
      this.openingTags  = openingTags;
      this.output       = output;
      
   }
   
   /**
    * Processes the outside of the tag. <br>
    * Eleminates all whitespace bafore the tag, then looks for a '<' <br>
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detect(char[] line) throws XMLParsingException {
      
      //Return and get next line if array is empty
      if (line.length == 0) return;
      
      int startIndex = 0;
      
      while (Character.isWhitespace(line[startIndex])){
         
         startIndex++; //Find all whitespace
         
      }
      
      if (startIndex > 0){
         
         //Trim the whitespace if any is found
         line = Arrays.copyOfRange(line, startIndex, line.length);
         
      }
      
      //Otherwise, detect a <.
      detectLessThan(line);
      
   }
   
   /**
    * Detects the < character. <br>
    * If found, passes it aling to the DetectTagState. <br>
    * Otherwise, throws an exception. <br>
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectLessThan(char[] line) throws XMLParsingException {
      
      if (line[0] == '<'){
         
         XMLStateInterface nextState = manager.getDetectTagState();
         manager.setNextState(nextState);
         nextState.detect(line);
         return;
         
      }
      
      else {
         
         detectOther(line);
         
      }
      
   }
   
   /**
    * Detects the > character.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectGreaterThan(char[] line) throws XMLParsingException {
      
      throw new UnsupportedOperationException("detectGreaterThan not supported in outsideTagState.");
      
   }
   
   /**
    * Unsupported.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectSlash(char[] line) throws XMLParsingException {
      
      throw new UnsupportedOperationException("detectSlash not supported in outsideTagState.");
      
   }
   
   /**
    * Always throws an exception. Random characters outside the tag 
    * are not allowed.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectOther(char[] line) throws XMLParsingException {
      
      throw new XMLParsingException("Invalid outside tag at line ");
      
   }

}
