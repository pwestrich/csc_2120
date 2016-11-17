package plot;
import java.awt.Color;
import java.awt.Graphics;
import matrix.*;

/**
 *  This class represents a discrete set of points that is extended to 
 *  a continuous set by linear interpolation. <br>
 *  Any intermediate functional values
 *  not specifically stored are obtained by a linear interpolation between stored values. <br>
 */
public class DataList implements Plottable
{
   protected ListSortedInterface<Point> list;
   private int size;
   private Color lineColor;
   private Color pointColor;
   private final int CIRCLESIZE;
   private String title;

   /**
    *  The constructor accepts two colors (java.awt.Color) and an integer. <br>  
    *  The first color is the color of the line joining the represented points. <br>
    *  The second color is the color of the actual represented points. <br>
    *  The integer is the size of the represented points. <br>
    */
   public DataList(String title, Color color1, Color color2, int circle)
   {
      list = new ListSortedDoubleNode<Point>();
      size = 0;
      lineColor = color1;
      pointColor = color2;
      CIRCLESIZE = circle;
      this.title = title;
   }

   /**
    *  Adds a point to the discrete set of represented points. <br>  
    */   
   public void addPoint(double x, double y)
   {
      list.add(new Point(x, y));
      size++;
   }

   /**
    *  Returns the number of represented points. <br>  
    */
   public int size()
   {
      return size;
   }

   public void draw(Graphics g, int width, int height, MatrixOperationsInterface window)
   {
      int xtemp1 = 0;
      int ytemp1 = 0;
      int xtemp2 = 0;
      int ytemp2 = 0;

      //draw the lines first
      int count = 0;

      g.setColor(lineColor);
      for (Point point : list)
      {
         point = point.multiply(window);

         xtemp1 = (int) (point.getX() + 0.5);
         ytemp1 = (int) (point.getY() + 0.5);

         if (count > 0)
         {
            g.drawLine(xtemp1, ytemp1, xtemp2, ytemp2);
         }

         xtemp2 = xtemp1;
         ytemp2 = ytemp1;
         count++;
      }

      //draw the points second
      boolean test = false;

      g.setColor(pointColor);
      for (Point point : list)
      {
         point = point.multiply(window);

         xtemp1 = (int) (point.getX() + 0.5);
         ytemp1 = (int) (point.getY() + 0.5);

         g.fillOval(xtemp1 - CIRCLESIZE/2, ytemp1 - CIRCLESIZE/2, CIRCLESIZE, CIRCLESIZE);
      }

      g.drawString(title, xtemp1 - 50, ytemp1 - 25);
   }

   /**
    *  Displays all the represented points. <br>  
    */
   public String toString()
   {
      String str = "";
      for (Point point : list)
      {
         str += point.toString();
      }
      return str;
   }

}