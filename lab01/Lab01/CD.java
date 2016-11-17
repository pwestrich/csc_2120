/**
 *  CD.java       Author: Lewis and Loftus
 *
 *  Represents a compact disc.
 */

import java.text.DecimalFormat;

public class CD implements Comparable<CD>
{
   private String title, artist;
   private double value;
   private int tracks;

   /**
    *  Creates a new CD with the specified information.
    */
   public CD (String theTitle, String theArtist, double theValue, int theTracks)
   {
      title = theTitle;
      artist = theArtist;
      value = theValue;
      tracks = theTracks;
   }

   public String getTitle()
   {
      return title;
   }

   public double getPrice()
   {
      return value;
   }

   public String getArtist()
   {
      return artist;
   }

   public int getTracks()
   {
      return tracks;
   }

   public void setTracks(int theTracks)
   {
      tracks = theTracks;
   }

   public void setPrice(double thePrice)
   {
      value = thePrice;
   }

   /**
    *  Compares two CDs.
    */
   public int compareTo (CD cd)
   {
      //define the way that CD's are compared-- in this case, alphabetically by title
      //comparing the objects is defined to be comparing the strings that make up the title
      //we could make this much more complex (compare titles and artists) if we wanted to

      int result;
      String first = this.getTitle().toLowerCase();
      String second = cd.getTitle().toLowerCase();

      //if first comes before second alphabetically, a negative number will be returned
      //if first comes after second alphabetically, a positive number will be returned
      //if first.equals(second), a 0 will be returned
     
      result = first.compareTo(second);  //Strings already implement the Comparable interface as detailed above
      return result;
   }

   /**
    *  Returns a description of this CD.
    */
   public String toString()
   {
      DecimalFormat fmt = new DecimalFormat("0.00");

      String description = title + "  " + artist + "  $" + fmt.format(value) + "  " + tracks;
      return description;
   }

}

