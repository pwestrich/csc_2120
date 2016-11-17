/**
 *  XMLStateInterface.java.java
 *
 *  @author  Philip Westrich
 *  @version November 25, 2013
 *
 *  This is the interface for the XML States.
 */

public interface XMLStateInterface {
   
   /** Detects a character */
   public void detect(char[] line) throws XMLParsingException;
   
   /** Detects < character */
   public void detectLessThan(char[] line) throws XMLParsingException;
   
   /** Detects > character */
   public void detectGreaterThan(char[] line) throws XMLParsingException;
   
   /** Detects / character */
   public void detectSlash(char[] line) throws XMLParsingException;
   
   /** Detects any other character */
   public void detectOther(char[]line) throws XMLParsingException;
   
}
