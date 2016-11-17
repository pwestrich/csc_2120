/**
 *  XMLEndTagState.java
 *
 *  @author  Philip Westrich
 *  @version November 25, 2013
 *
 *  This object processes the end of the tag after processing an element. <br>
 */

import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class XMLEndTagState implements XMLStateInterface {

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
   
   public XMLEndTagState(XMLStateManager manager, Stack<String> openingTags,
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
    * Calls one of the other four detect mehtods pending implementation.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detect(char[] line) throws XMLParsingException {
      
      detectSlash(line); //Look for a '/'
      
   }
   
   /**
    * Unsupported.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectLessThan(char[] line) throws XMLParsingException {
      
      throw new UnsupportedOperationException("detectLessThan not supported in EndTagState.");
      
   }
   
   /**
    * Unsupported
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectGreaterThan(char[] line) throws XMLParsingException {
      
      throw new UnsupportedOperationException("detectGreaterThan not supported in EndTagState.");
      
   }
   
   /**
    * Detects the / character.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectSlash(char[] line) throws XMLParsingException {
      
      if (line[1] == '/'){
         
         //Move to ProcessCloseTagState
         XMLStateInterface nextState = manager.getClosingState();
         manager.setNextState(nextState);
         nextState.detect(line);
         
      }
      
      else {
         
         throw new XMLParsingException("Need a closing tag at line ");
         
      }
      
   }
   
   /**
    * Detects any other character.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectOther(char[] line) throws XMLParsingException {
      
      throw new UnsupportedOperationException("detectOther not supported in EndTagState.");
      
   }

}
