/**
*  XMLProcessOpenState.java
*
*  @author  Philip Westrich
*  @version November 25, 2013
*
*  This object processes the opening tag. <br>
*  It will accumulate characters, thenpush the tag in to the stack. <br>
*  It will then move to the BetweenTagState. <br>
*/

import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class XMLProcessOpenState implements XMLStateInterface {

   /** The manager */
   private XMLStateManager manager;
   
   /** Reference to opening tag stack */
   private Stack<String> openingTags;
   
   /** Reference to list of output */
   private LinkedList<String> output;
   
   /** Has a tag been opened already? (no second <'s in tag) */
   private boolean tagOpened;
   
   /** Tag to build */
   private StringBuilder tag;
   
   /**
    * Sets up the state.
    *
    * @param   manager     Reference to the StateManager
    * @param   openingTags Reference to the opening tag stack
    * @param   output      Reference to the list of formatted output
    *
    * @throws  NullPointerException if any reference is null.
    */
   
   public XMLProcessOpenState(XMLStateManager manager, Stack<String> openingTags,
                             LinkedList<String> output) throws NullPointerException {
      
      if (manager == null || openingTags == null || output == null){
         
         throw new NullPointerException("Error: Null argument(s) passed in.");
         
      }
      
      this.manager      = manager;
      this.openingTags  = openingTags;
      this.output       = output;
      tagOpened         = false;
      tag               = new StringBuilder();
      
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
         
         tagOpened = false;
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
    * Detects the < character.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectLessThan(char[] line) throws XMLParsingException {
      
      tagOpened = true; //A tag nas been opened
      tag.append(line[0]); //Append the character
      line = Arrays.copyOfRange(line, 1, line.length); //Remove character from line
      
      this.detect(line); //Keep the same state
      
   }
   
   /**
    * Detects the > character. <br>
    * Pushes the completed tag onto both the open tags stack and the formatted 
    * output list, with its tabs. <br>
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectGreaterThan(char[] line) throws XMLParsingException {
      
      tagOpened = false; //Tag now not open.
      
      tag.append(line[0]); //Append the character
      line = Arrays.copyOfRange(line, 1, line.length); //Remove character
      
      openingTags.push(tag.toString()); //Push finished tag onto stack
      
      String tabs = ""; //Tabs for proper output formatting
      
      for (int i = 1; i < openingTags.size(); i++){
         
         tabs += "\t"; //Add a tab for each tag in stack
         
      }
      
      output.add(tabs + tag.toString()); //Add tag with its tabs
      
      tag = new StringBuilder(); //Reset builder.
      
      XMLStateInterface nextState = manager.getBetweenTagState();
      manager.setNextState(nextState); //Move to the Between Tag State
      nextState.detect(line);
      
   }
   
   /**
    * Detects the / character. <br>
    * This character is not allowed in an opening tag; it signals a closing tag.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectSlash(char[] line) throws XMLParsingException {
      
      throw new XMLParsingException("Invalid character in tag name at line ");
      
   }
   
   /**
    * Detects any other character. <br>
    * Adds it to the StringBuilder.
    *
    * @param   line  Character array with the line.
    *
    * @throws  XMLParsingException  if error parsing file
    */
   
   public void detectOther(char[]line) throws XMLParsingException {
      
      if (!tagOpened){
         
         throw new XMLParsingException("Tag not opened at line ");
         
      }
      
      tag.append(line[0]); //Append character to tag
      line = Arrays.copyOfRange(line, 1, line.length); //Remove character from line
      this.detect(line); //Keep same state
      
   }

}
