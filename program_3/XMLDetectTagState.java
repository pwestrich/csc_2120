/**
 *  XMLDetectTagState.java
 *
 *  @author  Philip Westrich
 *  @version November 25, 2013
 *
 *  This object detects the tag and sends it to the correct state. <br>
 */

import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class XMLDetectTagState implements XMLStateInterface {

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
   
   public XMLDetectTagState(XMLStateManager manager, Stack<String> openingTags,
                             LinkedList<String> output) throws NullPointerException {
      
      if (manager == null || openingTags == null || output == null){
         
         throw new NullPointerException("Error: Null argument(s) passed in.");
         
      }
      
      this.manager      = manager;
      this.openingTags  = openingTags;
      this.output       = output;
      
   }
   
   /**
    * Detects a character. <br>
    * Determines whether this is an opening or a closing tag. <br>
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detect(char[] line) throws XMLParsingException {
      
      //line[0] should be a '<'
      if (line[1] == '/'){
         
         detectSlash(line);
         
      }
      
      else {
         
         //If it isn't a '/', then it's an opening tag
         detectOther(line);
         
      }
      
   }
   
   /**
    * Unsupported.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectLessThan(char[] line) throws XMLParsingException {
      
      throw new UnsupportedOperationException("detect< not supported in detectTagState.");
      
   }
   
   /**
    * Unsupported.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectGreaterThan(char[] line) throws XMLParsingException {
      
      throw new UnsupportedOperationException("detect> not supported in detectTagState.");
      
   }
   
   /**
    * Detects the / character. <br>
    * Changes states to the CloseTagState.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectSlash(char[] line) throws XMLParsingException {
      
      XMLStateInterface nextState = manager.getClosingState();
      manager.setNextState(nextState);
      nextState.detect(line);
      
   }
   
   /**
    * Detects any other character. <br>
    * Changes states to the processOpenTag state.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectOther(char[]line) throws XMLParsingException {
      
      XMLStateInterface nextState = manager.getOpenTagState();
      manager.setNextState(nextState);
      nextState.detect(line);
      
   }
   
}
